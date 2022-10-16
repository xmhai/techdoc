## Authn  
https://kubernetes.io/docs/reference/access-authn-authz/authentication/

- Normal User
  - Users are managed outside k8s.
- Service Account
  - For in-cluster processes to talk to API server.
  - Managed by k8s API, created by API server or through API call.
  - Bound to specific namespace.
  - Tied to secrets.
- Authn Strategy
  - Token
  - OIDC

## Authz
https://kubernetes.io/docs/reference/access-authn-authz/rbac/

## Network Policy
https://github.com/ahmetb/kubernetes-network-policy-recipes

## Security Context
- Pod
- Container
