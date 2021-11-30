https://github.com/ernesen/DDD  

## Business Model
- Customer Segment
- Value Proposition
  - Different customer looks for different value propostions (like convinience, value for money, logistic)
- Key Resources
- Key Partners and Suppliers
- Key Activities
- Customer Relationship
- Channels
- Revenue Streams
- Cost Structure

## (Business) Domain Driven (System) Design
Leads to smaller independent domain models.  

Domain experts are the people who define the requirements and end-user of the system.

**Straitegy Patterns & Pratices**  
- Categorize the Sub-domains
  Based on their complexity & business value. Type of sub-domains:  
  - Core
    Differentiator for the business, e.g. credit card for banks. Need to implement it in-house.  
  - Generic  
    Known solutions exists, e.g. HR, FM.  usually buy the solutions.  
  - Supporting  
    Does not provide business advantage but core depends on it, e.g. customer support and compliance for banks.
    Different from Core in the complexcity, usaully it is simple like CRUD. Can be implemented in-house or out-sourcing.  
- Identify the Sub-domains
  - Coarse-grained subdomains: Company's departments and other organizational units as starting point
  - Distill to Finer-grained sudomains: Inner workings of departments. Map to specific business capabilities.  
  - Principle: subdomains as a set of coherenct use cases, which means involve the same actor, the business entities, and they all manipulate a colsely related set of data.
  - Some subdomains might have nothing to do with software.  
- Ubiquitous Language
  - Maintaining a glossary of project-related terminology.
  - Use cases to capture not only the verbs but also the actual business logic, with its rules, assumptions, and invariants.
  - This language will be used in code later.
- Boundary Context
  - A bounded context can contain multiple sub-domains, it defines physical (microservices) and ownership boundaries.
  - How to decide **bounded context???**  
- Boundary Context Integration
  - Cooperation: Two-way, Partnership or Shared kernel, both teams make the change to integration model
  - Comsumer-supplier: One-way, Conformist, anticorruption layer or open-host service pattern
  - Separate ways: duplicate the code and evolve separately

**Tactic Patterns & Pratices**  
- Implementing Simple Business Logic
  - Transaction Script (enclosed with beginTx and commit, e.g. ETL)
  - Active Record (map to a db record, and CRUD is implemented inside, JPA)
- Complex Business Logic
  - Entity:
  - Value Object: Immutable object, use value object whenever you can.
    From a business domain perspective, a useful rule of thumb is to use value objects for the domain’s elements that describe properties of other objects (i.e. Entity).  
  - Aggregate: use entities as part of an aggregate. Sometimes a hierarchy of entities.
    Only the information that is required by the aggregate’s business logic to be strongly consistent should be a part of the aggregate. (Include value as properties)
    All information that can be eventually consistent should reside outside of the aggregate’s boundary. (Refer by ID)
    i.e. Only the data within an aggregate’s boundaries can be considered strongly consistent. Everything outside is eventually consistent.
  - Domain Event: a significant event that has occurred in the business domain.  
    Domain events are part of an aggregate’s public interface. An aggregate publishes its domain events.  
  - Domain Service: a stateless object that implements business logic that either doesn’t belong to any aggregate or value object, or that seems to be relevant to multiple aggregates. Creat a service for each **Use Case???**  
  - Aggredate vs Event sourcing
- Architecture Pattern
  - Layered Architecture
  - port & adapters (JPA repository is an example)
  - CQRS
- Communication Pattern
  - Open-Host Service/Anti-Corruption Layer (Use Proxy + Adaptor pattern, SPI)
  - Reliable Publish Event (Use Outbox pattern: Book Learn DDD, chapter 9)
  - Long running transaction across multiple aggregates (Use Saga pattern)
  
**Practice**
- **Start with wider boundaries**. If required, decompose the wide boundaries into smaller ones as you gain domain knowledge. The reason is refactoring into small logical boundaries (namespace, modules, and packages) is much less expensive than changing physical boundaries (deployment).
- Eventstorming to identify domain knowledge
  - Domain Events
  - Timelines
  - Pain points
  - Pivotal Domain Events (possibly indicate the boundary)  
    at this point, the resultant model should cover a wide range of company business model.  
  - Command that trigger the events
  - Actor, Policy or External System that trigger the commands
  - Read Models
  - Organize in aggregates
  - Decide Bounded Context
  - 