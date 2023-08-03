## Installation  
https://docs.camunda.org/manual/latest/installation/full/tomcat/pre-packaged/
https://downloads.camunda.cloud/release/camunda-bpm/tomcat/7.19/

## Useful tools for Camunda development  
Admin Portal: http://localhost:8080/
Database: http://localhost:8080/h2-console
Restful API: Postman

## Enable H2  
Add h2 datasource in yaml and add springboot-starter-jdbc

## Error: no processes deployed with key 'loanApproval': processDefinition is null  
It is a bug in Camunda quick start, remove processPostDeploy code

## History Cleanup
- Configure in Model under process "History Cleanup"

## Constructs
- Service Task
  - Use Java Delegate expression is a preferred way
  - Should have Delegate expression defined
- Exclusive Gateway
  - Should have Condition Defined
  - Create a default flow: Just mark the sequence flow and click the wrench symbol in the mouse menu and select ‘default flow’.