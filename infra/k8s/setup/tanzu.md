## Tanzu Kubernetes Grid Instance (e.g. for different environments) includes:  
- Management Cluster
- Tanzu Kubernetes Clusters  
  Created from Tanzu Kubernetes Cluster Plan
- Shared and In-Cluster Services

## Installation
**Bootstrap Environment (2CPU/6G, with browser)**  
Tanzu Kubernetes Grid Installer  
https://docs.vmware.com/en/VMware-Tanzu-Kubernetes-Grid/1.3/vmware-tanzu-kubernetes-grid-13/GUID-install-cli.html  
```sh
# Download tanzu-cli-bundle-v1.3.1-linux-amd64.tar and kubectl-linux-v1.20.5-vmware.1.gz
mkdir tanzu
# scp/copy downloaded files to ~/tanzu
cd ~/tanzu
tar -xvf tanzu-cli-bundle-v1.3.1-linux-amd64.tar
cd cli
sudo install core/v1.3.1/tanzu-core-linux_amd64 /usr/local/bin/tanzu
tanzu version
tanzu plugin install --local cli all
tanzu plugin list

cd ~/tanzu
gzip -dk kubectl-linux-v1.20.5-vmware.1.gz
sudo install kubectl-linux-v1.20.5-vmware.1 /usr/local/bin/kubectl
kubectl version
```
**Preparation**  
https://docs.vmware.com/en/VMware-Tanzu-Kubernetes-Grid/1.3/vmware-tanzu-kubernetes-grid-13/GUID-mgmt-clusters-prepare-deployment.html  

**Install Management Cluster (aws)**  
https://docs.vmware.com/en/VMware-Tanzu-Kubernetes-Grid/1.3/vmware-tanzu-kubernetes-grid-13/GUID-mgmt-clusters-deploy-management-clusters.html  
```sh
tanzu management-cluster create --ui  
# Error: unable to ensure tkg BOM file: failed to download default bom files from the registry: failed to download the BOM file from image name 'projects.registry.vmware.com/tkg/tkg-bom:v1.3.1':Collecting images: %s: Working with projects.registry.vmware.com/tkg/tkg-bom:v1.3.1: Get "https://projects.registry.vmware.com/v2/": x509: certificate signed by unknown authority
```
---
**Conclustion:   
Not widely used by the community as it's not OpenSource,and the setup need vSphere or Azure/AWS**
