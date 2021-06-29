## Installation
- **Normal Install**:  
  At least 1CPU/2G  (for cloud, 2CPU/4G is minimum)  
  AWS t2.micro (1CPU/1G) too small, encounter "TLS timeout error" when running "kubectl get nodes"
  ```sh
  # Redhat
  sudo systemctl disable firewalld --now  
  sudo systemctl disable nm-cloud-setup.service nm-cloud-setup.timer  
  sudo reboot  

  curl -sfL https://get.k3s.io | sh -s - server  --write-kubeconfig-mode 0644
  ```
- Use NGINX Ingress:  
  curl -sfL https://get.k3s.io | INSTALL_K3S_EXEC="--no-deploy traefik" sh -s - server  --write-kubeconfig-mode 0644
- Use MySQL as data store:  
  curl -sfL https://get.k3s.io | sh -s - server 
  --datastore-endpoint="mysql://hai:hai@tcp(192.168.10.10:3306)/k3s" --write-kubeconfig-mode 0644 --tls-san rancher.my.org --tls-san 192.168.10.10
  (tls-san will be reflected in serving-kube-apiserver.crt)

- For HA setup, just run above command on another node.
- By default, K3s uses Traefik as the ingress controller for your cluster. The decision to use Traefik over NGINX was based on multi-architecture support across x86 and ARM based platforms. Normally Traefik meets the needs of most Kubernetes clusters.

## Install NGINX ingress
```sh
helm repo add ingress-nginx https://kubernetes.github.io/ingress-nginx
helm repo update
helm install ingress-nginx ingress-nginx/ingress-nginx
```

## Uninstall
sudo systemctl stop k3s  
/usr/local/bin/k3s-uninstall.sh  
drop MySQL table!!!