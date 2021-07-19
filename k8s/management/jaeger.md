## Architecture
![](jaeger.png)
- Flow  
  jaeger client (application library, sending UDP to agent)  
  -> jaeger agent (run as sidecar with application for high volume, or deamonset).  
  -> jaeger collector  
  -> (Kafka) -> (jaeger ingester&indexer)  
  -> ElasticSearch  
  -> jaeger query -> jaeger UI
- 
- Sampling
  - Head-based Sampling  
    Always on/off, probabilistic, rate limiting.
  - Tail-based (post trace) Sampling
    Require storage and performance overhead. (21/6/21) Currently support single node only. 
  - Use REMOTE-SAMPLING is recommended so the Jaeger Operator can control the sampling. Jaeger default to remote sampler to allow central configuration.
  - My thought, since Sampling can control the tracing volume, it is better to run Jaeger Agent as daemonset???.

## Installation
https://medium.com/jaegertracing/a-guide-to-deploying-jaeger-on-kubernetes-in-production-69afb9a7c8e5  
https://medium.com/@klaus.dobbler/introducing-distributed-tracing-to-a-docker-swarm-landscape-f92c033e36db  
https://logz.io/blog/jaeger-and-the-elk-stack/


## Deploy
Production
- Jaeger Client
- Jaeger Agent  
  Installing the agent as a deamonset is the simplest and most economical option.