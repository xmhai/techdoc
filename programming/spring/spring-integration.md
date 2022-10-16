# API
- RestTemplate
- Traverson (hyperlink awared)  
- WebClient

## RestTemplate
- Prefer standard HTTP methods like getForObject()  
- Prefer URL parameters specified in a Map<String,String>  
- getForEntity() to get information in header etc.  

# Messaging
- JMS (ActiveMQ)
  Concept: destination, queue, topic
  - JmsTemplate
  - Message Driven POJOs
- AMQP (RabbitMQ)
  Concept: exchange, binding, queue
- Kafka
  Concept: cluster, partition, topic, pub/sub

## JMSTemplate
- Send
  - send()
  - convertAndSend()
  - MessageConverter
- Receive
  - receiving messages
    pull vs push: generally push is better. But in case of messages arrive too quickly, pull enables consumer to declare its readyness.  
  - receive()
  - receiveAndConvert()
  - @JmsListener

## AMQP
RabbitTemplate

