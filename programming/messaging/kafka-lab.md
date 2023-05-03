## Kafka Server setup
https://kafka.apache.org/quickstart  
installation folder /opt/kafka

Create test-topic with 3 partitions:  
```sh
bin/kafka-topics.sh --create --topic my-topic --bootstrap-server localhost:9092 --partitions 3
```

## Kafka Topic Monitoring
https://towardsdatascience.com/overview-of-ui-monitoring-tools-for-apache-kafka-clusters-9ca516c165bd  
Use Offset Explorer as it is easy to install on windows.

## Create Kafka Producer

## Create Kafka Consumer
Use default Configuration
- factory.setConcurrency(3);  
  this will create 3 threads for KafkaListenerContainer, each Listener will be assigned a partition (if there are 3 partitions as in this lab).

- Stop the consumer will not cause the Consumers disappear.

- Offset is maintained at Kafka Server side for partition and the consumer group. 

- When an exception happened in KafkaListener logic, offset is commited before the failed record. Then the ErrorHandler is triggered.

- Default retry attempt is defined as a FixedBackOff(interval=0, maxAttempts=10), after retry, the consumer will ack the message, and the commit offset will be updated to this failed record. This can be named as Retry and Ignore strategy.

- Default behaviour provided by error handler is to log exception and commit offset.  

- AckMode.BATCH only commit after batch process finished. This can be proved by close Eclipse during processing, commit offset doesn't change.

- AckMode.MANUAL_IMMEDIATE works as infinite re-try if there is no ack in the fallback logic.
