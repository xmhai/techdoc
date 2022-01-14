## Annotation
- @RequestMapping  
  use it at class level.  
- @GetMapping/@PostMapping/@DeleteMapping
- @Valid
- @SessionAttributes  
  - Class-level @SessionAttributes annotation  
    specifies any model objects like the order attribute that should be kept in session.  
  - Cleanup session  
    SessionStatus sessionStatus;  
    sessionStatus.setComplete();
- @ModelAttribute  
  - Parameter-level @ModelAttribute  
    indicate that its value should come from the model and that Spring MVC shouldn’t attempt to bind request parameters to it.  
  - Method-level @ModelAttribute  
    ensures that an model object will be created in the model.  

## ModelView
- Before Spring hands the request over to a
view, it copies the model data into request attributes.  

## Validation
- Validation API and the Hibernate implementation of the Validation API are automatically added to the project.  
- javax.validation.constraints.Pattern for regex validation.  
```java
// sample validation
public String processOrder(@Valid Order order, Errors errors) {
    if (errors.hasErrors()) {
        return "orderForm";
    }
    ...
}
```
```html
<!-- Display validation error -->
<span class="validationError" th:if="${#fields.hasErrors('ccNumber')}" th:errors="*{ccNumber}">CC Num Error</span>
```

## Redirect vs. Forward
Forward:
- The request will be further processed on the server side
The client isn't impacted by forward, URL in a browser stays the same
- Request and response objects will remain the same object after forwarding. Request-scope objects will be still available.
    
Redirect:
- The request is redirected to a different resource
The client will see the URL change after the redirect
A new request is created.
- Redirect is normally used within Post/Redirect/Get web development pattern.  

## Custom WebConfig
for example, a simple view controller can be defined as followng:  
```java
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("home");
    }
}
```
## Template Engine
- JSP is only an option if you’re building your application as a WAR file and deploying it in a traditional servlet container because Tomcat look for JSPs somewhere under /WEB-INF which does not exist in JAR deployment.  
- Disable cache in development  
  spring.thymeleaf.cache=false

