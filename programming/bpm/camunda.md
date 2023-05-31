## Cluster Architecture
https://docs.camunda.org/manual/7.19/introduction/architecture/#:~:text=Camunda%20Platform%20is%20a%20Java,and%20workflows%20on%20the%20JVM.  

## Thread Model  
https://camunda.com/blog/2019/10/job-executor-what-is-going-on-in-my-process-engine/  

To summarize, asynchronous continuations or BPMN timer events will created JOBs, and the job executor will pick up the jobs and process.

## Job Executor  
https://docs.camunda.org/manual/7.19/user-guide/process-engine/the-job-executor/  
