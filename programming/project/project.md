## Developer Onboarding  
https://dzone.com/articles/reduce-onboarding-costs-using-a-code-repository?edition=702393

## Project Parent pom  
```xml
<Properties> java version etc. </Properties>  
<DependencyManagement>
<Dependencies>lombok, swagger, apache-common, testing</Dependencies>
<PluginManagement>
<Plugins>common plugins like formatter, jacoco, pmd, surefire, failsafe, sonarqube, spring-boot-maven etc.</Plugins>
<Repository>
```

## Application.properties
- Two levels, e.g.  
  ```yaml
  server:
  spring:
    security:
  logging:
    level:
  ```
- Always put environment specific properties in env profile, dev/sit/uat/prod

## Common Library
- Base classes
- Beans with @ConditionOn for different type of services
- Entities & Repositories (must be named)
- Utility classes
- In pom, set spring-boot-maven to skip

## Standard folders
- api package name:   com.company.lbu.project.api.module
- batch package name: com.company.lbu.project.batch.module
- api/request|response, controller, service, repository, model, mapper
- event
- config, exception, constants, util

## API
Read more [api.md](../api/api.md)
- Swagger
- Request Object validation
- CommonError response
- BaseController handle validation Error (ControllerAdvice)
- MessageId
- Pagination  
Read more [pagination.md](../db/pagination.md)

## Batch
Read more [api.md](../batch/batch.md)

## Dashboard
https://mariadb.com/kb/en/data-warehousing-summary-tables/  

## Kafka

## Authentication

## MakerChecker
- MakerCheckerEntity

## DB Design
Read more [db-design.md](../data/db-design.md)
- BaseEntity (id, createdBy, createdAt, modifiedBy, modifiedAt)
- Use Enum (Enumerated.STRING)
