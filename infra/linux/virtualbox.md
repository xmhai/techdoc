## Installation
- Download VirtualBox
- 

**VM Settings:**  
- Memory: 2G  
- Dynamic Disk: 30G  
- Network: 1st adaptor Hostonly, 2nd adaptor NAT  

**Network**  
https://blogs.oracle.com/scoter/networking-in-virtualbox-v2

- Option 1: NAT + Host Only (**Preferred**)  
Adaptor 1: Host Only / enp0s3 / 192.168.56.1xx  
Adaptor 2: NAT / enp0s8 / 10.0.2.15  
192.168.56.1 is the default host ip address, this can be set from VirtualBox menu "File"->"Host Network Manager..."

- Option 2: Bridge Mode  
Adaptor 1: Bridge / enp0s3  
is recommended if ip address is enough  

**Redhat**  
Create user  
Choose "Server Installation"  
Check the network section on installation screen  

## Install VirtualBox Guest Add-on
- A set of drivers and system utilities for supported guest operating systems that optimize the guest OS performance and usability.
- Provides closer interaction between host and guest operating systems. Features such as:
  - mouse pointer integration
  - enhanced video mode support
  - shared folders
  - shared clipboard
  - **time synchronization**  
can be enabled after installing VirtualBox Guest Additions on a guest OS.
- In the VirtualBox VM window, click Devices > Insert Guest Additions CD image (The ISO file is located in the VirtualBox installation directory).
- Installation  
https://www.tecmint.com/install-virtualbox-guest-additions-on-centos-8/

## Tips
- Right+Ctrl to release mouse captured by VM
- Resize Disk  
From VirtualBox File->Virtual Media Manager...

## Best Practice
- Create base image for different OS, and then clone to make new vm.
- (**Ubuntu**) remember to modify "dhcp-identifier: mac" in /etc/netplan/*.yaml, otherwise same dhcp ip address will be returned as Ubuntu default use client_id as the identifier.

## Network  
- Get new DHCP IP  
```sh
sudo dhclient -v -r enp0s8  
sudo dhclient -v enp0s8  
```  

In the case, network adaptor is configured after VM created:  
**Redhat**  
```sh
# Check interface connection status
nmcli d 
# Make interface connection active
nmcli dev connect enp0s8
sudo nano /etc/sysconfig/network-scripts/ifcfg-enp0s8
# change ONBOOT=yes
```  
https://superuser.com/questions/1313692/how-to-setup-a-bridge-connection-for-enp0s3-centos7-on-oracle-virtualbox  
**Ubuntu**  
```sh
sudo nano /etc/netplan/00-installer-config.yaml  
# add enp0s8 if not inside.  
```  
https://askubuntu.com/questions/984445/netplan-configuration-on-ubuntu-17-04-virtual-machine  
https://askubuntu.com/questions/293816/in-virtualbox-how-do-i-set-up-host-only-virtual-machines-that-can-access-the-in/1013467#1013467
