## Architecture
https://docs.spring.io/spring-security/site/docs/5.2.6.RELEASE/reference/html/overall-architecture.html  

## Concept
- Security Filter Chain (DefaultSecurityFilterChain)  
  ```java
    @Configuration
    @EnableWebSecurity(debug=true)
    public class OAuth2ClientConfig {
        @Bean
        public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
            http.authorizeRequests()...;
            return http.build();
        }
    }
  ```

- Filters (Inherit from OncePerRequestFilter -> GenericFilterBean)  
  Security Filter are injected when http.build() is called;

- SecurityContextHolder
  to provide access to the SecurityContext. User information stored using ThreadLocal.

- SecurityContext (Interface)
  to hold the Authentication and possibly request-specific security information.

- Authentication (Interface)  
  Sample implementation: UsernamePasswordAuthenticationToken
  ```java
  getPrincipal();
  getAuthorities();
  ```

- Principal  
  to represent the principal in a Spring Security-specific manner.
  Usually an instance of UserDetails.

- UserDetails  
  UserDetails is a core interface in Spring Security. It represents a principal. Most authentication mechanisms within Spring Security return an instance of UserDetails as the principal.

- UserDetailsService (Interface)  
  ```java
  // the only interface method
  UserDetails loadUserByUsername(String username);  
  ```
  It is purely a DAO for user data. Most users tend to write their own UserDetailsService.

- GrantedAuthority  
  to reflect the application-wide permissions granted to a principal.

- AuthenticationManager (Interface)  
  validate Authentication, returns a fully populated Authentication instance on successful authentication.
  - Default implementation is ProviderManager
    delegates to a list of configured AuthenticationProvider. e.g DaoAuthenticationProvider, LdapAuthenticationProvider  
    - DaoAuthenticationProvider  
      leverages a UserDetailsService

## Debug
- @EnableWebSecurity(debug=true)  
  This will output detailed Request information and the security chain.
- Spring logging
  ```yaml
  logging.level.org.springframework.security: TRACE
  # for oauth2 code exchange
  logging.level.org.springframework.web.client.RestTemplate: DEBUG
  ```

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