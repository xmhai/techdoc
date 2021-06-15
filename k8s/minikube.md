## very hard to install on VirtualBox VM :(  
Finally make it run, what I have tried:
- sudo yum install -y conntrack
- sudo systemctl stop firewalld
- sudo systemctl disable firewalld
- sudo swapoff -a
- sudo nano /etc/hosts  
add hostname
- sudo nano /etc/docker/daemon.json  
  {  
  "exec-opts": ["native.cgroupdriver=systemd"]  
  }
- systemctl enable kubelet.service
- sudo nano /etc/systemd/system/kubelet.service.d/10-kubeadm.conf  
  Environment="KUBELET_SYSTEM_PODS_ARGS=--pod-manifest-path=/etc/kubernetes/manifests --allow-privileged=true --fail-swap-on=false"  
  Add KUBELET_SYSTEM_PODS_ARGS to:
  ExecStart=/usr/bin/kubelet $KUBELET_KUBECONFIG_ARGS ...
- systemctl daemon-reload
- systemctl restart kubelet
- minikube delete
- minikube start --alsologtostderr -v=8  
or add --vm-driver=none