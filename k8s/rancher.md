## Rancher Features
- Create k8s cluster

https://www.rancher.cn/installing-rancher-single-container-high-availability

## Setup RKE
**Env Setup**  
Support Matrix  
https://rancher.com/support-maintenance-terms/all-supported-versions/rancher-v2.5.8/
- Rancher 2.5.8
- Docker: 20.10.6
- kubectl: 1.20.4
- RKE: 1.2.8
- RHEL: 8.2

Overall installation steps
https://rancher.com/docs/rancher/v2.x/en/installation/resources/k8s-tutorials/infrastructure-tutorials/ec2-node/

FOLLOW below link (best and detailed):  
https://computingforgeeks.com/install-kubernetes-production-cluster-using-rancher-rke/

RKE is a container-based installer, which means 
- it requires Docker to be installed on the remote server (Node), and it currently requires Docker version 1.12 to be installed on the servers. 
RKE works by connecting to each server via SSH and setting up a tunnel to the Docker socket on remote server (Node), which means 
- the SSH user must have access to the Docker engine on remote server (Node). To enable access to the SSH user, you can add this user to the Docker group:
usermod -aG docker
- Testing: Make sure the user can Jumphost ssh to all remote server and run docker ps command (this is the make sure docker is available at nodes)

**RKE (AzureVM: 1 x Jumphost, 1 x LoadBalancer, 3 x EC2 (RHEL 8.2)) SUCCESS**  
https://rancher.com/docs/rancher/v2.x/en/installation/resources/k8s-tutorials/infrastructure-tutorials/infra-for-ha/

**Rancher Server (Docker) + RKE (1 node CentOS 8.3) SUCCESS**
https://www.youtube.com/watch?v=SIv5DV1Jjuc&list=RDCMUC6VkhPuCCwR_kG0GExjoozg&index=26
- No need to open any ports on both server.
- Set rancher server name to ip address
- Choose Calio as network protocol
- Set both address and internal address

Even though rancher-agent shows below error, RKE is created successfully:  
time="2021-05-27T05:16:14Z" level=error msg="Failed to connect to proxy. Response status: 400 - 400 Bad Request. Response body: Operation cannot be fulfilled on nodes.management.cattle.io \"m-421aa90e079f\": the object has been modified; please apply your changes to the latest version and try again" error="websocket: bad handshake"
time="2021-05-27T05:16:14Z" level=error msg="Remotedialer proxy error" error="websocket: bad handshake"


