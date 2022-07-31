## Design
- User external scheduler (AutoSys, Tivoli, Control-M or Quartz/Spring Scheduler) to trigger the batchjob script running on VM.
- The script pulls data from staging server folder (should the script move the file to another folder???)
- The script runs SpringBatch command line, SpringBatch run and die.
- In the SpringBatch, it read file and output to multiple destination using CompositeItemWritter.
- Exception handling: ignore, retry or abort
- Batch Job running report store in database. Two tables: summary and exception records.
- View the job execution status and details from UI and scheduler console.
- Job retrigger
