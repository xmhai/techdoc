## Installation
Version 6.10.0 (Long Term Support release)  
https://it.megocollector.com/linux/install-atlassian-bitbucket-on-centos-8/  
```sh
#install Git 2.11.0+
#install JDK first
wget https://www.atlassian.com/software/stash/downloads/binary/atlassian-bitbucket-6.10.0-x64.bin?_ga=2.132685326.1651200632.1580009508-119668002.1580009508
mv atlassian-bitbucket-6.10.0-x64.bin\?_ga\=2.132685326.1651200632.1580009508-119668002.1580009508 atlassian-bitbucket-6.10.0-x64.bin
chmod +x atlassian-bitbucket-6.10.0-x64.bin

##clear old installation
sudo rm -rf /opt/atlassian/bitbucket

sudo ./atlassian-bitbucket-6.10.0-x64.bin
```
## Access
http://ip:7990  
Need to register a Atlassian account first to get a token to complete server setup.  

## Uninstall
https://confluence.atlassian.com/bitbucketserverkb/how-to-uninstall-bitbucket-server-828789036.html#:~:text=In%20Registry%20Editor%20menu%20click,Editor%20menu%20and%20select%20delete%20.  
```sh
# BITBUCKET_INSTALLATION=\opt\atlassian
# BITBUCKET_HOME=\var\atlassian
sudo service atlbitbucket stop
cd /opt/atlassian/bitbucket/6.10.0/bin
sudo ./install_linux_service.sh -u remove_service atlbitbucket
sudo rm -Rf /opt/atlassian/bitbucket
sudo rm -Rf /var/atlassian/bitbucket
sudo userdel atlbitbucket
```