## Installation
https://about.gitlab.com/install/?version=ce#ubuntu  
NOTE:  
```sh
# Replace DNS in follow command
sudo EXTERNAL_URL="https://192.168.10.16:10000" dnf install -y gitlab-ce
# Get root password
sudo cat /etc/gitlab/initial_root_password
```
Note: Might encounter when invoke Let's Encrypt.

## Configuration
- To change IP/Port after installation, edit the following line in /etc/gitlab/gitlab.rb:  
```sh
export EXTERNAL_URL "https://ip:port"  
sudo gitlab-ctl reconfigure  
```

- Create User  
From "Admin Area", create user, and then "Edit" user to set password.

## Sonarqube Integration
https://www.programmersought.com/article/66704933922/  
- Install Sonarqube Server