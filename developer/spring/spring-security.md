## Architecture
https://docs.spring.io/spring-security/site/docs/5.2.6.RELEASE/reference/html/overall-architecture.html  

## Customization
```java
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    // user authentication
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // four ways to authenticate:
        // In-memory user store
        auth.inMemoryAuthentication()
        // JDBC-based user store
        auth.jdbcAuthentication()
        // LDAP - or
        auth.ldapAuthentication()
        // Customizing user authentication with following classes:
        // public class User implements UserDetails
        // public class UserRepositoryUserDetailsService implements UserDetailsService
        auth.userDetailsService(userDetailsService);
        //lookup user
        //check password (passwordEncoder)
        //get authorities (roles)
        ...
    }

    // Secure HTTP request
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        ...
    }
}
```

## CSRF
- enabled by default  
- The hidden field will be rendered automatically for you.  
  just need to make sure that one of the attributes of the \<form\> element is prefixed as a Thymeleaf attribute. e,g, action attribute below  
  ```html
  <form method="POST" th:action="@{/login}" id="loginForm">  
  ```

## Get user information in Java
There are four ways to do that:
```java
// Inject a Principal object into the controller method
public String controllerMethod(..., Principal principal) {
    User user = userRepository.findByUsername(principal.getName());    
}
// Inject an Authentication object into the controller method
public String controllerMethod(..., Authentication authentication) {
    User user = (User) authentication.getPrincipal();
}
// Use an @AuthenticationPrincipal annotated method
public String controllerMethod(..., @AuthenticationPrincipal User user) {
}
// Use SecurityContextHolder to get at the security context
```