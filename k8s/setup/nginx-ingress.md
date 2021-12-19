## Overview  
https://kubernetes.io/docs/concepts/services-networking/ingress/  
Url Rewrite  
https://github.com/kubernetes/ingress-nginx/blob/main/docs/examples/rewrite/README.md  
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
echoserver.yaml
```yaml
apiVersion: v1
kind: Namespace
metadata:
  name: echoserver
---
apiVersion: apps/v1
kind: Deployment
metadata:
  name: echoserver
  namespace: echoserver
spec:
  replicas: 1
  selector:
    matchLabels:
      app: echoserver
  template:
    metadata:
      labels:
        app: echoserver
    spec:
      containers:
      - image: k8s.gcr.io/echoserver:1.4
        imagePullPolicy: Always
        name: echoserver
        ports:
        - containerPort: 8080
---
apiVersion: v1
kind: Service
metadata:
  name: echoserver
  namespace: echoserver
spec:
  ports:
  - port: 80
    targetPort: 8080
    protocol: TCP
  selector:
    app: echoserver
---
apiVersion: networking.k8s.io/v1
kind: Ingress
metadata:
  name: echoserver
  namespace: echoserver
  annotations:
    nginx.ingress.kubernetes.io/rewrite-target: /$2
spec:
  ingressClassName: "nginx"
  rules:
  - host:
    http:
      paths:
      - path: /echo(/|$)(.*)
        pathType: "Prefix"
        backend:
          service:
            name: echoserver
            port:
              number: 80

```