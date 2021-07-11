# Main Feature
https://docs.microsoft.com/en-us/dotnet/architecture/microservices/architect-microservice-container-applications/direct-client-to-microservice-communication-versus-the-api-gateway-pattern  
- Reverse Proxy
- Cross-cutting  
  - Authentication and Authorization 
  - Rate limiting and throtting
  - Response caching
  - IP whitelisting
  - etc
- Request Aggregation  
  May cause "new ESB" issue.

  # Dummy api endpoint
  https://httpbin.org/anything
