## Promtail
https://grafana.com/docs/loki/latest/clients/promtail/
- Discovers targets
- Attaches labels to log streams  
  During service discovery, metadata is determined (pod name, filename, etc.) that may be attached to the log line as a label for easier identification.
- Pushes them to the Loki instance

## Installation
Export the helm and change the values:
- helm show values grafana/loki-stack > loki-stack-values.yaml
- Enable loki persistence

helm install loki grafana/loki-stack --values loki-stack-values.yaml -n loki --create-namespace

helm list -n loki
helm list -A

kubectl -n loki get all

?? for statefulset, how the pvc is assigned to a pod, will it be reused???

## Access Grafana
### Option 1  
Use port forward to access grafana:
kubectl -n loki port-forward svc/loki-grafana 3000:80  
NOTE: port forwading only works from local machine, which means you cannot remote access the port-forward machine.

Visit localhost:3000  
- Username is admin
- To find out password, run below command:  
kubectl -n loki get secrets  
kubectl -n loki get secret **loki-grafana** -o yaml  
echo "*password_base64_encoded*" | base64 -d; echo

### Option 2  
Setup ingress (Refer to k8s project grafana-ingress.yaml)