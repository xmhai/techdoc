REST in practice (O'Reily)
Refactoring Databases by Scott J. Ambler and Pramod J. Sadalage (Addison-Wesley).

## Overview
- Strategy goals -> Design Principles (e.g. 12 factors) -> Practice
  (principles and practices guide how our systems should be built)
- Provide example code and Service Template to issustrate the practice

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
- For versioning, blue/green deployments is not worth for an average project.
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
  
  
