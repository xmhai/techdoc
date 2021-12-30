## @DataJpaTest
It will create a application context only with JPA related beans.

## Jacoco
https://www.baeldung.com/jacoco  

## beanio
Read from file (txt,csv,json/sml) and convert to bean.

## Mockito
Mock static method, return should only return the object.

## @Schedule
How schedule execution is stored???

## JPA @Column
Only used for SQL generation???

## JPA saveAll
https://www.baeldung.com/spring-data-save-saveall  
The performance difference is due to Transaction, if the saveAll() is called within a transaction, there should not be performance difference.  
https://www.baeldung.com/jpa-hibernate-batch-insert-update  
- Enabled JPA Batch Inserts:  
```ini
# Enable batch
spring.jpa.properties.hibernate.jdbc.batch_size=5
# For multiple tables (Parent/Children) insert, tell Hibernate to group insert by entity
spring.jpa.properties.hibernate.order_inserts=true
spring.jpa.properties.hibernate.order_updates=true
```
- Need to call entityManager.flush(); and entityManager.clear(); to clear Persistent Context.  
First of all, the persistence context stores newly created entities and also the modified ones in memory. Hibernate sends these changes to the database when the transaction is synchronized. **This generally happens at the end of a transaction**. However, calling EntityManager.flush() also triggers a transaction synchronization.  
Secondly, the persistence context serves as an entity cache, thus also referred to as the first level cache. To clear entities in the persistence context, we can call EntityManager.clear().