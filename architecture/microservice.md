## Design
The way to identify microservices.  
- Domain Driven Design
  Identify Business Domain/Bounded Context/Data Model
- Business Function  
- Business Organization
- Change Frequency

## Difficulties
- Code will increase 35-45 percent compare to monolithic
- Distributed Transaction (SAGA pattern to solve)  
  -> Doesn't support Read isolation (use process orchestrator to solve)

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
- Make sure the infrastructure is ready before making any commitment to the migration (It will take 2-3 month). Infrastructure includes: 
	- API gateways
  - SSO
	- Service mesh
  - Event-driven architecture
	- DevSecOps pipelines
	- Developer tools and utilities
	- Agile project management
	- Issue tracking solutions
	- Logging
	- Monitoring solutions and so on.
