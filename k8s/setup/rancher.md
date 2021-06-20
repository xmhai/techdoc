## Installation
https://rancher.com/docs/rancher/v2.x/en/installation/install-rancher-on-k8s/

**Install HELM (Jumphost)**  
```sh 
curl -fsSL -o get_helm.sh https://raw.githubusercontent.com/helm/helm/master/scripts/get-helm-3  
chmod 700 get_helm.sh  
./get_helm.sh  
```
**Install Rancher**  
Preparation
```sh
# use stable version
helm repo add rancher-stable https://releases.rancher.com/server-charts/stable
kubectl create namespace cattle-system  
```
Install Cert  
Option 1 - Cert-manager
``` sh
kubectl create namespace cert-manager  
kubectl apply --validate=false -f https://github.com/jetstack/cert-manager/releases/download/v1.0.4/cert-manager.crds.yaml  
helm repo add jetstack https://charts.jetstack.io  
helm repo update  
helm install cert-manager jetstack/cert-manager --namespace cert-manager --version v1.0.4 
# for k3s: add --kubeconfig /etc/rancher/k3s/k3s.yaml 
# check cert-manager status, make sure 3 pods are running  
kubectl get pods --namespace cert-manager
```
Option 2 - Bring in your own cert (for Layer-4 load balaner)
``` sh
# add option to helm install rancher
--set privateCA=true

kubectl -n cattle-system create secret tls tls-rancher-ingress --cert=tls.crt --key=tls.key

kubectl -n cattle-system create secret generic tls-ca --from-file=cacerts.pem
```
Option 3 - TLS Terminated at external Layer-7 load balaner  
NOTE: for test environment setup:  
- 1 vm run browser (set dns to load balancer)
- 1 vm run Nginx load balancer
- 1 vm run k3s + Rancher Server (set dns to load balancer)
https://forums.rancher.com/t/agent-certificate-chain-error-with-custom-ca-external-tls-termination/17960
``` sh
# create the tls-ca secret before running the install (with just the root CA cert, cert must contain SAN)
kubectl -n cattle-system create secret generic tls-ca --from-file=cacerts.pem

# add option to helm install rancher
--set tls=external --set privateCA=true

# The CA cert can be access from https://rancher.my.org/v3/settings/cacerts
```
Install Rancher
``` sh
# set to 1 instance for one node  
# set hostname for the load balancer dns name.  
helm install rancher rancher-stable/rancher --namespace cattle-system --set hostname=rancher.my.org --set replicas=1 (...other settings???)
# check rancher status, make sure deployment successfully rollout (it takes time, wait for a few minutes)  
kubectl -n cattle-system rollout status deploy/rancher
```
## Create Cluster
- Disable firewall on nodes
- Install Docker
- Add rancher loadbalancer name to /etc/host
- Add cluster from Rancher UI.  
  Remember to set below option when running rancher agent on the node:  
 --address 10.0.2.15 --internal-address 192.168.56.102

## Deploy Workload  
For testing, use "HostPort". If select "NodePort", the service is not created, seems have to manually create yaml file and apply.

## Uninstall Rancher
https://www.rancher.co.jp/docs/rancher/v2.x/en/system-tools/#remove
(However it is not working, the namespace will be in terminating status forever:(

Create Cluster from Rancher Server
https://www.linuxsysadmins.com/setup-kubernetes-cluster-with-rancher/

## Rancher Features
https://www.rancher.cn/installing-rancher-single-container-high-availability

**Deploy**  
- Load Balancer setup (Layer 4 or Layer 7)
- Rancher Server datastore backup (docker version can be back be MySql???)
- Prometheus stateful set, enable and how to set the PV???
- 

**Installation**  
Installing Rancher on top of Linux OS running docker is an easy way to deploy Rancher Server, but it does not come with a HA option, hence it is intended more for testing and demo purposes.   

For Rancher Server to be used in Production, installing on top of a RKE cluster is definitely recommended. In fact, Rancher Server can be first deployed as a 1 node RKE cluster, and then additional nodes can be added accordingly if HA is required subsequently.

**Cluser Creation**
For creating a cluster on prem, there are a few options:-  
- (Option a) Create a RKE cluster and then use Rancher Server to register/import it  
- (Option b) Create the infra nodes and then use Rancher Server to create a RKE cluster on top of these nodes  
- (Option c) Use Rancher Server to both provision the infra nodes and create a RKE cluster  

Option c would the most preferred way as it allows the full suite of features to be available to the managed cluster. However, this can only be done on selective infrastructure provider, such as vSphere and Openstack for on-prem cloud.  

Option b is generally intended for bare metal or virtual-machine deployment, but since the provisioning of the nodes is performed outside of Rancher server, some nodes related features such as node scaling is not available.  

Option a would have a limited suite of features, hence it should be the least preferred option.
Do check out the link below for a more details.  
https://rancher.com/docs/rancher/v2.x/en/cluster-provisioning/

## Rancher Server Name can be changed

## Air-gap environment need private docker repository

## Private chart repository

## Security
CIS
OPA Gatekeeper

## Backup
local -> Cluster Explorer -> App & Market -> Rancher Backup
Backup to local means???

## Longhorn
How it works?

## CICD using Fleet
