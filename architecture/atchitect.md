## Understand the Business
- Business lines
- Revenue
- Competitor
- Strength and weakness
  
## Understand the system
- Define system goals
- Understand and work with end users

## Communcation
- Communcate with different stackholders in different languages.

## Architect should code
- Understand the technology
- Support developers
- Gain respect

## Architecture Process
- System requirements
  - Business Services (Use Case)
  - Business Flow (Flow Chart or BPMN)
  - User Interface (Mock screen)
- Non-function requirements
  - Performance Latency/Throughput
  - Load: How many actual requests the system can handle without crashing.
  - Data Volume: Day one/Growth Rate
  - Concurrent Users: How many user using the system (include dead times), Rule of thumb: Concurrent Users=10 x Load
  - SLA
  - Always talk in numbers 
  - To be fulfilled by system capability qualities:
    Scalability/Modulality/Extensibility/Testability/Manageability
- System Architecture
  - Tier (Web/App/Database/Integration/Gateway Utility)
  - Software Components
    - Stateless
  - Component communication
    * Loose Coupling (Service Discovery)
    * Messaging (Rest API/HTTP Push/Queue/File)
  - Performance
    * In-memory Caching
    * Distributed Caching
  - Manageability
    * Logging (Log Service/Correlation ID)
    * Monitoring
- Architecture Document
- Support Team

---
- Component Architecture
  - Application type: web api, mobile, console and service etc
  - Technology Stack
    - Can perform the task.
    - Use stackoverflow tag to check community size and activity.
    - Google trend for popularity check.
    - Developer's opinion
  - Layers (Presentation/Business/DAL/Exception Handling)
    Layers run in single process, different concept from tiers.
  - API
    - Only contain entities in url, from the out most one to inner.
  - Interface/DI/SOLID
  - Design Pattern (Enhance Mantainabylity)
    * Factory/Repository/Facade/Command
  - Naming Convention
  - Exception Handling
    * Catch exception only if you have something to do with it
    * Catch only specific exception
    * Catch smallest code fragment if possible
  - Logging
