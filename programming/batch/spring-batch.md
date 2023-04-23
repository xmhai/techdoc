## Common Pattern
- Conditional step  
  - Use JobExecutionDecider.  
    ```java
    .start(step1())
    .next(decider()).on("TRUE").next(step2())
    .from(decider()).on("*").next(step3())
    .end()
    .build();
    ```
  - Use subflow is there are multiple deciders, one subflow for every decider.
- Skip logic  
  - Use faultTolerant() and skip()
  - Use SkipPolicy for more complicated scenarios.
- Listner

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

### JobRepository
metadata about the currently running process  
BATCH_* tables To store Job, JobExecution, StepExecution state???  
BATCH_JOB_INSTANCE  
BATCH_JOB_EXECUTION_PARAMS  
BATCH_JOB_EXECUTION  

## Spring Batch Programming
- To initialize batch tables:  
  spring.batch.initialize-schema=always
- Create custom Tasklet/ItemReader/ItemProcessor/ItemWritter.
- Define Job and its steps in Configuration class.

## Parallelization  
- Multiple Threads Step (Chunks are processed in different thread)  
- Parallel Steps  
- Asynchronous ItemProcessor/ItemWriter
- Remote Chunking (network intensive)  
- Partitioning

## Features  
- CommandLineJobRunner  
- JobRegistryJobRunner
- JobLaunderCommandLineRunner
- Late Job Parameter Binding (Parameter, @StepScope)  
- JobParameter
- JobExecutionListener/StepExecutionListner/ChunkListener (Notification/Initialization/CleanUp)
- ExecutionContext (Execution state for restarting job)
- Step
  - Tasklet
  - CallableTaskletAdapter (executed in a different thread)
  - MethodInvokingTaskletAdapter (execute method in another class)
  - SystemCommandTasklet (execute system command asynchronously)
- Flow
- Status: 
- Customize BatchConfiguration
  - JobRepository, e.g. mulitple datasource
  - JobExplorer
  - PlatformTransactionManager
  - JobLauncher, e.g. User MvcController???
- ValidatingItemProcessor
- BeanValidatingItemProcessor
  - @NotNull
  - @Size
  - @Pattern

## What I want to know
- Trigger a Job  
  - On application run: this is default, JobLauncherCommandLineRunner will run any @Job defined.
  - RESTful call:   
    spring.batch.job.enabled = false  
  - executed via a schedule  
    Quartz + RESTful trigger  
    Autosys + UberJar  

- Stop a Job  
  - stepExecution.setTerminateOnly();

- Exception  
  - Spring Batch’s default method of handling an exception is to stop the job in the failed status.
  - Skipping records is common practice in batch processing. Can set skip exception class and skip count.
  - Use ItemListener to record the error record.
  - For no input, use StepExecutionListner to return FAILED status.

- Restart a Job

## Job Restart
```txt
Spring Batch will always create new Job Execution and will not reuse a previous failed job execution to continue its execution.  

Need to understand three similar but different concept in Spring Batch: Job, Job Instance, Job Execution. Example:-

Job : End-Of-Day Batch
Job Instance : End-Of-Day Batch for 2018-01-01
Job Execution: End-Of-Day Batch for 2018-01-01, execution #1
In high-level, that's how Spring Batch's recovery works:

Assuming your first execution failed in the step 3. You can submit the same Job (End-of-Day Batch) with same Parameters (2018-01-01). Spring Batch will try to look up last Job Execution (End-Of-Day Batch for 2018-01-01, execution #1) of the submitted Job Instance (End-of-Day Batch for 2018-01-01), and found that it has previously failed in step 3. Spring Batch will then create a NEW execution, [End-Of-Day Batch for 2018-01-01, execution #2], and start the execution from step 3.

So by design, what Spring trying to recover is a previously failed Job Instance (instead of Job Execution). Spring batch will not reuse execution when you are re-running a previous-failed execution.
```