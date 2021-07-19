## Design
The way to identify microservices.  
- Domain Driven Design
- Business Function  
- Business Organization
- Change Frequency
## Migration
- Start with new features???
- Strangler Pattern
  - Analyze system modules.
  - Design a logical breakdown of the modules into probable services.
  - Identify the first batch of modules with the least dependencies to be moved out.
  - Rewrite these modules as microservices and, then, let microservices shadow the modules for some time.
  - Once the microservices are stabilized, strangulate/disconnect the original modules.
  - Repeat this process for other modules  

## Practice
- Make sure the infrastructure is ready before making any commitment to the migration. Infrastructure includes: Event-driven architecture, SSO, API Gateway etc.