## Useful links  
(MUST Read!!!) https://www.jpa-buddy.com/blog/the-ultimate-guide-on-db-generated/  
https://www.baeldung.com/spring-jdbc-batch-inserts  
https://percona.community/blog/2018/10/12/generating-identifiers-auto_increment-sequence/  
https://docs.jboss.org/hibernate/core/3.3/reference/en/html/batch.html  

## Check batch mode  
generate_statistics: true

## Database support
Database need to support sequence and multivalue insert.  
Maridb 10.3 start supporting sequence. Need to set dialect to MariaDB103Dialect

## JDBC Batch
```java
// native way
   // https://docs.jboss.org/hibernate/core/3.3/reference/en/html/batch.html
// jdbc
stmt.addBatch(SQL);
int[] count = stmt.executeBatch();
// jdbcTemplate
jdbcTemplate.batchUpdate(...);
// hibernate
repository.saveAll(list);
```

## Hibernte Batch
```yaml
spring:
  jpa:
    properties:
      hibernate:
        generate_statistics: true # to view the actual JDBC call to verify batch is working
        jdbc.batch_size: 100 # 100 is an ideal value
        order_inserts: true
        order_updates: true
```
hibernate batch insert is disabled when ID generator is not Sequence.

## Spring Batch
- Spring Batch will stop when exception thrown, e.g. if database insert error, exception thrown from db will cause the batch failed
  Test with a trigger to fail some records, in single record mode, the batch stop on exception
- If batch insert/update is used, no exception will be thrown even some insert/update failed  
  Test with a trigger to fail some records, in batch mode, other records will be insert successfully.

## Best Practice
- Define the batch size, 100 is a good choice
- Create sequence INCREMENT BY 100
- Difine Entity @id use @SequenceGenerator with allocationSize=100
- The validation need to be done at Java side, server side should not throw exceptions.
- Spring Batch trunk defined as 100
- spring.jpa.properties.hibernate.batch_size=100
- rewriteBatchedStatements=true (https://www.reddit.com/r/java/comments/wk640s/a_lesserknown_mysql_jdbc_optimization_setting/)
- useServerPrepStmts=true (https://stackoverflow.com/questions/32286518/whats-the-difference-between-cacheprepstmts-and-useserverprepstmts-in-mysql-jdb/32645365#32645365)
- cachePrepStmts (https://vladmihalcea.com/how-does-the-mysql-jdbc-driver-handle-prepared-statements/)

## Statistics
One million record insert:  
| Batch Size  | Time (min)  |
| ----------: | ----------: |
|          1  |        26   |
|         10  |        17   |
|        100  |         9   |
|        200  |         7.5 |
|        500  |         7.75|
|       1000  |         7   |
