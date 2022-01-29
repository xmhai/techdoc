# Installation
- Centos 7
```sh
#remove the old version
sudo yum -y remove git
sudo yum -y remove git-*
#add repo
sudo yum -y install https://packages.endpoint.com/rhel/7/os/x86_64/endpoint-repo-1.9-1.x86_64.rpm
sudo yum install git
```

# Git Concept
## Repository
Git makes no distinction between the working copy and the central repository—they're all full-fledged Git repositories.

Git’s collaboration model is based on repository-to-repository interaction. You push or pull commits from one repository to another.

- Bare Repository
  - No working directory and not for normal development.
  - Only contains .git directory.
  - Shouldn't make any commits in it.
  - No remotes are created.
- Development (non-bare) Repository

data structures (objects/ folder)
- object store
  - blob  
    each version of file is represented as a blob. only hold file data.
  - tree  
    represents one level of directory information.
  - commit  
    holds meta data of each change, including author, committer etc.  
    points to a tree object.
  - tag  
    assigns human readable name to a commit.
- index  
  a temporary and dynamic binary file that describes the directory structure of entire repository.

# Branch Strategy
Branch Model  
http://nvie.com/posts/a-successful-git-branching-model/

# Git Reposition Setup
## Create Remote Repository
- git init --bare *repository* or from github/gitlab/...
- A bare repository is created.
- Central repository is not a working copy.

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

# Azure DevOps
Goto Azure devop portal, open the project, e.g.:  https://dev.azure.com/*organization*/_git/*project*

Click on Repos, and from the upper right corner, click button  'Clone',
set user name and password (or use PTA - Personal Assess Token), copy the git url.

From project parent directory, run Cmd:  
git clone https://dev.azure.com/*organization*/_git/*project*  
Enter user name/password or PTA/PTA from Clone Repository page.

## Best Pratice
- Only allow fast-forward merge for integration branch  
  https://git-scm.com/docs/git-push  
- Integrate more often, not only at the end of sprint
- 
