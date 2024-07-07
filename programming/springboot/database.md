- Springboot will create datasource bean and try to connect to database IF there is jpa Entity class defined in project.
- Need to include javax.persistence-api and javax.transaction-api in Springboot 2 project pom.xml
- Spring Boot 3.0 makes use of Jakarta EE 9 APIs (jakarta.) instead of EE 8 (javax.). imports with javax.* and replace them with jakarta.*.  
  https://stackoverflow.com/questions/73350585/upgrade-from-spring-boot-2-7-2-to-spring-boot-3-0-0-snapshot-java-package-java  
- Application will show "HikariPool-1 - Start completed."  

HikariCP
- hikaricp.connections.min: 10  (default value 10)
- hikaricp.connections.max: 10  (default value 10)
- hikaricp.connections: 10      (current pool size)
- hikaricp.connections.idle: 10 (the number of idle connections in pool)
- hikaricp.connections.active: 0   (the number of connections in use, active + idle = pool size)
- hikaricp.connections.pending: 0  (the number of requests waiting for getting the connection from pool)
- hikaricp.connections.timeout: 4  (total timeout connection, this happen when all the connections are used, and the rest connection requests will wait for 30 seconds and throw timeout error)
- hikaricp.connections.creation: time to create a physical connection to db
- hikaricp.connections.acquire: time to acquire a connection from pool
- hikaricp.connections.usage: time to use the connection

http://localhost:8080/actuator/metrics/hikaricp.connections.usage:  
```json
{
    name: "hikaricp.connections.usage",
    description: "Connection usage time",
    baseUnit: "seconds",
    measurements: [
        {
            statistic: "COUNT",
            value: 1
        },
        {
            statistic: "TOTAL_TIME",
            value: 0.028
        },
        {
            statistic: "MAX",
            value: 0
        }
    ],
    availableTags: [
        {
            tag: "pool",
            values: [
                "HikariPool-1"
            ]
        }
    ]
}
```
