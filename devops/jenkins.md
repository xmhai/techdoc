## Installation  
v7.3 on Centos 7  
- Install dnf first: sudo yum install dnf  
- Follow instruction:  
  https://www.jenkins.io/doc/book/installing/linux/  (Refer to the section of RHEL/Centos)  
- Change port number to 9090 as default port used by Jira (:8080)  
  sudo nano /etc/sysconfig/jenkins  

## Access  
http://ip:9090  
default password in: /var/lib/jenkins/secrets/initialAdminPassword  
