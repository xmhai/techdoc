- Batch (Microbatch) processing vs Stream Processing
  - Batch processing is not enough to get fast response  
- PageRanking
- MapReduce

## Concept
- Stream
- Processor (processing nodes)
- Topic  
  Any number of applications can read from same topic.  
- Backpressure
- DAG (Directed Acyclic Graph)
  Vertex, Edge(arc)

## Kafka Stream Program Structure
1. Define the configuration items.  
2. Create Serde instances, either custom or predefined.  
3. Build the processor topology.  
   1. Create a source node.  
4. Create and start the KStream.  
