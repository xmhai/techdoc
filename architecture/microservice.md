## Design
The way to identify microservices.  
- Domain Driven Design
- Business Function  
- Business Organization
- Change Frequency

## Migration
- How to start
  - Start with new function/features.
  - Start with functions required scale up on demand.
  - Start with frequently changed functions, from the least coupled functionalities.
  - Prefered to choose functionanlity with user interface.
- Strangler Pattern  
  https://martinfowler.com/bliki/StranglerFigApplication.html  
  https://martinfowler.com/bliki/EventInterception.html  
  - Analyze system modules.
  - Design a logical breakdown of the modules into probable services.
  - Identify the first batch of modules with the least dependencies to be moved out.
  - Rewrite these modules as microservices and, then, let microservices shadow the modules for some time.
  - Once the microservices are stabilized, strangulate/disconnect the original modules.
  - Repeat this process for other modules  
- Design consideration
  - Determine how micro the microservice to be, make sure Ops team can handle like monitoring.
  - Decide how many microservice should be called in a sequence.
- Setup DevOps pipeline for the first microservice
- Database Decomposition
  - Database wrapping service” pattern.

## Practice
- Make sure the infrastructure is ready before making any commitment to the migration. Infrastructure includes: Event-driven architecture, SSO, API Gateway etc.