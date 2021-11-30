## Autoconfiguration
https://www.marcobehler.com/guides/spring-boot
Basically, 
- it lookup all the Configuration class in org.springframework.boot:**spring-boot-autoconfigure.jar**. Also inside **spring.factories**, under the META-INF folder.
- Evaluate all the @Conditional on these Configuration classes.
- Dependency is on spring-boot-starter

## Exception Handling  
https://dzone.com/articles/spring-boot-exception-handling?edition=703410  
https://github.com/SeunMatt/smattme-tutorials/blob/master/spring-boot-exception-handling/src/main/java/com/smattme/springbootexceptionhandling/GlobalExceptionHandler.java  

https://www.baeldung.com/exception-handling-for-rest-with-spring  

- Exception Handler (@ControllerAdvice)
- ErrorController
  An implementation of the ErrorController is used to provide a custom whitelabel error page.
  Spring Boot provides an ErrorController implementation to handle errors in a sensible way. In a nutshell, it serves a fallback error page for browsers (a.k.a. the Whitelabel Error Page) and a JSON response for RESTful, non-HTML requests:
  - Custom ErrorController with @RequestMapping("/error")
