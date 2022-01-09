## Mockito  
- import  
  ```java
  import org.mockito.Mock;
  import org.mockito.junit.jupiter.MockitoExtension;
  import static org.mockito.Mockito.doThrow;
  import static org.mockito.Mockito.when;
  ```
- Annotation  
  - @ExtendWith(MockitoExtension.class)
  - @Mock
- Mock return value
  ```java
  when(mockStream.read()).thenReturn(-1);
  ```
- lenient()  
  modify the strictness of object mocking, allow different output for the same method by parameters. e.g.:  
  ```java
  Mockito.lenient()
    .when(mockAccountManager.findAccountForUser("1"))
    .thenReturn(senderAccount);
  Mockito.lenient()
    .when(mockAccountManager.findAccountForUser("2"))
    .thenReturn(beneficiaryAccount);
  ```
- Mock InputStream  
  ```java
  when(mockStream.read()).thenReturn((int) 'W')
    .thenReturn((int) 'o')
    .thenReturn((int) 'r')
    .thenReturn((int) 'k')
    .thenReturn((int) 's')
    .thenReturn((int) '!')
    .thenReturn(-1);
  ```
- Mock Exception  
  ```java
    doThrow(new IOException( "cannot close" ))
      .when(mockStream).close();
  ```