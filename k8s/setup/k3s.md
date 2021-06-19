## Installation
- curl -sfL https://get.k3s.io | sh -s - server \
  --datastore-endpoint="mysql://hai:hai@tcp(192.168.10.10:3306)/k3s"
- Run "sudo chmod 644 /etc/rancher/k3s/k3s.yaml" after installation.
- For HA setup, just run above command on another node.
- Stop k3s:  
  sudo nano systemctl stop k3s
- By default, K3s uses Traefik as the ingress controller for your cluster. The decision to use Traefik over NGINX was based on multi-architecture support across x86 and ARM based platforms. Normally Traefik meets the needs of most Kubernetes clusters.