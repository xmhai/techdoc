## Concept  
https://access.redhat.com/documentation/en-us/red_hat_enterprise_linux/5/html/installation_guide/ch-partitions-x86
- Disk Drive  
  Storage devices.
- Partition  
  Each partition can be accessed as if it was a separate disk. This is done through the addition of a partition table. Partitioning is particularly useful if you run multiple operating systems.
- Partition Table  
  is stored at very start of disk.  
- File System  
  Format into file system.  
- Logical Group

## Command
- lsblk  
  lists all mass storage devices and partitions on them, including mounted file systems, unmounted file systems and devices without any file system.
- df  
  reports file system disk space usage.
- fdisk [-l]
- parted [-l]
- mount/umount

## Logical Volume Manager (lvm)
- Resize  
  https://www.redhat.com/sysadmin/resize-lvm-simple  
  ```sh
  # view lvm
  sudo lsblk
  # resize by fixed size
  sudo lvextend -L +100G /dev/centos/root
  # resize by free space
  sudo lvextend -l +100%FREE /dev/vg01/lvol02
  # perform an online resize (for xfs), use fsadm to handle xfs, ext4 and a few filesystem
  xfs_growfs /dev/centos/root
  ```

## Add New Disk
```sh
# Create partition
fdisk
# Create 
```

## Practice
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
  sudo df -hT
  sudo rm -rf lost+found
  ```
  **Method 2**: Logical Volume/mount  
  https://www.thegeekdiary.com/redhat-centos-a-beginners-guide-to-lvm-logical-volume-manager/  
  ```sh
  # run as root
  sudo lvmdiskscan
  # create Physical Volume
  sudo pvcreate /dev/sda1
  sudo pvscan
  sudo pvs
  # create Volume Group
  sudo vgcreate vg01 /dev/sda1
  sudo vgs
  sudo vgdisplay vg01
  # create Logical Volume
  sudo lvcreate -L 100G -n lvol01 vg01
  sudo lvcreate -L 50G -n lvol02 vg01
  sudo lvs
  sudo lvdisplay
  # create File System
  sudo mkfs.ext4 /dev/vg01/lvol01
  sudo mkfs.ext4 /dev/vg01/lvol02
  # mount
  sudo nano /etc/fstab
  /dev/vg01/lvol01	/opt			ext4	defaults	0 0
  # comment existing /home mapping
  /dev/vg01/lvol02	/home			ext4	defaults	0 0
  # delete home lv
  sudo lvremove /dev/centos/home
  # check available space with vgs
  sudo vgs
  sudo lvextend -L+8G /dev/centos/root
  sudo resize2fs /dev/centos/root
  ```
- Logical Volume Management  
  https://www.howtogeek.com/howto/40702/how-to-manage-and-use-lvm-logical-volume-management-in-ubuntu/  
