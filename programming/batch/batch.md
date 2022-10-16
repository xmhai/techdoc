## Batch Framework
- User external scheduler (AutoSys, Tivoli, Control-M or Quartz/Spring Scheduler) to trigger the batchjob script running on VM.
- The script pulls data from staging server folder (should the script move the file to another folder???)
- The script runs SpringBatch command line, SpringBatch run and die.
- In the SpringBatch, it read file and output to multiple destination using CompositeItemWritter.
- Exception handling: ignore, retry or abort
- Batch Job running report store in database. Two tables: summary and exception records.
- View the job execution status and details from UI and scheduler console.
- Job retrigger

## Batch Processing Requirements
- Bulk-oriented (Long running)
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

