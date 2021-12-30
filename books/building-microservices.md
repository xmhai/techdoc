## Overview
- Strategy goals -> Design Principles (e.g. 12 factors) -> Practice
  (principles and practices guide how our systems should be built)
- Provide example code and Service Template to issustrate the practice

## Principles
- Model around business concepts: Bounded Context
- Adopt a Culture of Automation: Automated Test, uniform command-lin deploy script, environment configuration
- Hide internal implementation details
- Decentralize All the Things
- Indepenendently Deployable
- Isolate Failure
- Highly Observable

## How to model services
- Loose Coupled and High Cohesion
  Bounded Context contains: internal models AND shared models can share externally through explicit interfaces

- Prematurely decomposing a system into microservices can be costly, especially if you are new to the domain. In many ways, having an existing codebase you want to decompose into microservices is much easier than trying to go to microservices from the beginning.  

- Design the microservices and communication according to organizational structure is a good approach:
  - Whether you choose the nested (context) approach over the full separation (context) approach should be based on your organizational structure.
  - The same terms and ideas that are shared between parts of your organization should be reflected in your interfaces.

## Integration
- Prefer choreographed system over orchestration, but need to monitor and track the process across system boundary
- Spring Reactive programming
- don’t violate DRY within a microservice, but be relaxed about violating DRY across all services。 And finally, make sure that the clients are in charge of when to upgrade their client libraries。
- Data freshness can be address by a reference (e.g. customer id), but definitely it will cause the load to customer service.
- Version strategy: internally V1 point to V2, and V2 point to V3
- For versioning, blue/green deployments is not worth for an average project. Only the kind of system like e-commerce required that.
- BFF: restrict the use of these backends to one specific user interface or application (e.g. Mobile, web, admin). These BFFs should only contain behavior specific
to delivering a particular user experience (NO buisness logic inside).
- CMS: Use CMS as a service, and front it and other services with own Frontend.
- Use Facade service for legacy systems or COTS systems.
- Strangle pattern

## Monolithic to Microservices
- Our code should represent our organization, so:-
  1. our packages representing the bounded contexts in our organization.
  2. our packages should interact in the same way the real-life organizational groups in our domain interact.

- Break the packages: if there is not interaction between organizational groups, then should not have dependency
- Break database: Split repository; use service call instead of table access;
- Shared data, code table:-
  Option 1: Table for each service (not recommended)
  Option 2: Keep data in configuration file or code (very sensible approach)
  Option 3: Common service (a bit overkilled for simple table)
- Rely on eventually consistent.
- Walk through Use Case to see what calls get made:
  Use CRC (class-responsibilty-collabration) to understand service boundary
  Is there any circular reference
  Is there chatty communication between services

## Deployment
- CI: Daily check-in; Unit Testing; Fix the broken issue first.  
- CD Pipeline model the deployment process including manual step, separate the fast test and slow test in the pipeline.  
- Start with single build until microservices are clear.
- The most sensible way to trigger any deployment is via a single, parameterizable command-line call. This can be triggered by scripts, launched by your CI tool, or typed in by hand.

  
## Security
- Favor coarse-grained roles, modeled around how your organization works.
- Service-to-service authentication option:
  - Allow everything inside the perimeter
  - HTTP(S) Base authentication (The server needs to manage its own SSL certificates)
  - SAML/OIDC (Service account)
  - Client certificates (Certificate Management is an issue)
  - HMAC over HTTP (need to share secret)
  - API Key (API Key managed by API Gateway)

## Scale
- Test the failure scenarios
- Handle failure
  - Timeout: put timeout on all out-of-process call; Pick a default timeout; Log when timeouts occur;  
  - Circuit Breaker: mandating circuit breakers for all synchronous downstream calls. e.g. Netflix Hystrix
  - Bulkhead: e.g. separate connection pools for different downstream legacy systems.
  - Isolation
  - Idempotency
- Scaling
  - Split workload: e.g. split the query reporting service into separate microservice.
  - Load balancing
  - Woker-based systems: e.g. worker nodes to process one-off tasks which can be put stored in message broker.
  - Autoscaling: predictive and reactive.
- Database scaling
  - Read replica: scale for read, caching is a better option
  - Sharding: scale for read
  - CQRS: event-sourcing
  - Caching:
    - Client-side caching: cache-control; expires; ETag
    - Proxy caching
    - Server-side caching
    - Caution: Expires: Never will make the cache never be refreshed
- CAP (Consistency/Availability/Partion Tolerance)
  - C: not easy for distributed system, so strongly NOT recommended
  - AP: Eventually Consistent model. **AP** systems end up being the right call in many situations.
  - CA: CA systems don’t exist in distributed systems.
  - CP: depends
  - Individual service doesn't need to be CP or AP.
- Service Discovery
  - DNS: \<servicename>-\<environment>.musiccorp.com; point to load balancer for multiple instances.
  - Dynamic Service Registry: Zookeeper, generic; Consul; Eureka: only for Java;
- Build a simple dashboard to display the services running, and pull more and more information over time.

## Reference Books
REST in practice (O'Reily)  
Refactoring Databases (Addison-Wesley) Scott J. Ambler and Pramod J. Sadalage  
Continuous Delivery (Addison-Wesley) Jez Humble and David Farley  
Agile Testing: A Practical Guide for Testers and Agile Teams (Addison-Wesley) Lisa Crispin and Janet Gregory  
Release It! (Pragmatic Programmers) Michael Nygard

