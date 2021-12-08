## Overview  
https://kubernetes.io/docs/concepts/services-networking/ingress/  

## Install
Install Helm:  
https://helm.sh/docs/intro/install/  

Install NGINX ingress:  
https://kubernetes.github.io/ingress-nginx/deploy/  
```sh
helm repo add ingress-nginx https://kubernetes.github.io/ingress-nginx
helm repo update
helm install ingress-nginx ingress-nginx/ingress-nginx
```
## Appendix (Sample Ingress)
kubernetes-dashboard-ingress.yaml  
```yaml
apiVersion: networking.k8s.io/v1
kind: Ingress
metadata:
  name: kubernetes-dashboard-ingress
  namespace: kubernetes-dashboard
  annotations:
    kubernetes.io/ingress.class: "nginx"
spec:
  ingressClassName: "nginx"
  tls:
  - secretName: tls-secret # NON-EXISTENT
  rules:
  - host:
    http:
      paths:
      - path: /
        pathType: "Prefix"
        backend:
          service:
            name: kubernetes-dashboard
            port:
              number: 30001
```