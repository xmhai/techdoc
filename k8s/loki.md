## Promtail
https://grafana.com/docs/loki/latest/clients/promtail/
- Discovers targets
- Attaches labels to log streams  
  During service discovery, metadata is determined (pod name, filename, etc.) that may be attached to the log line as a label for easier identification.
- Pushes them to the Loki instance

## Installation
Loki recommended installation is on k8s.

Export the helm and change the values:
- helm show values grafana/loki-stack > loki-stack-values.yaml
- Enable loki persistence (for production)

helm install loki grafana/loki-stack --values loki-stack-values.yaml -n loki --create-namespace

helm list -n loki
helm list -A

kubectl -n loki get all

?? for statefulset, how the pvc is assigned to a pod, will it be reused???
