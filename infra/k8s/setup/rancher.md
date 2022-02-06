## Installation
https://rancher.com/docs/rancher/v2.x/en/installation/install-rancher-on-k8s/

**Install HELM (Jumphost)**  
```sh 
curl -fsSL -o get_helm.sh https://raw.githubusercontent.com/helm/helm/master/scripts/get-helm-3  
chmod 700 get_helm.sh  
./get_helm.sh  
```
**Install Rancher**  
*Step 1: Preparation*
```sh
# use stable version
helm repo add rancher-stable https://releases.rancher.com/server-charts/stable
kubectl create namespace cattle-system  
```
*Step 2: Install Cert*  
*Option 1 - Cert-manager*
``` sh
kubectl create namespace cert-manager  
kubectl apply --validate=false -f https://github.com/jetstack/cert-manager/releases/download/v1.0.4/cert-manager.crds.yaml  
helm repo add jetstack https://charts.jetstack.io  
helm repo update  
helm install cert-manager jetstack/cert-manager --namespace cert-manager --version v1.0.4 --kubeconfig /etc/rancher/k3s/k3s.yaml 
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
*Step 3: Install Rancher*
``` sh
# set to 1 instance for one node  
# hostname.  
# - for rancher cluster, load balancer dns name
# - for on-premise, any valid domain name
# - for cloud, private IPv4 DNS
helm install rancher rancher-stable/rancher --namespace cattle-system --set hostname=rancher.my.org --set replicas=1  --kubeconfig /etc/rancher/k3s/k3s.yaml  (...other settings???)
# check rancher status, make sure deployment successfully rollout (it takes time, wait for a few minutes)  
kubectl -n cattle-system rollout status deploy/rancher

# access Rancher UI using Chrome (Opera report error "Network Request Failed")
```

## Uninstall Rancher
https://www.rancher.co.jp/docs/rancher/v2.x/en/system-tools/#remove
(However it is not working, the namespace will be in terminating status forever:(

---
## (Optional for Reinstall Cluster) Clean Node
Clean docker on the host (https://rancher.com/docs/rancher/v2.5/en/cluster-admin/cleaning-cluster-nodes/)  
Reboot the host  
Clean folders on the host
Check processes (shouldn't be any other than system)  
Check containers (shouldn't be any)  
Check rancher/rancher log so it doesn't log any tls: bad certificate  
Create custom cluster  
Add node using docker run  
If it doesn't work, post rancher/rancher logging from start to end.  

## Create Cluster
https://www.linuxsysadmins.com/setup-kubernetes-cluster-with-rancher/
- Disable firewall on nodes.
- Disable selinux on nodes. 
- Install Docker.
- Add rancher loadbalancer name to /etc/host.
- Make sure Rancher server can ssh to the nodes.
- Add cluster from Rancher UI.  
  Remember to set below option in UI when running rancher agent on the node with 2 network interfaces:  
 --address 10.0.2.15 --internal-address 192.168.56.102

## Enable Monitoring
2 (or 4???) CPU is needed for Rancher Prometheus deployment.  

## Enable Logging
https://rancher.com/docs/rancher/v2.x/en/logging/v2.5/  
Ranch UI->App&Market->Logging

**Make sure** the Racher/RKE server can access the ElasticSearch Server

The UI is buggy!!! To use Cluster Output in Flows, select namespace as "cattle-logging-system" first, choose the cluster output, then switch back to the desired namespace.

Update:  
https://banzaicloud.com/docs/one-eye/logging-operator/  
https://banzaicloud.com/docs/one-eye/logging-operator/configuration/plugins/  
https://github.com/uken/fluent-plugin-elasticsearch/tree/v4.3.3#hosts  
To setup 3 nodes, use "Edit Yaml":
```yaml
  elasticsearch:
    host: 20.40.82.80
    hosts: 20.40.82.80,20.40.80.201
    port: 9200
    scheme: http
```
**Another option**:  
Change to old version logging which installed from menu Tools->Logging, it works.

**Test**:  
Deploy "chentex/random-logger" to default namespace and the logs comes in.

## Deploy Workload  
Select "NodePort", specfiy the container port.

---
## Troubleshooting
check rancher/rancher log

**Error**: Rancher Cluster Explorer page shows "500 error" page newly created RKE cluster   
**Diagnosis**:  
```sh
# check pod cattle-cluster-agent in status of “CrashLoopBackOff”
k describe pod cattle-cluster-agent-747c5b8bbb-gmvrl -n cattle-system
docker ps -a (find the error cattle-cluster-agent container)
docker logs 86f3bf662747
find error "Invalid numeric literal at line 1, column 10"
google "cattle-cluster-agent Invalid numeric literal at line 1, column 10", and find the solution.  
```
**cause**: you probably followed the tutorial and used the standard hostname rancher.my.org as it seems - that resolves to an existing public ip. it should resolve to the local rancher ip.   
https://github.com/rancher/rancher/issues/22063  
**Solution**: 
```sh
kubectl -n cattle-system patch  deployments cattle-cluster-agent --patch '{
    "spec": {
        "template": {
            "spec": {
                "hostAliases": [
                    {
                      "hostnames":
                      [
                        "rancher.my.org"
                      ],
                      "ip": "192.168.10.10"
                    }
                ]
            }
        }
    }
}'
```
or using Rancher UI
RKE->System->cattle-cluster-agent->Edit->Show Advanced Options->Networking->Add HostAlias

---
## Best Practice
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
