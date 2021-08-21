- Stream API
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

  