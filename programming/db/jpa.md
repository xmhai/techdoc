## Useful links
- https://www.jpa-buddy.com/blog/spring-data-jpa-interview-questions-and-answers/
- https://stackoverflow.com/questions/38509882/how-are-spring-data-repositories-actually-implemented/38511337#38511337
- https://stackoverflow.com/questions/23563252/how-to-see-the-repository-implementation-generated-by-spring-data-mongodb
- Spring Offical Document: https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#dependencies

## Concept
- Runtime (thus no code generation)
- RepositoryFactorySupport create a proxy from the repository 
- Proxy has a MethodInceptor which intercept the calls and route to the appropriate implementations:
  - refer to this excellent article: https://stackoverflow.com/questions/38509882/how-are-spring-data-repositories-actually-implemented/38511337#38511337
- @EnableJpaRepositories to create the proxy instances

## Pratice

## Tips
- Springboot application will fail to start if database connection failed.
  - java.sql.SQLException will be thrown, and detailed error message is returned from db server.
  - Access denied for user 'root'@'localhost' (using password: YES):
    cause: passord is wrong.
  - Access denied for user 'root'@'localhost' (using password: NO):
    cause: password is null, due to wrong spring yaml configuratioan.
  - Communications link failure:
    cannot access db ip and port, could due to wrong ip address or db server not up.
  - Host 'OptiPlex5000.lan' is not allowed to connect to this MariaDB server:
    cause: access from any server, e.g. hai@% is not set.
