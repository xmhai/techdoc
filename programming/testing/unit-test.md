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
- Create: Menu File -> New -> Java ->JUnit Test Case...
- Error: "The input type of launch configuration does not exist" when right click on test class and run as "Junit Test" .  
  Reason is the test class is not created under src/test/java folder.  
- @ExtendWith({ExecutionContextExtension.class})  
  e.g.   @ExtendWith(SpringExtension.class)
- **Extension points**  
  - **Conditional Test Execution**  
    ExecutionCondition makes the test case to run on the conditions. e.g. properties  
  - **Life-cycle callbacks**  
    BeforeAllCallback, AfterAllCallback, BeforeEachCallback, AfterEachCallback  
  - **Parameter resolution**  
    ParameterResolver to resolve the constrcutor parameters.  
  - **Exception handling**  
    TestExecutionExceptionHandler  
  - **Test instance postprocessing**  
    TestInstancePostProcessor  

## Integration Test
use failsafe plugin.  
https://www.baeldung.com/maven-integration-test  
- Create test cases like xxxIT.
- mvn verify

## easy-random
To generate random data in objects.  

## Spring Boot Test
if a test requires starting up Spring in order to run such as @WebMvcTest , it is not a unit test but an integration test.  
@SpringBootTest, @ContextConfiguration, @DataJpaTest and @WebMvcTest are all involved in loading a subset of Spring components. They are in consequence related to integration testing.  
- @SpringBootTest with TestRestTemplate
  Integration-test for Spring Boot application.  
- @WebMvcTest with MockMvc  
  Test for controller layer.  
  - MockMvc
  - MockMvcRequestBuilders.get|post  
  - MockMvcResultMatchers.content|status|view  
- Use @SpringJUnitConfig to write unit-test.  
- Use @DataJpaTest to write test for persistence layer.  
- Use @MockBean for integration service.  
```java
// bootstrap the entire container
@SpringBootTest

// Annotation configuration, define additional beans or customizations for a test
@TestConfiguration
public class xxxConfig {...}
@Import(xxxConfig.class) // import above configuration class

// Test skeleton
// Refer to <<JUnit in Action>> pg 365
// Refer to <<Spring Boot in Action>> pg 82
@AutoConfigureMockMvc
public class RestApplicationTest {
  @Autowired
  private MockMvc mvc;
  // Mock Spring Bean
  @MockBean
  UserRepository mockRepository;
  // usage in code
  @Test
  void testXXX () {
    Mockito.when(mockRepository.count()).thenReturn(123L);

    // Verify status code
		mvc.perform(MockMvcRequestBuilders.get("/"))
			.andExpect(MockMvcResultMatchers.status().isOk());

    // Verify JSON
    mvc.perform(get("/countries"))
      .andExpect(status().isOk())
      .andExpect(jsonPath("$.name", is("Peter Michelsen")))
      ...;

    // Verify MVC
    mvc.perform(get("/"))
      .andExpect(status().isOk())
      .andExpect(view().name("readingList"))
      .andExpect(model().attribute("reader", samePropertyValuesAs(expectedReader)))
      .andExpect(model().attribute("books", hasSize(0)));

    // verify the mock method is call 1 time
    Mockito.verify(passengerRepository, times(1)).save(passenger);
  }
}

  // SpringJPATest???
  ```

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

