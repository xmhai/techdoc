## Concept
https://coderanch.com/wiki/718795/Unit-Testing
https://www.testim.io/blog/unit-test-vs-integration-test/  
A test is not a unit test if:
- It talks to the database
- It communicates across the network
- It touches the file system
- It can’t run at the same time as any of your other unit tests
- You have to do special things to your environment (such as editing config files) to run it

Also quote from Spring doc:  
>True unit tests typically run extremely quickly, as there is no runtime infrastructure to set up.  

## JUnit
- Error: "The input type of launch configuration does not exist" when right click on test class and run as "Junit Test" .  
  Reason is the test class is not created under src/test/java folder.  

## easy-random
To generate random data in objects.  

## Spring Boot
if a test requires starting up Spring in order to run such as @WebMvcTest , it is not a unit test but an integration test.  
@SpringBootTest, @ContextConfiguration, @DataJpaTest and @WebMvcTest are all involved in loading a subset of Spring components. They are in consequence related to integration testing.  
- Use @SpringJUnitConfig to write unit-test.
- Use @WebMvcTest with MockMvc to write test for controller layer.
- Use @DataJpaTest to write test for persistence layer.
- Use @SpringBootTest with TestRestTemplate to write integration-test for Spring Boot application.
- Use @MockBean for integration service.

## Best Practice
- Maven skip integration test cases  
  @Ignore or 
- Use Apache Common IO for file manipulation
- Use Spring ReflectionTestUtils.setFields to change private field value
- Load test resource  
  input = this.getClass().getResourceAsStream("/data/test_data.txt");
  String data = IOUtils.toString(input, StandardCharsets.UTF-8);
- Random data for bean
  random-easy
- Create instance for private constructor class  
  ```java
  Constructor cxor = CommonUtl.class.getDeclaredConstructor();  
  cxor.setAccessible(true);  
  cxor.newInstance();  
  ```
- Unit test for bean  
  BeanUtils.copyProperities  

## Unit Test Best Practice
- Test only what could possibly break.  
- Exclude all bean classes and Spring configuration classes.  
- Exclude the generated classes (depends)  
- Mock vs Stub: Stub has it own logic, response is fixed. e.g. Jetty server or h2 database.  
- Refactor the class for unit test if necessary.  

