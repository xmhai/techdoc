## Deployment
- Recommend running Kibana separate from your Elasticsearch data or master nodes.
- Depends on your use case, in most cases, one node should be sufficient, multiple Kibana instances for large number of heavy Kibana users.
- Kibana isn’t terribly resource intensive, it has a default maximum memory limit of 1.4 GB, and in most cases, we recommend leaving this unconfigured.

## Installation
- Standalone  
https://www.elastic.co/guide/en/kibana/current/rpm.html  
Note:  
  - Kibana version must be **exactly** same version as ElasticSearch (curl http://localhost:9200 to get ES-version).  
  Follow the steps in "Download and install the RPM manually":  
    ```sh
    wget https://artifacts.elastic.co/downloads/kibana/kibana-<ES-version>-x86_64.rpm
    sudo rpm --install kibana-<ES-version>-x86_64.rpm
    ```
  - Configure
    ```sh
    sudo nano /etc/kibana/kibana.yml  
    # Set server.host to server IP or "0.0.0.0"
    ```
  - Follow the steps to start the service
- Docker  
  https://www.elastic.co/guide/en/elastic-stack-get-started/current/get-started-docker.html

Docker-compose file  
(NOTE: no space between hosts)
```yaml
version: '2.2'
services:
  kib01:
    image: docker.elastic.co/kibana/kibana:7.13.2
    container_name: kib01
    ports:
      - 5601:5601
    environment:
      ELASTICSEARCH_HOSTS: '["http://40.82.210.252:9200","http://20.40.82.80:9200","http://20.40.80.201:9200"]'
    networks:
      - elastic

networks:
  elastic:
    driver: bridge  
```
## Uninstall
```sh
sudo systemctl stop kibana.service
sudo yum remove kibana
```
## Troubleshooting
```sh
sudo cat /var/log/kibana/kibana.log
```
## Access
http://srv:5601  

- Use Kibana "Dev Tools" to send REST API, the url consists of api and command in the format of /*api*/*command*
- View k8s log:  
  - Management -> Stack Management -> Index Pattern -> Create Index Pattern to match k8s log, e.g. "logstash*"
  - Analytics -> Discover  
    Left Panel -> Hover the field -> click "add" icon.  
    Enter container-name in "Search" or "Add filter"  