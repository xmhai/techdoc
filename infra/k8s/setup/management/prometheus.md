## Installation
https://github.com/prometheus-community/helm-charts/tree/main/charts/kube-prometheus-stack  
https://medium.com/@chris_linguine/how-to-monitor-your-kubernetes-cluster-with-prometheus-and-grafana-2d5704187fc8
```sh
# install helm
wget https://get.helm.sh/helm-v3.8.0-linux-amd64.tar.gz
tar -zxvf helm-v3.8.0-linux-amd64.tar.gz
sudo mv linux-amd64/helm /usr/local/bin/helm

# set prometheus repo
helm repo add prometheus-community https://prometheus-community.github.io/helm-charts
helm repo update

# Customizing the Chart Before Installing
helm show values prometheus-community/kube-prometheus-stack > prometheus-values.yaml
# locate "Using tmpfs volume" and comment out the section
    ## Using tmpfs volume
    ##
    emptyDir:
      medium: Memory

# install prometheus
helm install prometheus prometheus-community/kube-prometheus-stack --values prometheus-values.yaml -n prometheus --create-namespace
```

## Access
http://srv:31000  
admin/prom-operator

Grafana
Add prometheus data source (k get svc | grep 9090):   
http://prometheus-kube-prometheus-prometheus:9090/ (new)
http://prometheus-server.prometheus.svc.cluster.local (with namespace)

Import latest dashboard from grafana website (14205 is working)

## Appendix A: Grafana NodePort Yaml
```yml
apiVersion: v1
kind: Service
metadata:
  annotations:
    meta.helm.sh/release-name: prometheus
    meta.helm.sh/release-namespace: default
  labels:
    app.kubernetes.io/instance: prometheus
    app.kubernetes.io/managed-by: Helm
    app.kubernetes.io/name: grafana
    app.kubernetes.io/version: 8.3.5
    helm.sh/chart: grafana-6.21.5
  name: prometheus-grafana
  namespace: default
spec:
  ports:
  - name: http-web
    port: 80
    protocol: TCP
    nodePort: 31000
  selector:
    app.kubernetes.io/instance: prometheus
    app.kubernetes.io/name: grafana
  type: NodePort
```