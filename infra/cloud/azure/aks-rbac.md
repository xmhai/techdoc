- Pre-requisite: AKS cluster enabled with Azure AD integration.  
  https://docs.microsoft.com/en-us/azure/aks/managed-aad  
- Namespace access control  
  https://docs.microsoft.com/en-us/azure/aks/azure-ad-rbac  

Step by Step
```sh
# Get current user AD groups
az ad user get-member-groups --id <xmhai@hotmail.com>

# Find out group members
az ad group member list --group <group-object-id>

# Enable AKS AD integration
# All users in this group will be cluster admin
az aks update -g <group-name> -n <cluster-name> --enable-aad --aad-admin-group-object-ids <admin-ad-group-object-id>

# Get .config
az aks get-credentials --resource-group <aks-resource-group-name> --name <cluster-name>

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
