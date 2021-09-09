## Tanzu Kubernetes Grid Instance (e.g. for different environments) includes:  
- Management Cluster
- Tanzu Kubernetes Clusters  
  Created from Tanzu Kubernetes Cluster Plan
- Shared and In-Cluster Services

## Installation
Bootstrap Environment (2CPU/6G)  
Tanzu Kubernetes Grid Installer  
https://docs.vmware.com/en/VMware-Tanzu-Kubernetes-Grid/1.3/vmware-tanzu-kubernetes-grid-13/GUID-install-cli.html  
Management Cluster  
https://docs.vmware.com/en/VMware-Tanzu-Kubernetes-Grid/1.3/vmware-tanzu-kubernetes-grid-13/GUID-mgmt-clusters-deploy-management-clusters.html  
```sh
tanzu management-cluster create --ui  
```
Tanzu Kubernetes Clusters  
