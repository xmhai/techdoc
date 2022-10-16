- Spring default use logback  
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
- lombok @Slf4j
- log4j
