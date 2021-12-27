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
- Use MySQL as data store:  
  ```sh
  curl -sfL https://get.k3s.io | sh -s - server 
  --datastore-endpoint="mysql://hai:hai@tcp(192.168.10.10:3306)/k3s" --write-kubeconfig-mode 0644 --tls-san rancher.my.org --tls-san 192.168.10.10
  (tls-san will be reflected in serving-kube-apiserver.crt)
  ```
- Use NGINX Ingress:  
  By default, K3s uses Traefik as the ingress controller for your cluster. The decision to use Traefik over NGINX was based on multi-architecture support across x86 and ARM based platforms. Normally Traefik meets the needs of most Kubernetes clusters.  
  ```sh
  curl -sfL https://get.k3s.io | INSTALL_K3S_EXEC="--no-deploy traefik" sh -s - server  --write-kubeconfig-mode 0644

  # install NGINX ingress
  helm repo add ingress-nginx https://kubernetes.github.io/ingress-nginx
  helm repo update
  helm install ingress-nginx ingress-nginx/ingress-nginx
  ```
- For HA setup, just run above command on another node.

- **Run as Docker using Docker Compose**  
```sh
md k3s
# create docker-compose.yaml with content from https://github.com/k3s-io/k3s/blob/master/docker-compose.yml  
set K3S_TOKEN="K3S_TOKEN"  
docker-compose up
set KUBECONFIG="kubeconfig.yaml"
kubectl get nodes
# Test deploy Nginx
kubectl create deployment --image=nginx nginx
# Expose the service: https://k3d.io/usage/guides/exposing_services/
# Use port forward to expose to host
# get pod name: kubectl get pods
kubectl port-forward pod/<podname> 8080:80
```


## Uninstall
sudo systemctl stop k3s  
/usr/local/bin/k3s-uninstall.sh  
drop MySQL table!!!

## Run containrd
K3s includes and defaults to containerd
```sh
$ sudo chmod 777 /var/lib/rancher/k3s/ -R
$ sudo chmod 777 /run/k3s/containerd/ -R
$ k3s crictl ps
```

## Use K3S
```sh
# Add alias to
nano ~/.bashrc
alias k='k3s kubectl'
alias crictl='k3s crictl'
```
