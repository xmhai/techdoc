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

## Common Library
- Base classes
- Beans with @ConditionOn for different type of services
- Entities & Repositories (must be named)
- Utility classes
- In pom, set spring-boot-maven to skip

## Standard folders
- package name: com.company.lbu.project.module
- api/[api]/request|response, controller, service, repository, entity, config, util
- mapper

## API
Read more [api.md](../api/api.md)
- Swagger
- Request Object validation
- CommonError response
- BaseController handle validation Error
- MessageId

## Batch
Read more [api.md](../batch/batch.md)

## DB Design
Read more [db-design.md](../data/db-design.md)
- BaseEntity (id, createdBy, createdAt, modifiedBy, modifiedAt)
- Use Enum (Enumerated.STRING)

