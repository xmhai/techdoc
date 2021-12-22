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
sudo rm -rf /opt/atlassian

sudo ./atlassian-bitbucket-6.10.0-x64.bin
```
## Access
http://ip:7990  
Need to register a Atlassian account first to get a token to complete server setup.  

