## Folder Structure
https://tldp.org/HOWTO/HighQuality-Apps-HOWTO/fhs.html
https://unix.stackexchange.com/questions/8656/usr-bin-vs-usr-local-bin-on-linux/259326  
- /etc — Configuration Files  
- /var — Variable Data Files
  Log files and everything else that would normally be written to /usr during normal operation are written to the /var directory. e.g. log files in /var/log.
- /opt — Optional Packages  
  commonly used by proprietary software that doesn’t obey the standard file system hierarchy. e.g. Jboss EAP
- /lib — Essential Shared Libraries  
  contains libraries needed by the essential binaries in the /bin and /sbin folder. Libraries needed by the binaries in the /usr/bin folder are located in /usr/lib.

```txt
Executable location:  
/usr/bin:        for distribution managed user programs  
/usr/local/bin:  for NON-distribution managed user programs  
$HOME/bin:       current user  
$PATH can see the order of paths, /usr/local/bin search before /usr/bin  

To find out the location of command: $which command  
```

## Linux Software Package
https://embeddedinventor.com/a-beginners-introduction-to-linux-package-managers-apt-yum-dpkg-rpm/
https://embeddedinventor.com/yum-dnf-explained-for-beginners/

- yum (RHEL)
- apt (Ubuntu)
- Download File  
  - curl -OJL url
  - wget --user=admin --password='dangji2004' ftp://192.168.1.100/Public/geocoder.tar.gz
- Unzip  
  - gz:  gzip -d filename
  - tar: tar -xvf filename

## Shell Script
How the script is execute: https://en.wikipedia.org/wiki/Shebang_(Unix)
- When a text file with a shebang is used as if it is an executable in a Unix-like operating system, 
- The program loader mechanism parses the rest of the file's initial line as an interpreter directive. 
- The loader executes the specified interpreter program, passing path/to/script as the first argument
- Create shell file in Notepad++, and "Edit"->"EOL Conversion"->Unix, otherwise can encounter "invalid end of file" error.

## System  
- systemctl status|start|stop servicename
- hostnamectl

## Network
- nmcli
- ifconfig
- ip addr
- sudo lsof -i -P -n | grep LISTEN
- netstat -tunlp | grep 8081  

## Mem
- free

## CPU
- top

## Disk
- df  
  reports file system disk space usage.
- lsblk  
  lists all mass storage devices and partitions on them, including mounted file systems, unmounted file systems and devices without any file system.
- fdisk
- mount/umount
- Move /opt, /home to another device  
softlink and bind mount???  
**Method 1**: partition/mount  
https://www.howtogeek.com/442101/how-to-move-your-linux-home-directory-to-another-hard-drive/  
```sh
sudo fdisk /dev/sda
     d: delete existing partition
     n: create new partition
sudo mkfs -t ext4 /dev/sda1
sudo mkdir -p /mnt/sda1
sudo mount -t auto /dev/sda1 /mnt/sda1
sudo umount /dev/sda1
df -hT
sudo rm -rf lost+found
```
**Method 2**: Logical Volume/mount  
https://www.thegeekdiary.com/redhat-centos-a-beginners-guide-to-lvm-logical-volume-manager/  
```sh
# run as root
lvmdiskscan
# create Physical Volume
pvcreate /dev/sda1
pvscan
pvs
# create Volume Group
vgcreate vg01 /dev/sda1
vgs
vgdisplay vg01
# create Logical Volume
lvcreate -L 100G -n lvol01 vg01
lvcreate -L 50G -n lvol02 vg01
lvs
lvdisplay
# create File System
mkfs.ext4 /dev/vg01/lvol01
mkfs.ext4 /dev/vg01/lvol02
# mount
nano /etc/fstab
/dev/vg01/lvol01	/opt			ext4	defaults	0 0
# comment existing /home mapping
/dev/vg01/lvol02	/home			ext4	defaults	0 0
# delete home lv
lvremove /dev/centos/home
# check available space with vgs
vgs
lvextend -L+8G /dev/centos/root
resize2fs /dev/centos/root
```
- Logical Volume Management  
  https://www.howtogeek.com/howto/40702/how-to-manage-and-use-lvm-logical-volume-management-in-ubuntu/  

## Performance
- top

## User
```sh
sudo adduser <username>
sudo userdel <username>
sudo passwd <username>
# /etc/passwd file store all users, change user home or rename user from here.

# /etc/group file store all information of groups
sudo groupadd <groupname>
sudo groupdel <groupname>
sudo adduser <username> <groupname>
sudo deluser <username> <groupname>

# 777 (owner, group owner, everybody else)
sudo chmod 777 <filename>
sudo chmod 777 <dirname> -R
sudo chown <user> <filename>
sudo chown -R <user> <dirname>
sudo chgrp <group> <filename>
sudo chgrp -R <group> <dirname>
```