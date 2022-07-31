## [Deprecated] SpringFox Swagger 3:  
https://www.baeldung.com/swagger-2-documentation-for-spring-rest-api  
Note:  
- Replace "extends WebMvcConfigurerAdapter" with "implements WebMvcConfigurer" as in java 8 WebMvcConfigurer interface provides the default implementation. So abstract class is no longer needed.
- Springfox 3.0.0 is not working with Spring Boot 2.6.0, Only adding @EnableWebMvc in main class resolved the problem.  

Endpoints:  
- OpenAPI endpoint:  
  v2/api-docs
- UI endpoint:  
  /swagger-ui/index.html or /swagger-ui/

## springdoc-openapi
https://springdoc.org/migrating-from-springfox.html  
Note:  
- Remove WebMvcConfigurer  

https://www.baeldung.com/spring-rest-openapi-documentation  

Endpoints:
- UI  
  http://localhost:8080/swagger-ui.html

## Tips
- Same class name need to set to different schema name, e.g. class User can annotated with @Schema(name="user1"), @Schema(name="user2")  

## tcases
Test against OpenAPI specification.  
https://github.com/Cornutum/tcases/blob/master/tcases-openapi/README.md