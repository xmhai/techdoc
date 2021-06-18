## Deployment
- Recommend running Kibana separate from your Elasticsearch data or master nodes.
- Depends on your use case, in most cases, one node should be sufficient, multiple Kibana instances for large number of heavy Kibana users.
- Kibana isn’t terribly resource intensive, it has a default maximum memory limit of 1.4 GB, and in most cases, we recommend leaving this unconfigured.