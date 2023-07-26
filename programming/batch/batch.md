## Major concerns
- Timing
  - When should the job start
  - How long it will run
- Performance
  - Batch mode (determine batch size)
  - Reduce the number of SQL
- Recovery (https://docs.oracle.com/html/E79064_01/Content/Batch%20Processor/Batch_failure_and_recovery.htm)

## Batch Framework
- User external scheduler (AutoSys, Tivoli, Control-M or Quartz/Spring Scheduler) to trigger the batchjob script running on VM.
- The script pulls data from staging server folder to local staging folder.
- The script runs SpringBatch command line, SpringBatch run and die.
- Delete files/Move files to archive folder after processing (who pull/process the files is also responsible for clean up)
- In the SpringBatch, it read file and output to multiple destination using CompositeItemWritter.
- Exception handling: ignore, retry or abort
- Batch Job running report store in database. Two tables: summary and exception records.
- View the job execution status and details from UI and scheduler console.
- Job retrigger
  - Use Job Parameter to prevent same file to be processed multiple times.
  - Check duplicate records in itemProcessor
  - With external scheduler, we are not able to retrigger spring batch as need to use jobOperator.restart instead of normal jobLauncher.

## Batch Pattern
### File -> DB
- Source system put file on server (SFTP,S3)
  - Use control file to indicate data file is complete.
  - Should contain some form of integrity information in file like header or trailer including row_count or hash.
- Script/App pull file to Local Staging Folder, and delete file on server after pull successfully.
- Import file to staging table
  - Truncate staging table for import
- Process the records in staing table
- Job must be able to re-trigger (from step2)

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

