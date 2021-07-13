## Git Concept
## Repository
Git makes no distinction between the working copy and the central repository—they're all full-fledged Git repositories.

Git’s collaboration model is based on repository-to-repository interaction. You push or pull commits from one repository to another.

## Branch
Branch Model  
http://nvie.com/posts/a-successful-git-branching-model/


---
## Git Command Usage
## Remote first
From project parent directory, run:  
git clone *remote-url*  
What it does:
- copies an existing Git repository
- creates a remote connection called "origin" pointing back to the original repository

## Local first
Create the folder  
git init .  
echo "Readme" >> README.md  
git add README.md  
git commit -m "initial commit"  
git remote add origin *remote-url*  
git push origin master  

## Create branch
git checkout -b brahch_name  
git commit -m "initial commit"  
git push origin brahch_name  

## Switch to branch
git checkout brahch_name

## Setup central repository
Central repository is not a working copy.
git init --bare  

## Fork Repository


---
## Azure DevOps
Goto Azure devop portal, open the project, e.g.:  https://dev.azure.com/*organization*/_git/*project*

Click on Repos, and from the upper right corner, click button  'Clone',
set user name and password (or use PTA - Personal Assess Token), copy the git url.

From project parent directory, run Cmd:  
git clone https://dev.azure.com/*organization*/_git/*project*  
Enter user name/password or PTA/PTA from Clone Repository page.

