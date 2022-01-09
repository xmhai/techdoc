## Component
https://dzone.com/articles/microservices-with-jhipster?edition=596295&utm_source=dzone&utm_medium=email&utm_campaign=dzone&utm_content=%5B%5Brssitem_title%5D%5D%20-%20%5B%5Brss_title%5D%5D  
- **Service Discovery** (Eureka/Consul)  
  For microservice register and discovery. The services that get registered to the Eureka server send their heartbeats and when the client no longer sends a heartbeat, the service is taken out of the registry.
  - **Netflix Eureka**:  
    supports application services written in Spring Boot.
  - **HashiCorp Consul**:  
    supports multiple clients.
- **Configuration Management** (Spring Cloud Config Server)  
  This provides the centralized store for managing the properties and configurations for all the microservices.
- **API Gateway** (Zuul)  
- **Authorization Server** (UAA)  
- **Circuit Breaker** (Hystrix)  
- OpenFeign
- Monitoring
- Logging
- ...