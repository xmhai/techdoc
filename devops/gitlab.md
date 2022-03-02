## Architecture
https://docs.gitlab.com/ee/development/architecture.html  

Repository storages are configured in: /etc/gitlab/gitlab.rb by the git_data_dirs({}) configuration.  

## Concept
- Pipeline
- Stage
- Job
- Artifact
- Cache
- Runner  
  Multiple Runner for One Project, and runners can run on different machine:  
  - Pull the source code and download the artifacts before job execution.  
  - Upload the artifacts to Gitlab server after job execution.
  https://stackoverflow.com/questions/53214381/using-multiple-runners-in-one-gitlab-ci  
  https://docs.gitlab.com/runner/  
- Environment Variables  
  Defined in Instance/Group/Project setting->CICD->Variable section.  
  https://software.rcc.uchicago.edu/git/help/ci/variables/README.md  
  
## Installation
https://about.gitlab.com/install/?version=ce#centos-7  
NOTE:  
```sh
# Replace DNS in follow command
sudo EXTERNAL_URL="http://192.168.86.43:10000" dnf install -y gitlab-ce
# Get root password (username: root)
sudo cat /etc/gitlab/initial_root_password
```
Note: Might encounter when invoke Let's Encrypt.

**Install Runner**  
https://docs.gitlab.com/runner/install/linux-repository.html  
https://docs.gitlab.com/runner/register/index.html#linux

## Configuration
- To change IP/Port after installation, edit the following line in /etc/gitlab/gitlab.rb:  
    ```sh
    export EXTERNAL_URL "https://ip:port"  
    sudo gitlab-ctl reconfigure  
    ```

- Create User  
From "Admin Area", create user, and then "Edit" user to set password.

- Disable puma cluster mode to save memory
  Puma is the web server for GitLab.
  ```sh
  sudo nano /etc/gitlab/gitlab.rb  
  sudo gitlab-ctl reconfigure
  ```
- Use external PostgreSQL
  https://docs.gitlab.com/ee/administration/postgresql/external.html  

- Reduce Memory  
  https://docs.gitlab.com/omnibus/settings/memory_constrained_envs.html  
  NOTE:  
  cggroup setting should NOT included as it will cause exception. Just use below settings.
    ```ini
    puma['worker_processes'] = 0
    prometheus_monitoring['enable'] = false
    sidekiq['max_concurrency'] = 10
    ```
- Reset Password  
  sudo gitlab-rake "gitlab:password:reset[root]"

## CICD setup
Runner is recommended for Gitlab CI, as it is running on a separate machine.

Following are the steps to install docker runner an run maven:  
- Install docker for docker runner.
- Create Runner.  
  "Settings"->"CI/CD"->"Runners", follow instruction under "Set up a specific runner manually", click button "Show Runner Installation instructions"
- Create .gitlab-ci.yml
  ```yml
  image: maven:latest

  cache:
    key: "maven3"
    paths:
      - .m2/repository
      - target

  build:
    stage: build
    script:
      - mvn clean package
  ```

## Sonarqube Integration
https://www.programmersought.com/article/66704933922/  

```yml
variables:
  JAVA_HOME: "/usr/lib/jvm/java-11-openjdk-11.0.11.0.9-2.el8_4.x86_64"

stages:
  - build
  - test
  - deploy

maven_build:
  stage: build
  script:
    - echo "Compile..."
    - mvn clean compile package
  # pass to next job
  artifacts:
    paths:
      - target/classes/
      - target/*.jar
    expire_in: 5 mins

unit_test:
  stage: test
  script:
    - echo "Unit test..."
    - mvn test -Dmaven.main.skip

sonar_scan:
  stage: test
  script:
    - echo "Sonarqube scan..."
    # cannot use option -Dproject.settings=sonar-project.properties
    - mvn sonar:sonar -Dsonar.host.url=http://192.168.10.16:9000 -Dsonar.login=b6bf2cd55f171c5e16cfa6618357fb3b362b2e55 -Dsonar.projectKey=smartjava-core

deploy:
  stage: deploy
  script:
    - echo "Upload artifact to JIRA..."
    - echo "upload artifact"
```

# Script
**sed**
```sh
# remove lines start with SPACE
sed '/^\s*$/d' build-project.property
# remove lines start with #
sed '/^#/d'
# replace env.GITLAB_USER_EMAIL with env $GITLAB_USER_EMAIL
# option: ignore case, ! as delimiter, replace all
sed -i 's!env.GITLAB_USER_EMAIL!'$GITLAB_USER_EMAIL'!g' dockerfile
# replace initialDelaySeconds.*$ with ...
sed -i 's!initialDelaySeconds.*$!initialDelaySeconds: '$initialDelaySeconds'!g' k8s.release.yaml
# insert content with external file after the matching line
sed -i '/#lifecycle/r /home/gitlab-runner/scripts/lifecycleconf.xml' k8s.release.yaml
```
**awk**
```sh
# add "export " to each line of input
awk '{print "export " $0}'
```
**source**
```sh
# run each line of file
source <filename>
```
