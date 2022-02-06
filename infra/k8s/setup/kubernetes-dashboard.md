## Overview
kubernetes.io/docs/tasks/access-application-cluster/web-ui-dashboard/  

## Install
- Container running on https port 8443 in default installatopm
- To be running on http port 9090, need to change the container argument  
vividcode.io/disable-authentication-and-https-in-kubernetes-dashboard/  
**remove** the argument --auto-generate-certificates  
**add** following extra arguments:  
--enable-skip-login  
--disable-settings-authorizer  
--enable-insecure-login  
--insecure-bind-address=0.0.0.0  
**modify**  followng sections:  
ports  
livenessProbe  
Sample yaml after modification:  
https://vividcode.io/content/insecure-kubernetes-dashboard.yml  

## Access
https://github.com/kubernetes/dashboard/blob/master/docs/user/accessing-dashboard/README.md  
- Create sample user and get the token.
  https://github.com/kubernetes/dashboard/blob/master/docs/user/access-control/creating-sample-user.md  
- Accessing dashboard UI
  - kubectl proxy / Port forward (not test yet)
  - NodePort (not test yet)
  - ingress-nginx
    refer to the files need to change in Appendix

## Appendix (ingress access)
- kubernetes-dashboard.yaml  
kubectl get deployment kubernetes-dashboard -n kubernetes-dashboard -o yaml > kubernetes-dashboard.yaml  
nano kubernetes-dashboard.yaml  

- kubernetes-dashboard-service.yaml  
kubectl get service kubernetes-dashboard -n kubernetes-dashboard -o yaml > kubernetes-dashboard-service.yaml  
nano kubenetes-dashboard-service.yaml
```yaml
apiVersion: v1
kind: Service
metadata:
  labels:
    k8s-app: kubernetes-dashboard
  name: kubernetes-dashboard
  namespace: kubernetes-dashboard
spec:
  clusterIP: 10.102.135.93
  clusterIPs:
  - 10.102.135.93
  internalTrafficPolicy: Cluster
  ipFamilies:
  - IPv4
  ipFamilyPolicy: SingleStack
  ports:
  - port: 80
    protocol: TCP
    targetPort: 9090
    nodePort: 30001
    name: dashboard-port
  selector:
    k8s-app: kubernetes-dashboard
  sessionAffinity: None
  type: NodePort
```
- ingress.yaml  
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
              name: dashboard-port
```    