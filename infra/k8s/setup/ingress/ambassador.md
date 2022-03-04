## Concept
- ambassador running on port 8080/8443, so the NodePort/LoadBalancer need to point to this port.  
- Configure Listener and Host (for HTTPS) after installation.
- For EdgeStack v1.xx, only need to define the Mapping and Host.

## Installation
V2.1.1 + k3s (1.22.5) + Centos 7  
Emissary is new version for Ambassador and is OpenSource, while Edge-stack is more for enterprise which has more features.  
https://www.getambassador.io/docs/emissary/latest/topics/install/yaml-install/  

For old version of kubernetes (1.20.x):  
https://www.suse.com/c/rancher_blog/deploy-an-ingress-controller-on-k3s/#:~:text=Deploying%20K3s%20with%20Ambassador,Traefik%20as%20an%20ingress%20controller.  

```sh
wget https://app.getambassador.io/yaml/edge-stack/2.1.1/aes-crds.yaml
wget https://app.getambassador.io/yaml/edge-stack/2.1.1/aes.yaml
nano aes
# Change LoadBalancer to NodePort
# Add HTTP nodePort: 30080
# Add HTTPS nodePort: 30443
kubectl create namespace ambassador
kubectl apply -f aes-crds.yaml
kubectl apply -f aes.yaml
kubectl -n ambassador wait --for condition=available --timeout=90s deploy edge-stack
```

- TLS  
  https://www.getambassador.io/docs/edge-stack/latest/howtos/tls-termination/  

- Loadbalancer  
  https://www.getambassador.io/docs/edge-stack/latest/topics/install/bare-metal/  

- Configuration  
https://www.getambassador.io/docs/edge-stack/latest/howtos/configure-communications/  
Sample [configuration](https://github.com/xmhai/PersonalFinanceAssistant/blob/master/k8s/k8s-prod/ingress-service-ambassador.yaml)

## Troubleshooting
https://www.getambassador.io/docs/edge-stack/latest/topics/running/debugging/  
- Error: Loadbalancer Pending
  https://serverfault.com/questions/1064313/ambassador-service-stays-pending  
  https://stackoverflow.com/questions/67637854/ambassador-service-stays-pending  
- Error: Unhealthy upstream  
  change the service from service: <service.name> to service: <service.name>.<namespace>
- Error: upstream request timeout  
  Encounter this error and cannot solve it, so revert to k3s bundled traefik.

## Appendix: Sample Configuration
```yaml
apiVersion: getambassador.io/v3alpha1
kind: Listener
metadata:
  name: http-listener
  namespace: ambassador
spec:
  port: 8080
  protocol: HTTP
  securityModel: INSECURE
  hostBinding:
    namespace:
      from: ALL
---
apiVersion: getambassador.io/v3alpha1
kind: Listener
metadata:
  name: https-listener
  namespace: ambassador
spec:
  port: 8443
  protocol: HTTPS
  securityModel: XFP
  hostBinding:
    namespace:
      from: ALL
---
apiVersion: getambassador.io/v3alpha1
kind: Host
metadata:
  name: srv-host
spec:
  hostname: "*"
  requestPolicy:
    insecure:
      action: Route
  acmeProvider:
    authority: none
  tlsSecret:
    name: tls-cert
  selector:
    matchLabels:
      hostname: srv-host
---
apiVersion: getambassador.io/v3alpha1
kind: Mapping
metadata:
  name: common-service-mapping
spec:
  hostname: "*"
  prefix: /api/common/
  service: common-service-cluster-ip-service:8080
---
apiVersion: getambassador.io/v3alpha1
kind: Mapping
metadata:
  name: dashboard-mapping
spec:
  hostname: "*"
  prefix: /dashboard/
  service: https://kubernetes-dashboard.kubernetes-dashboard:443
```