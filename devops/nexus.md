## Installation
Centos 7 + Nexus 3  
https://www.fosslinux.com/27838/installing-sonatype-nexus-repository-oss-on-centos-7.htm  
```sh
# instal Java JDK 8+
sudo yum -y install java-1.8.0-openjdk java-1.8.0-openjdk-devel
cd /opt
sudo wget https://download.sonatype.com/nexus/3/latest-unix.tar.gz
sudo tar -xvzf latest-unix.tar.gz
mv nexus-3.xx.xx-xx nexus
mv sonatype-work nexusdata
# create user to run the service
useradd --system nexus
chown -R nexus:nexus /opt/nexus
chown -R nexus:nexus /opt/nexusdata
# update data folder from sonatype-work to nexusdata
sudo nano /opt/nexus/bin/nexus.vmoptions
# change runasuser to nexus
sudo nano /opt/nexus/bin/nexus.rc
# run nexus as service
sudo nano /etc/systemd/system/nexus.service

[Unit]
Description=Nexus Service
After=syslog.target network.target

[Service]
Type=forking
LimitNOFILE=65536
ExecStart=/opt/nexus/bin/nexus start
ExecStop=/opt/nexus/bin/nexus stop
User=nexus
Group=nexus
Restart=on-failure

[Install]
WantedBy=multi-user.target

# Start Service
sudo systemctl daemon-reload
sudo systemctl enable nexus.service
sudo systemctl start nexus.service
# view log
sudo tail -f /opt/nexusdata/nexus3/log/nexus.log
```