**Rancher Server (RKE) + RKE (1 node CentOS 8.3) Failed**
- Can only access Rancher UI by hostname (if access by ip address, will return 404 error, also when register node, rancher agent will return error: https://192.168.10.110/ping is not accessible (The requested URL returned error: 404))
- Need to set rancher server name as the name in "helm ... -- set hostname=name"

---
**Linux Setup**  
- sudo without password  
-- Ubuntu --  
sudo visudo  
-- RHEL --  
sudo nano /etc/sudoers.d/hai  
add:  hai     ALL=(ALL) NOPASSWD:ALL  
  
- Create ssh key  
https://help.ubuntu.com/community/SSH/OpenSSH/Keys#:~:text=ssh%2Fid_rsa.,pub.  
For setup in Azure VM, don't need this step since azureuser is created by default.
```sh 
mkdir ~/.ssh  
chmod 700 ~/.ssh  
ssh-keygen -t rsa (or ssh-keygen -t rsa -b 4096)  
ssh-copy-id hai@<host>  
```
- Disable selinux (RHEL)  
sudo nano /etc/selinux/config  

**Install kubectl (Jumphost)**  
https://kubernetes.io/docs/tasks/tools/install-kubectl-linux/

```sh 
curl -LO https://dl.k8s.io/release/v1.20.4/bin/linux/amd64/kubectl  
chmod +x ./kubectl  
sudo mv ./kubectl /usr/local/bin/kubectl  
kubectl version --client  
```

**Install RKE (Jumphost)**  
https://rancher.com/docs/rke/latest/en/installation/

```sh 
curl -LO https://github.com/rancher/rke/releases/download/v1.2.8/rke_linux-amd64  
chmod +x rke_linux-amd64  
sudo mv rke_linux-amd64 /usr/local/bin/rke  
rke --version  
```

**Install HELM (Jumphost)**  
```sh 
curl -fsSL -o get_helm.sh https://raw.githubusercontent.com/helm/helm/master/scripts/get-helm-3  
chmod 700 get_helm.sh  
./get_helm.sh  
```

**Create the cluster configuration file cluster.yml (Jumphost)**  
https://rancher.com/docs/rancher/v2.x/en/installation/resources/k8s-tutorials/ha-rke/  
(Refer to the end of document)

**Enable SSH to nodes (Jumphost)**  
-- Cloud VM --  
cd ~/.ssh  
sudo nano id_rsa  
Copy private file (*.pem) to ~/.ssh/id_rsa  

**Install Docker (All Nodes)**  
https://docs.docker.com/engine/install/centos/  
https://docs.docker.com/engine/install/linux-postinstall/  

-- Ubuntu --  
DON'T use ubuntu snap docker. If installed, run "sudo snap remove docker" to remove it before installation.  

https://docs.docker.com/engine/install/ubuntu/ 
```sh 
curl https://releases.rancher.com/install-docker/20.10.sh | sudo bash -  
or  
curl -fsSL https://download.docker.com/linux/ubuntu/gpg | sudo gpg --dearmor -o /usr/share/keyrings/docker-archive-keyring.gpg  
echo \
  "deb [arch=amd64 signed-by=/usr/share/keyrings/docker-archive-keyring.gpg] https://download.docker.com/linux/ubuntu \
  $(lsb_release -cs) stable" | sudo tee /etc/apt/sources.list.d/docker.list > /dev/null  
sudo apt-get update  
sudo apt-get install docker-ce docker-ce-cli containerd.io  
```
-- RHEL --  
```sh
sudo dnf config-manager --add-repo https://download.docker.com/linux/centos/docker-ce.repo  
sudo dnf install docker-ce-20.10.6 docker-ce-cli-20.10.6 containerd.io --allowerasing  

sudo systemctl start docker  
sudo systemctl enable --now docker  
sudo usermod -aG docker $USER  
newgrp docker
sudo chmod 666 /var/run/docker.sock
```
**Open the ports  (All Nodes)**  
https://rancher.com/docs/rancher/v2.x/en/installation/requirements/ports/#ports-for-rancher-server-nodes-on-rke  
Remember to enable ports in Cloud network security group

**Create the cluster  (Jumphost)**  
rke up

**Configure kubectl (Jumphost)**  
```sh
mkdir .kube
cp kube_config_cluster.yml .kube/config
kubectl get nodes
```

**DONE!!!**  
Modify & Clean up
https://www.rancher.cn/an-introduction-to-rke/

## Install Rancher on RKE
https://rancher.com/docs/rancher/v2.x/en/installation/install-rancher-on-k8s/

-Use stable version  
```sh
helm repo add rancher-stable https://releases.rancher.com/server-charts/stable

kubectl create namespace cattle-system  

kubectl create namespace cert-manager  
kubectl apply --validate=false -f https://github.com/jetstack/cert-manager/releases/download/v1.0.4/cert-manager.crds.yaml  
helm repo add jetstack https://charts.jetstack.io  
helm repo update  
helm install cert-manager jetstack/cert-manager --namespace cert-manager --version v1.0.4  
#Check cert-manager status, make sure 3 pods are running  
kubectl get pods --namespace cert-manager

#Set to 1 instance for one node  
#Set hostname for the load balancer dns name.  
helm install rancher rancher-stable/rancher --namespace cattle-system --set hostname=rancher.my.org --set replicas=1
```
-Check rancher status, make sure deployment successfully rollout  
```sh
kubectl -n cattle-system rollout status deploy/rancher
```

-Create Cluster from Rancher Server UI  
Remember to set below option when running rancher agent on the node:  
 --address 10.0.2.15 --internal-address 192.168.56.102

**Deploy Workload**  
For testing, use "HostPort". If select "NodePort", the service is not created, seems have to manually create yaml file and apply.

## Uninstall Rancher
https://www.rancher.co.jp/docs/rancher/v2.x/en/system-tools/#remove
(However it is not working, the namespace will be in terminating status forever:(

Create Cluster from Rancher Server
https://www.linuxsysadmins.com/setup-kubernetes-cluster-with-rancher/

## Troubleshooting

Error: Failed to apply the ServiceAccount needed for job execution: Post "https://20.198.168.250:6443/apis/rbac.authorization.k8s.io/v1/clusterrolebindings?timeout=30s": context deadline exceeded (Client.Timeout exceeded while awaiting headers)
Cause: Firewall not open

---
**cluster.yml**  
sudo nano cluster.yml
```yaml
nodes:
  - address: 10.2.0.4  
    user: azureuser  
    role: [controlplane, worker, etcd]  

services:  
  etcd:  
    snapshot: true  
    creation: 6h  
    retention: 24h  

# Required for external TLS termination with  
# ingress-nginx v0.22+  
ingress:  
  provider: nginx  
  options:  
    use-forwarded-headers: "true"  
```
**openport.sh**  
nano openport.sh
```sh
#!/bin/bash  
for i in 22 80 443 179 5473 6443 8472 2376 8472 2379-2380 9099  10250 10251 10252 10254 30000-32767; do
    sudo firewall-cmd --add-port=${i}/tcp --permanent
done
for i in 8285 8472 4789 30000-32767; do
   sudo firewall-cmd --add-port=${i}/udp --permanent
done
sudo systemctl disable firewalld
sudo firewall-cmd --reload
```
chmod +x openport.sh  
./openport.sh

