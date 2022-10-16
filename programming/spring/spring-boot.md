## Concept
- Auto-configuration by detecting libraries in classpath.  
- Starter Dependendency by functionality (web, security...).  

---
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

---
## Customized Configuration (bean)
override Spring Boot auto-configuration. e.g. SecurityConfig
- Explicit configuration
  ```java
  @Configuration
  @EnableWebSecurity
  public class SecurityConfig extends WebSecurityConfigurerAdapter
  ```
---
## Customized Configuration (properties)

## Properties
Spring environment pulls from several property sources, including:  
- JVM system properties
- Operating system environment variables (naming style using underscore: SERVER_PORT)
  export spring_main_show_banner=false // use underscore
- Command-line arguments  
  java -jar helloworld-0.0.1-SNAPSHOT.jar --spring.main.show-banner=false
- Application property configuration files (application.yml can be internal or external)

## Properties File (application.properites/application.yml)
- Multiple Profiles
  - Multiple profiles can be included in application.yml file.
  - Need to create mulitple files if .properties format is used.
- Self-defined properties
  - Can be parsed into a configuration class through @ConfigurationProperties(prefix = "cache.app-caches")
  - In fact, @ConfigurationProperties are often placed on beans whose sole purpose in the application is to be holders of configuration data.

---
## Customized Configuration (environment)
There are two ways to set configuration for diffrent environments:  
1. Environment Variables (Traditional way)  
   Cumbersome and cannot track the changes.  
2. Use external application.properties for different environment (old Spring)  
   External application.properties will override the bundled version. But hard to maintain application.properties across different env.  
3. Multiple profile in application.yaml, and set --spring.profiles.active=env

---
## Logging
To use log4j, need to exclude logback and add starter-log4j dependency.   

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
- How to build a common jar library?  
  https://spring.io/guides/gs/multi-module/  
  https://stackoverflow.com/questions/32696080/how-to-use-spring-boot-making-a-common-library/45060285  

