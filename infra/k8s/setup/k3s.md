**IMPORTANT**
Once decide the components (e.g. traefik, servicelb) during installation, it is not easy to put them back or remove. Suggest a clean setup.  

# Installation
At least 1CPU/2G  (for cloud, 2CPU/4G is minimum)  
AWS t2.micro (1CPU/1G) too small, encounter "TLS timeout error" when running "kubectl get nodes"
```sh
# Redhat
sudo systemctl disable firewalld --now  
sudo systemctl disable nm-cloud-setup.service nm-cloud-setup.timer  
sudo reboot  

curl -sfL https://get.k3s.io | sh -s - server  --write-kubeconfig-mode 0644
# to exclude traefik, servicelb
-- disable traefik
-- disable servicelb 
```
## Use MySQL as data store:  
```sh
curl -sfL https://get.k3s.io | sh -s - server 
--datastore-endpoint="mysql://hai:hai@tcp(192.168.10.10:3306)/k3s" --write-kubeconfig-mode 0644 --tls-san rancher.my.org --tls-san 192.168.10.10
(tls-san will be reflected in serving-kube-apiserver.crt)
```
## HA setup
run above command on another node.

## Disable Traefik  
https://www.suse.com/c/rancher_blog/deploy-an-ingress-controller-on-k3s/#:~:text=Deploying%20K3s%20with%20Ambassador,Traefik%20as%20an%20ingress%20controller.   
```sh
kubectl -n kube-system delete helmcharts.helm.cattle.io traefik
sudo service k3s stop
sudo nano /etc/systemd/system/k3s.service
# add --disable traefik
sudo systemctl daemon-reload
sudo rm /var/lib/rancher/k3s/server/manifests/traefik.yaml
sudo service k3s start
```

## Disable Load Balancer
https://rancher.com/docs/k3s/latest/en/networking/  
```sh 
k delete  daemonset.apps/svclb-traefik -n kube-system
```

## Use NGINX Ingress:  
By default, K3s uses Traefik as the ingress controller for your cluster. The decision to use Traefik over NGINX was based on multi-architecture support across x86 and ARM based platforms. Normally Traefik meets the needs of most Kubernetes clusters.  
```sh
curl -sfL https://get.k3s.io | INSTALL_K3S_EXEC="--no-deploy traefik" sh -s - server  --write-kubeconfig-mode 0644

# install NGINX ingress
helm repo add ingress-nginx https://kubernetes.github.io/ingress-nginx
helm repo update
helm install ingress-nginx ingress-nginx/ingress-nginx
```
## Use Ambassador Ingress:
https://www.suse.com/c/rancher_blog/deploy-an-ingress-controller-on-k3s/  
**Note**: The version used for Ambassador is outdated. Use Ambassador official instead:  
https://www.getambassador.io/docs/edge-stack/latest/tutorials/getting-started/  

## Run as Docker using Docker Compose
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

# Operating
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
kubectl config view --raw >~/.kube/config
```

## Private docker image registry
https://rancher.com/docs/k3s/latest/en/installation/private-registry/  
```sh
sudo nano /etc/rancher/k3s/registries.yaml
sudo systemctl restart k3s
```
```yml
mirrors:
  harbor.home:9443:
    endpoint:
      - "https://harbor.home:9443"
configs:
  "harbor.home:9443":
    auth:
      username: admin # this is the registry username
      password: admin # this is the registry password
    tls:
      cert_file: /etc/docker/certs.d/harbor.home:9443/harbor.home.cert # path to the cert file used in the registry
      key_file:  /etc/docker/certs.d/harbor.home:9443/harbor.home.key  # path to the key file used in the registry
      ca_file:   /etc/docker/certs.d/harbor.home:9443/ca.crt           # path to the ca file used in the registry	  
```