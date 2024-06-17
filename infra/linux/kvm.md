## Concept
- Cannot bridge on wireless interface  
- So if the host only has wireless interface, the other hosts in the LAN cannot access the VMs directly. port forward is the only way to directly access the VMs, which means VMs will NOT appear as a normal machine.  

## Installation
https://www.cyberciti.biz/faq/how-to-install-kvm-on-centos-7-rhel-7-headless-server/  
- Run all the command with **sudo**

- Create bridge network for full briding connection, where the guest devices are connected to the host LAN, without using NAT.  
https://linuxconfig.org/how-to-use-bridged-networking-with-libvirt-and-kvm  

```sh
# Create bridged-network
# Refer to https://linuxconfig.org/how-to-use-bridged-networking-with-libvirt-and-kvm
sudo virsh net-list
sudo virsh net-dumpxml default

sudo nano /etc/qemu-kvm/bridge.conf
# add "allow all"

sudo virt-install \
--virt-type=kvm \
--name kmaster \
--ram 4096 \
--vcpus=2 \
--os-variant=centos7.0 \
--location=/home/hai/iso/CentOS-7-x86_64-DVD-2009.iso \
--network network=bridged-network \
--graphics vnc \
--disk path=/home/hai/kvm/kmaster.qcow2,size=20,bus=virtio,format=qcow2

# Using macvtap
sudo virt-install \
--name=kvm1 \
--vcpus=2 \
--memory=4096 \
--cdrom=/home/hai/iso/CentOS-7-x86_64-DVD-2009.iso \
--disk path=/home/hai/kvm/kvm1.qcow2,size=20,bus=virtio,format=qcow2 \
--os-variant=centos7.0 \
--graphics vnc \
--network type=direct,source=wlp2s0,source_mode=bridge,model=virtio

# Find VNC server port (Exposed on Host, default 5900)
sudo virsh dumpxml kmaster | grep vnc

# From VNC Client machine (Windows in my case)
ssh hai@192.168.86.43 -L 5900:127.0.0.1:5900

# Install VNC
# run VNC with 127.0.0.1:5900
# This will bring up the installation wizard
Software Selection -> Infrastructure Server, select system adminstor tools
KDUMP -> Disable
NETWORK & HOSTNAME -> Enable eth0 (note down the ip addr), set hostname
Create User -> Make this user administrator
```  

## Clean up VM
```sh
# Get VM names
sudo virsh list --all
# Get source file name for a vm
sudo virsh dumpxml --domain <vm_name> | grep 'source file'
# Note down the guest storage file
sudo virsh shutdown <vm_name>
# (option) or force shutdown
sudo virsh destroy <vm_name>
# Delete VM
sudo virsh undefine <vm_name>
# (option) If error: Refusing to undefine while domain managed save image exists
sudo virsh managedsave-remove <vm_name>
# Remove source file
sudo rm -rf </home/hai/kvm/vm_source_file.qcow2>

# show bridge networks
brctl show
```

## Connect
- Connect via VNC
    ```sh
    # Enable VNC
    sudo virsh edit kmaster
    <graphics type='vnc' port='-1' autoport='yes' listen='0.0.0.0'>
    <listen type='address' address='0.0.0.0'/>
    </graphics>
    sudo virsh dumpxml kmaster | grep vnc
    ```
- Connect via console
    ```sh
    # Connect to guest via VNC, and execute following command
    systemctl enable serial-getty@ttyS0.service
    systemctl start serial-getty@ttyS0.service
    # Now from Host
    sudo virsh console kmaster
    ```
- Connect via SSH
    ```sh
    # get ip addr (bridged network)
    sudo virsh dumpxml VM_NAME | grep 'mac address' | awk -F\' '{ print $2}'
    # -or-
    sudo virsh domiflist kmaster
    sudo arp -an | grep 52:54:00:ce:8a:c4
    # For cloned VM, the ip address is next ip to original one
    # until ping once, the ip address are not allocated
    ```

## Commands
https://computingforgeeks.com/virsh-commands-cheatsheet/  
```sh
sudo virsh list --all
sudo virsh start|autostart|shutdown|destory <vm_name> 
sudo virsh edit <vm_name>
sudo virsh domiflist kmaster
```

## Troubleshooting
- Error: Cannot access storage file, Permission denied Error in KVM Libvirt  
https://ostechnix.com/solved-cannot-access-storage-file-permission-denied-error-in-kvm-libvirt/ Change to: user="hai" group="hai"
