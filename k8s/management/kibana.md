## Deployment
- Recommend running Kibana separate from your Elasticsearch data or master nodes.
- Depends on your use case, in most cases, one node should be sufficient, multiple Kibana instances for large number of heavy Kibana users.
- Kibana isn’t terribly resource intensive, it has a default maximum memory limit of 1.4 GB, and in most cases, we recommend leaving this unconfigured.

## Installation
- Standalone  
  
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