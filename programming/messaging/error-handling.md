## Error Handling Strategy
https://blog.consdata.tech/en/2021/09/13/kafka-retry-dlq.html  
https://www.baeldung.com/spring-retry-kafka-consumer  

Depends on the nature of messages:
- Can we afford message lost
- Is the order of message must be guarantee

Causes of error:
- Wrong message format or data  
  Cannot retry, just move to DLQ
- Program error (NullPointer)
  Cannot retry, just move to DLQ
- Temporary error (Network down or database down)
  Should retry until success.

Following are the options (last two options are heavily inspired by the way Kafka is used by Uber in their systems):  
- Ignore (Lack of error handling)  
  it is important to have effective monitoring which can detect a situation when the loss of messages exceeds some arbitrarily determined level.
- Infinite retry  
  in this scenario, having constant monitoring is even more important.
- Finite retry at the point of an error
- Finite retry on a separate topic

## Implementation
- Kafka is "dump broker, smart endpoint", thus DLQ is not handled by broker.  
- 
