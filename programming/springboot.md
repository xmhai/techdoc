## Autoconfiguration
https://www.marcobehler.com/guides/spring-boot
Basically, 
- it lookup all the Configuration class in org.springframework.boot:**spring-boot-autoconfigure.jar**. Also inside **spring.factories**, under the META-INF folder.
- Evaluate all the @Conditional on these Configuration classes.
- Dependency is on spring-boot-starter