## Concept
https://www.activiti.org/userguide/?_ga=2.195193317.810505573.1631679651-2116017183.1629103886#_introduction  
- Activiti is moving to cloud deployment since version 7.
- Activiti 6 is the latest version for on-premise deployment, but there is no update after first release.

**Major components:**  
- activiti-rest  
  Default credential: admin/test, REST api http header: authorization: Basic YWRtaW46dGVzdA==
- activiti-app  
  The process engine user console. Use this tool to start new processes, assign tasks, view and claim tasks, etc.
  Default credential: admin/test
- activiti-admin  
  The administrator portal. Use this tool to deploy process definitions. 
  Default credential: admin/admin  
  Need to configure REST api endpoint  
- Eclipse plugin

## Installation
**Activiti 6** (deploy to Tomcat 9 + JRE 8)  
- Download the zip files
- Unzip and configure database connection (default is h2)  
  - activiti-rest: WEB-INF\classes\db.properties
  - activiti-app: WEB-INF\classes\META-INF\activiti-app\activiti-app.properties
  - activiti-admin: WEB-INF\classes\META-INF\activiti-admin\activiti-admin.properties
- Copy the unzip folder to Tomcat webapps
- Copy jdbc driver to Tomcat lib folder

## Usage
**Option 1:**  
Rest API (https://www.activiti.org/userguide/?_ga=2.195193317.810505573.1631679651-2116017183.1629103886#_rest_api)  
**Option 2:**  
java lib

