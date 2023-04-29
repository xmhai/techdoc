## Thread Model  
https://www.madhur.co.in/blog/2020/08/30/kafka-consumer-multithreading.html  
https://stackoverflow.com/questions/55241976/how-can-i-process-kafkalistener-method-in-different-threads  
@KafkaListener
- Using an executor makes things complicated, with regard to managing committed offsets; it is not recommended. This means single thread per listener.
- With @KafkaListener, the framework creates a ConcurrentKafkaListenerContainerFactory for you. 
- concurrency on the annotation is just a convenience; it overrides the factory setting. This allows you to use the same factory with multiple listeners, each with different concurrency.
- increase the concurrency ONLY if you have at least that number of partitions on the topic.

## Poll Interval  
https://stackoverflow.com/questions/48402355/spring-kafkalistener-execute-and-poll-records-after-certain-interval  

## Configuration
- poll  
https://stackoverflow.com/questions/51753883/increase-the-number-of-messages-read-by-a-kafka-consumer-in-a-single-poll  
  ```yaml
  max.poll.records: 500 # maximum number of records return for a poll()
  fetch.max.bytes: 52428800 # Consumer config. The maximum amount of data the server should return for a fetch request.
  max.partition.fetch.bytes: 1048576 # Consumer Config. The maximum amount of data per-partition the server will return.
  message.max.bytes: 1000012 # Broker config
  max.message.bytes: 1000012 # Topic config
  ```
- commit
  ```yaml
  enable.auto.commit: false
  ```

## AckMode
https://www.baeldung.com/spring-retry-kafka-consumer  
There are three acknowledgment modes:  
- auto-commit: the consumer sends an acknowledgment to the broker as soon as it receives a message
- after-processing: the consumer only sends an acknowledgment to the broker after it has successfully processed the message
- manual: the consumer waits until it receives specific instructions before sending an acknowledgment to the broker

- BATCH (Default)  
  commit after all the records from a poll have been processed.
- RECORD (after-processing)  
  commit after each record is processed.
- MANUAL  
  any successful acks called before the failure are queued up and committed before calling the error handler with the remaining records.
- MANUAL_IMMEDIATE  
  offsets are committed immediately, as long as you call it on the listener thread.
- enable.auto.commit=true  
  It's best not to use this option. It won't commit until the next poll(), and then only if the the auto.commit.interval has passed.

## Classes
ConcurrentKafkaListenerContainerFactory (properties can be configure in application.yaml)
- setPollTimeout  
Determine how long the poll() will wait for new records to arrive. If new records arrive more often, it will not wait that long.
- idleBetweenPolls  
let the main loop in the listener container to sleep between KafkaConsumer.poll() calls.
- ackMode (BATCH|MANUAL_IMMEDIATE)  

KafkaMessageListenerContainer  
an event-driven, self-controlling wrapper around KafkaConsumer. It really calls poll() in a while (isRunning()) { loop, which is scheduled in a TaskExecutor.  
```java
```

## Best Practice
- There is a timeout even for calling poll() where if u don’t call poll for sometime (300000 ms) by default , the consumer was dying and also as we are using auto commit, offset was not committed and same records were again getting processed.  
To avoid this scenario: adjust max.poll.records and max.poll.interval.ms such that the listener will not exceed the latter when processing the results of the poll.  
