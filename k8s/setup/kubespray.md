## Overview
https://github.com/kubernetes-sigs/kubespray

## Installation
https://www.redhat.com/sysadmin/kubespray-deploy-kubernetes  

https://computingforgeeks.com/deploy-kubernetes-cluster-centos-kubespray/  

https://medium.com/@sarangrana/getting-started-with-kubernetes-part-3-kubespray-on-premise-installation-guide-90194f08be4e  

https://shiwanibiradar.medium.com/kubespray-726093984e11

## Uninstall
Run reset.yml playbook  

## Access
kubeadm generate the kube config file under /etc/kubernetes/admin.conf, so need to copy the file to home .kube  
```sh
mkdir .kube
cd .kube
sudo cp /etc/kubernetes/admin.conf config
sudo chown $USER config
sudo chgrp $USER config
```
