## Stream API
https://stackify.com/streams-guide-java-8/
  - map(*lambda*)
  - filter()
  - reduce(*identity*, *accumulator*[*lambda* or *Method Reference*], *combiner*)  
    e.g. 
    ```java
    List<User> users = Arrays.asList(new User("John", 30), new User("Julie", 35));
    int result = users.stream().reduce(0, (partialAgeResult, user) -> partialAgeResult + user.getAge(), Integer::sum);
    ```
    reduction stream operations allow us to produce one single result from a sequence of elements  
    predefined reduction operations: sum(), count()  


## Thread programming
- Related methods
  - wait()/notify()/notifyAll()
  - start()/join() - caller thread will wait until the thread exited.
  - sleep()
- Thread safety can be achieved in following ways:  
  - sychronized (method/block/static method sychronized)
  - volatile
  - wait()/notify()/notifyAll()
  - Concurrent wapper
- Thread Pool
- Thread Usage in Java Projects
  - In memory cache
  - Singleton
  - Controller state
  - Multiple JMS  
    setConcurrentConsummer or use taskExecutor?
  - Batch processing
