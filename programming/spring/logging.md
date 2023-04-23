- Logging Frameworks  
  https://medium.com/javarevisited/understanding-logging-in-spring-boot-ac0fd79177b4  
  log4J, logback, java.util.logging (JUL), tiny log, etc.  

- By default, Spring Boot includes SLF4J along with Logback implementations.

- Slf4j  
  An abstraction of all these logging frameworks. It offers a generic API, making the logging independent of the actual implementation.

- Logback  
  Logback is already using SLF4J. It's the reference implementation.
  Can be configured logback.xml or in application.yml:  
  ```xml
    <configuration>
        <appender name="STDOUT" class="ch.qos.logback.core.ConsoleAppender">
            <encoder>
                <pattern>
                %d{HH:mm:ss.SSS} [%thread] %-5level %logger{36} - %msg%n
                </pattern>
            </encoder>
        </appender>
        <logger name="root" level="INFO"/>
        <root level="INFO">
            <appender-ref ref="STDOUT" />
        </root>
    </configuration>
  ```
  ```yaml
    logging:
      level:
        root: WARN
        org.springframework.security: DEBUG
  ```

- log4j
  https://www.callicoder.com/spring-boot-log4j-2-example/  
  support Async

- lombok @Slf4j
  Generate log for a class.
