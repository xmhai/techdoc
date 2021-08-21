## Cache 
https://programmerfriend.com/ultimate-guide-to-redis-cache-with-spring-boot-2-and-spring-data-redis/  
Cacheable Annotation
- key  
  - Spring Expression Language (SpEL) expression for computing the key dynamically.  
  - Default is "", meaning all method parameters are considered as a key.  
  - The SpEL expression evaluates against a dedicated context that provides the following meta-data: #root.method, #root.target, #root.args[1], #p1 or #a1...
  - https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/cache/annotation/Cacheable.html
- value  
  Name of cache. 
- For Redis, key will be in the format of "*value::key*"

Implementation
- Configure application.properties
- Add @EnableCaching to Application class
- Add @Cacheable, @CacheEvict, @CachePut to methods
- Spring Boot starter provides a simple cache provider which stores values in an instance of ConcurrentHashMap

---
## Swagger
Can use Swagger Maven plugin to generate HTML from open API Json  
swagger2markup-maven-plugin/asciidoctor-maven-plugin  
https://dzone.com/articles/static-api-documentation-with-spring-and-swagger  

---
## Properties File (application.properites/application.yml)
- Multiple Profiles
  - Multiple profiles can be included in application.yml file.
  - Need to create mulitple files if properties format is used.
- Self-defined properties
  - Can be parsed into a configuration class through @ConfigurationProperties(prefix = "cache.app-caches")