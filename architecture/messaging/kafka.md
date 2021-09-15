# Overview  
chrome-extension://efaidnbmnnnibpcajpcglclefindmkaj/viewer.html?pdfurl=https%3A%2F%2Fportworx.com%2Fwp-content%2Fuploads%2F2020%2F10%2Fexperts-guide-kafka-kubernetes.pdf&clen=663355&chunk=true  

- Distribute data across the cluster  
- Use key/value pairs and partitions to group distributed data  
- Replication (fault-tolerant)  

# Kafka Concept  
- Broker  
  Kafka is a message broker.
- Cluster
- Producer  
  send messages to Kafka, and those messages are stored and made available to consumers via subscriptions to topics.  
- Topic
- Message  
  messages are never pushed out to consumers.  
- Consumer

# Kafka Architecture  
## Topic (stored as log in brokers)  
- The mechanism underlying Kafka is the log, which is an append-only, totally ordered sequence of records ordered by time.
- Topics in Kafka are logs that are segregated by topic name. You could almost think of topics as labeled logs.  
- topic logs are stored in folder: logs/partition-name_partition-number. log files are rolled by size or message timestamp.  

## Partition  
- Achieve tremendous output by split topic into partitions. However, Determining the correct number of partitions is not an
exact science.  
- Distribute the message to partition by key or round-robin if key is null, or we can create customed partitioner.
- The distribution is happened at client side (Java code).  

## ZooKeeper: leader, followers and partitions  
- ZooKeeper elect the controller broker.  
- The controller broker is responsible for setting up leader/follower relationships for all partitions of a topic.
- leader and followers are decided for each topic partition.  
- If a Kafka node dies or is unresponsive (to ZooKeeper heartbeats), all of its assigned partitions (both leader and follower) are reassigned by the controller broker.  

## Client ID
The client ID prefix is the user-defined value that uniquely identifies clients connecting to Kafka.  

## Group ID
The group ID is used to manage the membership of a group of
consumers reading from the same topic, ensuring that all consumers in the group can effectively read subscribed topics.

## Log management  
- Deleting logs  
- Compaction  

## Best Pratice
- Create topic manually.
- At least two replicas for each topic.

## Installation
https://strimzi.io/blog/2018/06/11/deploying-kafka-on-kubernetes-with-local-storage-using-strimzi/  
