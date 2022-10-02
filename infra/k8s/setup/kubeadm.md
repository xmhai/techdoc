## Setup
- Cluster
  - VirtualBox VM (1cpu 2G, HostNetwork, NAT)
  - 1 x Master Node 
  - 2 x Worker Nodes
  - Better to configure HostNetwork as first network adaptor as CNI plug in will use first adaptor by default
  - **Make sure server time are sync to host**
- CentOS 8.3 + Kubernetes 1.21.1 + Fannel + ingress-nginx + haproxy  

## Reference
  - https://upcloud.com/community/tutorials/install-kubernetes-cluster-centos-8/  
  - https://github.com/justmeandopensource/kubernetes/blob/master/docs/install-cluster-centos-7.md  
  **!!!Required changes to the instructions**  
-	For CentOS8, *Might need to set CentOS 8 to use iptables*  
    ```sh
    # revert back to iptables (CentOS 8)
    sudo nano /etc/firewalld/firewalld.conf
    sudo systemctl enable firewalld
    sudo service firewalld restart
    # check
    iptables-save | grep <hostnames>
    ```
  - For CentOS8, Install Flannel network plugin before join (use Flannel, tried Calico but not success maybe due to it only support CentOS 7)  
**(Flannel)**  
curl -LO https://raw.githubusercontent.com/coreos/flannel/master/Documentation/kube-flannel.yml  
Add - --iface=enp0s8  
https://stackoverflow.com/questions/47845739/configuring-flannel-to-use-a-non-default-interface-in-kubernetes  
kubectl apply -f kube-flannel.yml  
For flannel to work correctly, you must pass --pod-network-cidr=10.244.0.0/16 to kubeadm init  
**(Cannel)**  
canal_iface: ""  
https://stackoverflow.com/questions/46284335/kubernetes-cant-reach-other-node-services  
**(Calico)**
kubectl apply -f https://docs.projectcalico.org/manifests/calico.yaml  

## Scripts
**All Nodes**
```sh
# Disable Firewall
sudo systemctl disable firewalld
sudo systemctl stop firewalld

# Disable swap
sudo swapoff -a
sudo sed -i '/swap/d' /etc/fstab

# Disable SELinux
sudo setenforce 0
sudo sed -i --follow-symlinks 's/^SELINUX=enforcing/SELINUX=disabled/' /etc/sysconfig/selinux

# Update sysctl settings for Kubernetes networking
sudo bash -c 'cat >>/etc/sysctl.d/kubernetes.conf<<EOF
net.bridge.bridge-nf-call-ip6tables = 1
net.bridge.bridge-nf-call-iptables = 1
EOF'
sudo sysctl --system

# Install docker engine
sudo yum install -y yum-utils device-mapper-persistent-data lvm2
sudo yum-config-manager --add-repo https://download.docker.com/linux/centos/docker-ce.repo
sudo yum install -y docker-ce-19.03.12 
sudo systemctl enable --now docker

# Kubernetes Setup
sudo bash -c 'cat >>/etc/yum.repos.d/kubernetes.repo<<EOF
[kubernetes]
name=Kubernetes
baseurl=https://packages.cloud.google.com/yum/repos/kubernetes-el7-x86_64
enabled=1
gpgcheck=0
repo_gpgcheck=0
gpgkey=https://packages.cloud.google.com/yum/doc/yum-key.gpg
        https://packages.cloud.google.com/yum/doc/rpm-package-key.gpg
EOF'

# *Remove the version in the instruction
sudo yum install -y kubeadm kubelet kubectl
sudo systemctl enable --now kubelet

# Update /etc/hosts to include the master and worker nodes  
sudo nano /etc/hosts

# open docker sock
sudo chmod 666 /var/run/docker.sock
```
**Master Node**
```sh
# Initialize Kubernetes Cluster
sudo kubeadm init --apiserver-advertise-address=<master_ip> --pod-network-cidr=10.244.0.0/16
# if error: [ERROR CRI]: container runtime is not running
sudo rm /etc/containerd/config.toml
sudo systemctl restart containerd

# set kubectl
mkdir -p $HOME/.kube
sudo cp -i /etc/kubernetes/admin.conf $HOME/.kube/config
sudo chown $(id -u):$(id -g) $HOME/.kube/config

# Deploy Calico network
kubectl apply -f https://docs.projectcalico.org/manifests/calico.yaml
# Cluster join command
kubeadm token create --print-join-command
```
**Worker Node**
```sh
# Join the cluster
Use the output from kubeadm token create command in previous step from the master server and run here.
e.g. 
sudo kubeadm join 192.168.86.115:6443 --token baxjy2.g7mky8h97b9wlg5k \
        --discovery-token-ca-cert-hash sha256:4e07aa20b93b24a75f2d0ab9c724a47fe78338ab0b218a19ac22296adc6bbff7
# if error: [ERROR CRI]: container runtime is not running
sudo rm /etc/containerd/config.toml
sudo systemctl restart containerd
```

## Uninstall  
When there is an error, uninstall first:  
```
kubeadm reset  
sudo rm /etc/cni/net.d/*
sudo rmdir /etc/cni/net.d
sudo ip link del cni0
sudo ip link del flannel.1
sudo ip link del weave
sudo systemctl restart network
```

## Ingress
Setup haproxy and Kubernetes Ingress Controller (on Master Node)  
https://jhooq.com/ingress-controller-nginx/
https://www.digitalocean.com/community/tutorials/how-to-configure-haproxy-logging-with-rsyslog-on-centos-8-quickstart  

Use Kubernetes ingress NGINX, reason is Rancher default use it.  
```sh
kubectl apply -f https://raw.githubusercontent.com/kubernetes/ingress-nginx/controller-v0.46.0/deploy/static/provider/baremetal/deploy.yaml  
```
Need to define nodeport to specific port like 30080/30443, so that haproxy loadbalance can be configured.  

Try Nginx-ingress but not success

## Troubleshooting
**Error**: Flannel - CoreDns pods are stuck with status ContainerCreating  
Solution: delete /etc/cni/* (in my case, it is due to I installed Calico before)  

**Error**: nslookup Kubernetes.default -> connection timed out  
- Check fannel, kube-proxy, coredns logs
- In my case, it is Fannel default use the first interface enp0s3 which is a NAT interface, change interface to enp0s8 solve the issue.
- https://kubernetes.io/docs/tasks/administer-cluster/dns-debugging-resolution/
- https://kubernetes.io/docs/tasks/debug-application-cluster/debug-service/#is-the-kube-proxy-working
	
**Error**: Post "https://ingress-nginx-controller-admission.ingress-nginx.svc:443/networking/v1beta1/ingresses?timeout=10s": context deadline exceeded  
Solution:	kubectl delete -A ValidatingWebhookConfiguration ingress-nginx-admission

**Error**: kubectl get cs  
Encounter error: “controller-manager   Unhealthy   Get "http://127.0.0.1:10252/healthz": dial tcp 127.0.0.1:10252: connect: connection refused”  
sudo nano /etc/kubernetes/manifests/kube-controller-manager.yaml  
sudo nano /etc/kubernetes/manifests/kube-scheduler.yaml
Comment out - --port=0  

## Deploy
https://www.magalix.com/blog/deploying-an-application-on-kubernetes-from-a-to-z

## Monitor
## Logging
## Scalability
