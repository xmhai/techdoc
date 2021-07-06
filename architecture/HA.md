## HA Mode
- 2 Nodes
  - Active + Active
  - Active + Passive
  - Master + Replica 
- 3 Nodes
  - Master + Slave
  - All Masters (quorum)  
    e.g. etcd

## ActiveMQ

## JBossEAP

## Keycloak
**HA Mode**: Active-Active  

## ElasticSearch
**HA Mode**: 3 Master  
Elasticsearch cluster should have a minimum of 3 master-eligible nodes.

**Installation**  
https://www.crybit.com/setup-a-three-node-elasticsearch-cluster-centos-7/  
```sh
yum install java-1.8.0-openjdk
curl -L -O https://artifacts.elastic.co/downloads/elasticsearch/elasticsearch-7.13.2-x86_64.rpm
sudo rpm -i elasticsearch-7.13.2-x86_64.rpm
# configure elasticsearch.yml
# - For Cloud VM, network.host: \_*eth0*_ 
# - Use intranet ip address for discovery.zen.ping.unicast.hosts
sudo nano /etc/elasticsearch/elasticsearch.yml
# COPY the configuration files to other nodes, and change the node name.
# start the service
sudo systemctl daemon-reload
sudo systemctl enable elasticsearch.service
sudo systemctl start elasticsearch.service 
```
**Clean Node**
sudo rm -r /var/lib/elasticsearch

**Uninstall**
sudo systemctl stop elasticsearch
sudo rpm -e elasticsearch

**Springboot Connection**  
https://docs.spring.io/spring-data/elasticsearch/docs/current/reference/html/#elasticsearch.clients.configuration  
Define the nodes in ClientConfiguration

**Fluentd Connection**  
https://docs.fluentd.org/output/elasticsearch#parameters  
Define the nodes in hosts parameter

**FluentBit Connection**

**Jaeger Connection**


## Redis
https://redis.io/topics/sentinel  
**HA Mode**:  Sentinel + 1 Master (Write/Read) - 2 or more Replicas (Read)  
For HA setup, basic setup with three boxes running both Redis and Sentinal.  

![](redis-sentinel-setup-logical-diagram.png)

**Installation**  
https://www.tecmint.com/setup-redis-high-availability-with-sentinel-in-centos-8/  
NOTE:
- Part 1 - Must set to same password in all three nodes.  
- Part 2 - there is already a line of "sentinel monitor mymaster 127.0.0.1 6379 2", replace the ip.  
Use "journalctl -xe" command to view service error message.  
- Part 2 - for master node, "sentinel monitor mymaster 127.0.0.1 6379 2" is incorrect, must set to external ip address. e.g. "sentinel monitor mymaster 192.168.56.110 6379 2"

**How it works**  
Sentinal will update the configuration file /etc/redis-sentinel.conf dynamically when detecting master node failure.

However, when the master node down, replicas will not get update from new master even though client can still write/read from new master.

Sometimes telnet redis:6379 failed, need to restart the service in the case.

**Springboot Connection**  
Configure Sentinel Nodes in application.yml

## MySQL
