## ElasticSearch
https://www.elastic.co/guide/en/elasticsearch/reference/current/high-availability-cluster-small-clusters.html

**HA Mode**: 3 Master  
Elasticsearch cluster should have a minimum of 3 master-eligible nodes.
- Replication is supported natively and enabled by default.  
- Primary shard and replica shards for reading (2 replicas for critical systems, default 1 replica)
- Can take snapshot for backup.
- A master node is responsible for creating and deleting indices, and assign shards etc.
- A node with master node role will not automatically become the master node (is elected by quorum).

**Installation**  
For testing use Docker Compose:  
https://www.elastic.co/guide/en/elastic-stack-get-started/current/get-started-docker.html  
For production setup with 3 nodes:  
https://logz.io/blog/elasticsearch-cluster-tutorial/  
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

