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

## Configuration
- Find out Jenkins user  
From gui, go to "manage jenkins" > "system information" and look for "user.name"

### Generic Steps
- Install plugin
- Configue plugin
- Setup step

### Maven Integration
- Install Maven plugin  
- Install Maven  
  https://www.baeldung.com/install-maven-on-windows-linux-mac  
  ```sh
  wget https://dlcdn.apache.org/maven/maven-3/3.8.4/binaries/apache-maven-3.8.4-bin.tar.gz
  tar xzvf apache-maven-3.8.4-bin.tar.gz
  ```

### Nexus Integration  
https://dzone.com/articles/jenkins-publish-maven-artifacts-to-nexus-oss-using  

## Pipeline
https://www.jenkins.io/doc/book/pipeline/getting-started/  
Use share library  
need to install groovy???  
https://www.jenkins.io/doc/book/pipeline/shared-libraries/  
https://www.jenkins.io/blog/2017/10/02/pipeline-templates-with-shared-libraries/  

## Troubleshooting
- Error: groovy.lang.MissingPropertyException: No such property: docker for class: groovy.lang.Binding
  Reason: **docker pipeline plugin** is not installed.