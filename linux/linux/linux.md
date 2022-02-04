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
- /bin, /usr/bin, /usr/local/bin
  ```txt
  Executable location:  
  /usr/bin:        for distribution managed user programs  
  /usr/local/bin:  for NON-distribution managed user programs  
  $HOME/bin:       current user  
  $PATH can see the order of paths, /usr/local/bin search before /usr/bin  

  To find out the location of command: $which command  
  ```

## Install Software
https://embeddedinventor.com/a-beginners-introduction-to-linux-package-managers-apt-yum-dpkg-rpm/
https://embeddedinventor.com/yum-dnf-explained-for-beginners/

- yum (RHEL) / apt (Ubuntu)
- Download File  
  - curl -OJL url
  - wget --user=admin --password='dangji2004' ftp://192.168.1.100/Public/geocoder.tar.gz
  - Unzip  
    - gz:  gzip -d filename
    - tar: tar -xvf filename
  - mv \<dir\> /opt
  - create link in /usr/bin for the application under /opt/\<dir\>/bin  
    ln -s /<full>/<path>/<to>/<file> /usr/bin
  - OR add application to PATH  
    ```sh
    nano ~/.bashrc
    export PATH=<path>:$PATH    
    ```

## Shell Script
How the script is execute: https://en.wikipedia.org/wiki/Shebang_(Unix)
- When a text file with a shebang is used as if it is an executable in a Unix-like operating system, 
- The program loader mechanism parses the rest of the file's initial line as an interpreter directive. 
- The loader executes the specified interpreter program, passing path/to/script as the first argument
- Create shell file in Notepad++, and "Edit"->"EOL Conversion"->Unix, otherwise can encounter "invalid end of file" error.

## File
- which
- grep (search for text in file content)
  grep -r "text-to-find-here" .
- find (search for files by file information)  

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