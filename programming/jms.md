# Concept
## Pattern
- Queue
- Topic
- Acknowledge Mode
  - AUTO_ACKNOWLEDGE
  - INIDIVIDUAL_ACKNOWLEDGE 

# Sample Code
```java
    private Session createActiveMqSession() throws Exception {
        Connection connection = connectionFactory.createConnection();
        connection.start();
        return connection.createSession(false, AUTO_ACKNOWLEDGE); // INIDIVIDUAL_ACKNOWLEDGE
    }


    private void pushNewUserToQueue(User newUser) throws Exception {
        Session session = createActiveMqSession();
        Destination destination = session.createQueue(QUEUE_NAME);
        MessageProducer producer = session.createProducer(destination);

        ObjectMessage message = session.createObjectMessage(newUser);
        producer.send(message);
    }

    private void pollUserFromQueue() throws Exception {
        Session session = createActiveMqSession();
        Destination destination = session.createQueue(QUEUE_NAME);
        MessageConsumer consumer = session.createConsumer(destination);
        Message message = consumer.receive();

        ObjectMessage textMessage = (ObjectMessage) message;
        User user = (User) textMessage.getObject();
        creatingUser(user);
        // message.acknowledge(); // INIDIVIDUAL_ACKNOWLEDGE mode
    }
```