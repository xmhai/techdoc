# Main Feature
https://medium.com/devopsturkiye/kong-api-gateway-installing-configuring-and-securing-dfea423ee53c  
https://docs.microsoft.com/en-us/dotnet/architecture/microservices/architect-microservice-container-applications/direct-client-to-microservice-communication-versus-the-api-gateway-pattern  
- Reverse Proxy (Routing)  
- Cross-cutting  
  - Authentication and Authorization (by API key)  
  - Security (ACME/CORS/IP Restriction/Bot Detection)
  - Traffic Control (Rate limiting and throtting, Request Body Size, Request Terminating)
  - Logging
  - Caching
  - Analytics and Monitoring
- Transformation
- Request Aggregation (May cause "new ESB" issue)  

# Dummy api endpoint  
https://httpbin.org/anything

# API LifeCycle
- Definition (can import from other systems by OpenAPI Standard etc.)
- Development (Development tools integration, e.g. AxWay integration with SoapUI)
- Testing (Sandbox, e.g. AWS API Gateway)
- Publication (Jenkins Pipeline, or dbs manually way)
- Consumption (Retrieve API definition)
- Operational Metrics
