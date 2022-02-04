## Installation
- Cannot bridge on wireless interface  

```sh
sudo nano /etc/qemu/bridge.conf
# add "allow all"

virt-install \
--virt-type=kvm \
--name centos7 \
--ram 8192 \
--vcpus=2 \
--os-variant=centos7.0 \
--location=iso/CentOS-7-x86_64-DVD-2009.iso \
--network=bridge=br0,model=virtio \
--extra-args='console=tty0 console=ttyS0,115200n8 serial' \
--graphics none \
--disk path=images/centos7.qcow2,size=20,bus=virtio,format=qcow2
```  

## Commands
```sh
 virsh list --all
 ```