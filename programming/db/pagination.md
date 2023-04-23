## Useful links
https://frontbackend.com/thymeleaf/spring-boot-bootstrap-thymeleaf-datatable  
https://docs.spring.io/spring-data/rest/docs/2.5.9.RELEASE/reference/html/#paging-and-sorting

## Spring Web standard way
https://docs.spring.io/spring-data/rest/docs/2.5.9.RELEASE/reference/html/#paging-and-sorting
- Query string  
  ?page=0&size=20&sort=id&sort=name,desc
- Controller use Pagable pagable  
  ```ini
  # Configuration 
  spring.data.web.pageable.one-indexed-parameters=true
  ```

  ```java
  import org.springframework.data.domain.Page;
  import org.springframework.data.domain.Pageable;
  public Page<Customer> getCustomers(@ParameterObject Pagable pagable) {...}
  ```
