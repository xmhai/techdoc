## Batch Processing Requirements
- Bulk-oriendted (Long running)
- Background execution (Non-interactive)
- Support for multiple file formats, including fixed length, delimited files, XML and common database access using JDBC, and other prominent frameworks
- Automatic retry after failure 
- Job control language to monitor and perform common operations such as job start, stop, suspend, and cancel
- Tracking status and statistics during the batch execution and after completing the batch processing
- Support for multiple ways of launching the batch job, including script, HTTP, and message
- Support to run concurrent jobs
- Support for services such as logging, resource management, skip, and restarting the processing

## Batch Processing Framework Capability
- Invocation model:
    - Scheduled
    - Ad Hoc and on-demand (?)
- Chunk processing: 
- Restartablilty:
- Error Handling
  - logging
  - skipping records with errors
  - retry logic  
- Parallel processing

## Spring Batch Architecture
https://docs.spring.io/spring-batch/docs/current/reference/html/domain.html#domainLanguageOfBatch  

### JobLauncher
In the Spring Boot world, this is a component that you typically do not need to directly work with because Spring Boot provides facilities of launching a job out of the box.  

### Job
a container for Step instances. It defines:  
- The simple name of the job.  
- Definition and ordering of Step instances.  
- Global properies like whether or not the job is restartable.  

### Job Parameters
a JobParameters object holds a set of parameters used to start a batch job.

### Job Instance
refers to the concept of a logical job run.  
JobInstance = Job + identifying JobParameters.

### Job Execution
a single attempt to run a Job. An execution may end in failure or success.

### JobRepository
metadata about the currently running process  
BATCH_* tables To store Job, JobExecution, StepExecution state???  
BATCH_JOB_INSTANCE  
BATCH_JOB_EXECUTION_PARAMS  
BATCH_JOB_EXECUTION  

### Step
Each step is responsible for obtaining its own data, applying the required business logic to it, and then writing the data to the appropriate location.
- Tasklet  
  - Define a Functional Interface called repeatly until return FINISHED.  
  - Each call wrapped into a transaction.
- Chunk step
  - Define chunk size, reader, processor, writer.
  - ItemReader: usually defined as @StepScope as it is not thread safe.
  - ItemProcessor: return null for stop processing the item.
  - CompositeItemProcessor: delegate pattern

## Spring Batch Programming
- Create custom Tasklet/ItemReader/ItemProcessor/ItemWritter.
- Define Job and its steps in Configuration class.

## Parallelization  
- Multiple Threads Step (Chunks are processed in different thread)  
- Parallel Steps  
- Asynchronous ItemProcessor/ItemWriter
- Remote Chunking (network intensive)  
- Partitioning