## Concept
- Auto-configuration by detecting libraries in classpath.  
- Starter Dependendency by functionality (web, security...).  

## Auto-configuration
https://www.marcobehler.com/guides/spring-boot  
How auto-configuration is accomplished:  
- Leverages conditional configuration features.
- Transitive dependency resolution offered by Maven.  

How it works, Basically:  
- it lookup all the Configuration classes from:  
  - @Configuration class in **spring-boot-autoconfigure.jar**.  
  - Inside **spring.factories** file under the META-INF folder.
- Evaluate all the @Conditional on these Configuration classes.
- Dependency is on spring-boot-starter

## Customized Configuration
override Spring Boot auto-configuration. e.g. SecurityConfig
- Explicit configuration
  ```java
  @Configuration
  @EnableWebSecurity
  public class SecurityConfig extends WebSecurityConfigurerAdapter
  ```
- External properties  
  - property files (application.yml can be internal or external)  
    spring.main.show-banner=false  
  - environment variables  
    export spring_main_show_banner=false // use underscore
  - Java system properties  
  - Command line arguments  
    java -jar helloworld-0.0.1-SNAPSHOT.jar --spring.main.show-banner=false
- @ConfigurationProperties("amazon")

## Configuration for Different Environment
- Use spring.profiles.active=xxx
- Setting a system environment variable in production env
- Use external application.properties in different environment

## Logging
To use log4j need to exclude logback and add starter-log4j dependency.   

## Exception Handling  
https://dzone.com/articles/spring-boot-exception-handling?edition=703410  
https://github.com/SeunMatt/smattme-tutorials/blob/master/spring-boot-exception-handling/src/main/java/com/smattme/springbootexceptionhandling/GlobalExceptionHandler.java  

https://www.baeldung.com/exception-handling-for-rest-with-spring  

- Exception Handler (@ControllerAdvice)
- ErrorController
  An implementation of the ErrorController is used to provide a custom whitelabel error page.
  Spring Boot provides an ErrorController implementation to handle errors in a sensible way. In a nutshell, it serves a fallback error page for browsers (a.k.a. the Whitelabel Error Page) and a JSON response for RESTful, non-HTML requests:
  - Custom ErrorController with @RequestMapping("/error")

## Testing
Add maven-failsafe-plugin to pom  
```java
@SpringBootTest  

@Test
class xxxIT {}
```

## Acurator
Is disabled by default.  
http://localhost:8080/actuator  

## Deployment
- war
- jar

## Q&A
- If there are multiple 