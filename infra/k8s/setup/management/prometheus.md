helm repo add prometheus-community https://prometheus-community.github.io/helm-charts
helm show values prometheus-community/prometheus > prometheus-values.yaml
- Disabel persistenVolume
helm install prometheus prometheus-community/prometheus --values prometheus-values.yaml -n prometheus --create-namespace

Grafana
Add prometheus data source:   
http://prometheus-server.prometheus.svc.cluster.local

Import latest dashboard from grafana website (14205 is working)

https://medium.com/@chris_linguine/how-to-monitor-your-kubernetes-cluster-with-prometheus-and-grafana-2d5704187fc8