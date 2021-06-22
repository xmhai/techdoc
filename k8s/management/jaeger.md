## Architecture
- jaeger client -> jaeger agent -> jaeger collector -> (Kafka) -> (jaeger ingester&indexer) -> ElasticSearch -> jaeger query -> jaeger UI
- 
- Sampling
  - Head-based Sampling  
    Always on/off, probabilistic, rate limiting, Jaeger default to remote sampler to allow central configuration.
  - Tail-based (post trace) Sampling
    Require storage and performance overhead. (21/6/21) Currently support single node only. 

## Installation
https://medium.com/jaegertracing/a-guide-to-deploying-jaeger-on-kubernetes-in-production-69afb9a7c8e5  
https://logz.io/blog/jaeger-and-the-elk-stack/


## Deploy