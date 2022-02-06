## CentOS 7 Installation
- Choose "Minimal Install"
  Probably "Infrastructure Server" is a better option (need to try if future has chance), the reason is: NetworkManager-wifi will not be installed by default, so an **wired connection** is required for wifi setup.
- After installation with wifi connection finished, run "nmcli device status", it will shows error:  
eno1 **disconnected**  
wlp2s0 **unmanaged**  
    - To solve "disconnected" issue, run "nmcli con up eno1", then the status will be changed to "connected". Also need to modify /etc/sysconfig/network-scripts/ifcfg-eno1, change ONBOOT=yes
    - To solve "unmanaged" issue, run "yum install NetworkManager-wifi", reason is minimal install default wifi module is not installed.
- RJ45 diagnosis: one blinking orange light means data is transmitting, one steady green/red light means connection is ok.
- When putty shows error "no route to host", check the firewall status and make sure it disabled.
  ```sh
  systemctl status firewalld
  systemctl stop firewalld
  systemctl disable firewalld
  systemctl mask --now firewalld
  ```
- Useful command when diagnosis network issue
  ```sh
  nmcli device status
  nmcli con up eno1
  ip addr
  systemctl status xxx
  ```
## Initial Setup after installation
https://www.digitalocean.com/community/tutorials/initial-server-setup-with-centos-7  

## Create sudo user
```sh
adduser hai
passwd hai
# add your new user to the wheel group
usermod -aG wheel hai
# no password for sudo
sudo nano /etc/sudoers.d/hai  
# add:  hai     ALL=(ALL) NOPASSWD:ALL  
```
## Setup sftp server
No need to setup SFTP server as it is sshd that handle the upload and download.  
 
