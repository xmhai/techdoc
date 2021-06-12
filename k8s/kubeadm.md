## Setup
**Env**:  
- Cluster
  - VirtualBox VM (1cpu 2G, HostNetwork, NAT)
  - 1 x Master Node 
  - 2 x Worker Nodes
  - Better to configure HostNetwork as first network adaptor as CNI plug in will use first adaptor by default
- CentOS 8.3 + Kubernetes 1.21.1 + Fannel + ingress-nginx + haproxy  

Follow instruction:  
https://github.com/justmeandopensource/kubernetes/blob/master/docs/install-cluster-centos-7.md  
https://upcloud.com/community/tutorials/install-kubernetes-cluster-centos-8/  
Extra steps
-	Ignore the version in the instruction when install docker/kubeadm/kubectl/kubelet
-	kubeadm init --ignore-preflight-errors=NumCPU --apiserver-advertise-address=192.168.56.105 --pod-network-cidr=10.244.0.0/16
-	*Might need to set CentOS 8 to use iptables*  
```sh
# revert back to iptables (CentOS 8)
sudo nano /etc/firewalld/firewalld.conf
sudo systemctl enable firewalld
sudo service firewalld restart
# check
iptables-save | grep <hostnames>
```
-	Install Fannel network plugin before join (use Flannel, tried Calico but not success maybe due to it only support CentOS 7)  
**(Fannel)**  
curl -LO https://raw.githubusercontent.com/coreos/flannel/master/Documentation/kube-flannel.yml  
Add - --iface=enp0s8  
https://stackoverflow.com/questions/47845739/configuring-flannel-to-use-a-non-default-interface-in-kubernetes  
kubectl apply -f kube-flannel.yml  
**(Cannel)**  
canal_iface: ""  
https://stackoverflow.com/questions/46284335/kubernetes-cant-reach-other-node-services  
-	kubectl get cs  
Encounter error: “controller-manager   Unhealthy   Get "http://127.0.0.1:10252/healthz": dial tcp 127.0.0.1:10252: connect: connection refused”  
sudo nano /etc/kubernetes/manifests/kube-controller-manager.yaml  
sudo nano /etc/kubernetes/manifests/kube-scheduler.yaml
Comment out - --port=0  

## Uninstall  
kubeadm reset  
sudo rm /etc/cni/net.d  

## Ingress
Setup haproxy and Kubernetes Ingress Controller (on Master Node)  
https://jhooq.com/ingress-controller-nginx/
https://www.digitalocean.com/community/tutorials/how-to-configure-haproxy-logging-with-rsyslog-on-centos-8-quickstart  

Use Kubernetes ingress NGINX, reason is Rancher default use it. Try Nginx-ingress but not success.  
kubectl apply -f https://raw.githubusercontent.com/kubernetes/ingress-nginx/controller-v0.46.0/deploy/static/provider/baremetal/deploy.yaml  

Need to define nodeport to specific port like 30080/30443, so that haproxy loadbalance can be configured.  

## Troubleshooting
**Error**: pod in status of “CrashLoopBackOff”  
Diagnosis: run below command to view error message  
- kubectl describe -n nginx-ingress pod <pod_name>
- Find the pod running on which node, and go to the node run “docker ps -a” to get failed container
-	Docker logs <container_id>

**Error**: Flannel - CoreDns pods are stuck with status ContainerCreating  
Solution: delete /etc/cni/* (in my case, it is due to I installed Calico before)  

**Error**: nslookup Kubernetes.default -> connection timed out  
- Check fannel, kube-proxy, coredns logs
- In my case, it is Fannel default use the first interface enp0s3 which is a NAT interface, change interface to enp0s8 solve the issue.
- https://kubernetes.io/docs/tasks/administer-cluster/dns-debugging-resolution/
- https://kubernetes.io/docs/tasks/debug-application-cluster/debug-service/#is-the-kube-proxy-working
	
**Error**: Post "https://ingress-nginx-controller-admission.ingress-nginx.svc:443/networking/v1beta1/ingresses?timeout=10s": context deadline exceeded  
Solution:	kubectl delete -A ValidatingWebhookConfiguration ingress-nginx-admission

## Deploy
https://www.magalix.com/blog/deploying-an-application-on-kubernetes-from-a-to-z

## Monitor
## Logging
## Scalability
