## Create sudo user
```sh
adduser hai
passwd hai
usermod -aG wheel hai
sudo nano /etc/sudoers.d/hai  
# add:  hai     ALL=(ALL) NOPASSWD:ALL  
```
