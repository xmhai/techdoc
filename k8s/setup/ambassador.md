## Concept
- ambassador running on port 8080/8443, so the NodePort/LoadBalancer need to point to this port.  
- Configure Listener and Host (for HTTPS) after installation.

## Installation
V2.1.1 + k3s (1.22.5) + Centos 7  
https://www.getambassador.io/docs/edge-stack/latest/topics/install/yaml-install/  

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

- Loadbalancer  
  https://www.getambassador.io/docs/edge-stack/latest/topics/install/bare-metal/  

- Configuration  
https://www.getambassador.io/docs/edge-stack/latest/howtos/configure-communications/  
[Sample HTTP configuration](https://github.com/xmhai/PersonalFinanceAssistant/blob/master/k8s/k8s-prod/ingress-service-ambassador.yaml)

## Troubleshooting
- Error: Loadbalancer Pending
  https://serverfault.com/questions/1064313/ambassador-service-stays-pending  
  https://stackoverflow.com/questions/67637854/ambassador-service-stays-pending  


