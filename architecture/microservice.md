## Resources
https://martinfowler.com/articles/microservices.html  
"Microservices Architecture" - Michael Amundeson  
"Building Microservices" - Sam Newman  
"Implementing Domain-Driven Design" - Vaughn Vernon  

## Concept
My understanding: Microservice is to address non-functional requirements and techincal needs in three areas:-  
- **Maintenability**: Fast change and evolve part of the system  
- **Scalability**  
- **Performance**: Use appropriate technology Stack  

Criteria:  
- Loose coupled module: if a change to a miroservice will cause changes to other services, then we should think about is it part of other service.

Benifit:  
- Own CICD lifecycle, fast to evolve
- Fault isolation
- Increase throughput

## Design
- Identify Microservices  
  - Domain Driven Design
    Identify Business Domain/Bounded Context/Data Model
  - Business Capability/Function  
  - Business Organization
  - Change Frequency
- Granularity
  DDD and bounded context as first step.  
  - Too coarse-grained  
    - Too many responsibilities  
    - Manages data across a large number of tables
      Use the guideline that a microservice should own no more than three to five tables.  
    - Too many test cases  
      e.g. hundreds of unit and integration tests
  - Too fine-grained
    - Become a collection of simple CRUD
    - Heavily interdependent on one another (Chatty Services)  

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
- Make sure the infrastructure is ready before making any commitment to the migration (It will take 2-3 month). Infrastructure includes:-  
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

## Microservice Concern  
**Design**
- Data model sharing  
  Event sourcing and CQRS: one service emits event, other service use the event to build it's own model.  
  DON'T overuse it, to be use when necessary.
- Distributed Transaction  
  Eventual Consistent:  
  Saga: compensation transaction callback is invoked when need to rollback.
- Service communication  
  Asynchronous Messaging.  

**Operation**
- Security/Authentication  
  API Gateway.  
  Approach 1: Kong Ingress Controller with OIDC plugin, no authentication at springboot.  
- Deployment  
  Docker.
- Service discovery
- Request routing
- Service failure handling
- Logging

## Patterns
- API Gateway
- Back For Frontend (BFF)
- Saga
- Event Sourcing
