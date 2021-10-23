## Batch Processing Requirements
- Bulk-oriendted (Long running)
- Background execution (Non-interactive)

## Batch Processing Framework Capability
- Invocation model:
    - Scheduled
    - Ad Hoc and on-demand (?)
- Chunk processing: 
- Restartablilty:
- Parallel processing

## Spring Batch Architecture
https://docs.spring.io/spring-batch/docs/current/reference/html/domain.html#domainLanguageOfBatch  
### JobLauncher
### Job
A container for Step instances.
### JobRepository
metadata about the currently running process  
BATCH_* tables To store Job, JobExecution, StepExecution state???
### Step
- Tasklet  
  - Define a Functional Interface called repeatly until return FINISHED.  
  - Each call wrapped into a transaction.
- chunk step
  - Define chunk size, reader, processor, writer.
  - ItemReader: usually defined as @StepScope as it is not thread safe.
  - ItemProcessor: return null for stop processing the item.
  - CompositeItemProcessor: delegate pattern

## Spring Batch Programming
- Create custom Tasklet/ItemReader/ItemProcessor/ItemWritter.
- Define Job and its steps in Configuration class.
