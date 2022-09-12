- Pre-requisite: AKS cluster enabled with Azure AD integration.  
  https://docs.microsoft.com/en-us/azure/aks/managed-aad  
- Namespace access control  
  https://docs.microsoft.com/en-us/azure/aks/azure-ad-rbac  

Step by Step
```sh
# Get current user AD groups
az ad user get-member-groups --id iris.liu@YCH.COM

# Find out group members
az ad group member list --group ffbd50b1-3714-4e77-a22a-410b6c0da22f

# Enable AKS AD integration
# All users in this group will be cluster admin
az aks update -g Y3TECH-SAAS-NGAPPS -n cluster-tls-demo --enable-aad --aad-admin-group-object-ids ffbd50b1-3714-4e77-a22a-410b6c0da22f

# Get .config
az aks get-credentials --resource-group Y3TECH-SAAS-NGAPPS --name cluster-tls-demo

Y3 Product Development
ffbd50b1-3714-4e77-a22a-410b6c0da22f
Y3_DevOps
fe48699d-e1c7-42f1-9b8d-82c69850cb41

# Enable RBAC for other groups
kind: ClusterRoleBinding
apiVersion: rbac.authorization.k8s.io/v1
metadata:
  name: devops-full-access
roleRef:
  apiGroup: rbac.authorization.k8s.io
  kind: ClusterRole
  name: cluster-admin
subjects:
- kind: Group
  name: fe48699d-e1c7-42f1-9b8d-82c69850cb41
```