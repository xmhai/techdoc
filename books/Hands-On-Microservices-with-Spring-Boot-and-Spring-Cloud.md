## Design Patterns:
- Service Discovery
- Edge Server
- Central Configuration
- Centralized log analysis
- Centralized monitoring and alarms
- Distributed tracing
- Control loop
- Circuit Breaker
- Reactive Microservice: Whenever feasible, use an asynchronous programming model (Spring Reactive)

## Project Structure
- api project to store application API definitions includes api interfaces and DTOs (debatable???)
- util project to hold some shared help classes, e.g. error handling, each microservice can choose what version to use.
- composite service (backend for frontend pattern), consists of:-
  - api implementation
  - integration component
- Error handling: (SpringBoot has a default ExceptionHandler which returns json) 
  - @RestControllerAdive
- Integration test (@SpringBootTest)
  - Test case for each API
  - Use @MockBean for Integration Service
  - Use embedded database for unit test (h2 for jpa, flapdoodle for mongodb)
- Unit Test for Controller (@WebMvcTest(SomeController.class))
- Add Dockerfile/application.yml(add docker profile)/docker-compose.yml(specify profile in env)
- Add Swagger
- Persistence
- Reactive
- Spring-cloud-stream

## Best Practice
- Log connection information in SpringApplication
- delete API should be idempotent, i.e. return 200 even record not found
- Read opertation use non-blocking synchronous call, while create and update use event-driven asynchronous service
- One topic for one entity, messages contains event type (create/delete/update), key, body, timestamp

## Hands-on
- Create microservice projects from spring boot initializer.
  - Add controllers
  - Add persistences
  - Add services
- Create api and util library project (https://spring.io/guides/gs/multi-module/#scratch).
  - Use Eclipse to create a maven project (simple)
  - Replace the pom.xml with the one generated from spring boot initializer (keep the artifact information)
  - Remove build section and only keep spring-boot-starter dependency
  - Add .gitignore

## Programming
- Filter
- @IdClass/@EmbeddedId
- ReactiveCrudRepository
- Mono/Flux
  - subscribe(): usually it is the Spring WebFlux to initiate the stream processing
  - block()
- Spring Cloud Stream
- api format
  /product/{id} (@PathVariable) vs /review?productId=1 (@RequestParam)
- InetAddress  
  get hostname and ip
- ZonedDateTime
- @ResponseStatus
- RestTemplate  
  getForObject vs exchange: exchange() for List which is a generic class so RestTemplate doesn't know how to map
- Mockito  
  @MockBean/Mockito.when
- Collections.singletonList  
  immutable list contains only one element.
- WebClient/WebTestClient
  - Timeout on blocking read for 5000000000 NANOSECONDS  
    https://stackoverflow.com/questions/47286975/timeout-on-blocking-read-for-5000-milliseconds-in-spring-weblux
- @RestControllerAdive  
  - put Exception handler so it can catch any exception raised from controller.
- Persistence Test: @DataMongoTest/@DataJpaTest
  - MongoDb unique index annotation default is not enable from Spring Data 3.0, turn on in application.properties  
    spring.data.mongodb.auto-index-creation: true
- When JUnit is not working for generated class
  - Add folder /target to Run -> Run Configuration...-> Classpat tab-> User Entries -> Advance... -> Add folder
  - run "mvn clean compile test-compile" to make sure that generated classes are compiled to classes folder
- How to solve multiple service consistency issue??? Reactive???

## Docker
Use JDK11 for docker as JDK8 does not honor the docker memory and cpu constraints (Tested but seems issue fixed in latest version Nov 2021)  
- jshell
  - Runtime.getRuntime().availableProcessors()
- java
  - Get memory allocated: $java -XX:+PrintFlagsFinal -version | findstr MaxHeapSize
- connect to mysql docker
  docker-compose exec mysql mysql -uuser -p review-db
  docker-compose exec mysql mysql -uuser -p review-db -e "select * from reviews"
- connect to mongodb docker
  docker-compose exec mongodb mongo --quiet
  docker-compose exec mongodb mongo product-db --quiet --eval "db.products.find()"
  docker-compose exec mongodb mongo recommendation-db --quiet --eval "db.recommendations.find()"

## Reactive
- Replace CurdRepository with ReactiveRepository (For mongodb)  
  Methods will return Mono<T> after that.
- Maven dependency spring-boot-starter-data-mongodb change to spring-boot-starter-data-mongodb-reactive  
  Otherwise ReactiveRepository will not be scanned
- Caller of ReactiveRepository use Mono<T>.block() to return data

## Spring Cloud Stream
