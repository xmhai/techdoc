## Installation
**JIRA 7.3 on CentOS 7.x**  
https://www.sbarjatiya.com/notes_wiki/index.php/CentOS_7.x_Install_Jira  
```sh
wget https://www.atlassian.com/software/jira/downloads/binary/atlassian-jira-software-7.3.0-x64.bin
chmod a+x atlassian-jira-software-7.3.0-x64.bin
sudo ./atlassian-jira-software-7.3.0-x64.bin
# set port to 
```
**Cloud**  
https://www.atlassian.com/software/free (10 users)  
xmhai.atlassian.net   

**NOTE***  
Jira Server evalation license is valid for 30 days and can be extended to 90 days.  
Jira becomes read-only when your evaluation expires.  
You will get the following massage "One or more of your application licenses have expired. Users affected by this will not be able to log in. Please renew your licenses to reactivate your applications."

## Access
http://ip:8080  
Need to register a Atlassian account first to get a token to complete server setup.  

When first time login, create user admin/admin.  
http://192.168.10.10:8080/secure/WelcomeToJIRA.jspa  

## Use
- Epic  
  - large story
  - distinct start and end
  - span multiple sprints
  - contains stories, bugs and tasks
- Story
  - describe the business needs from business user point of view
  - be completed within a sprint
- task (should we use sub-task only)
  - used by development team to define the work need to be done
- bug
  - defect
- sub-task
  - more granular level of detail to be assigned when multiple people working on a story, task or a bug.  

## UI
- JIRA Administration
- Project Configuration
  - workflow
- Board Configuration
  - Columns/Quick Filter/Estimation
- Backlog
  - Version/Epic/Issue/SubTask
- Sprint
