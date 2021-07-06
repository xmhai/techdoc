## Docker Basic
https://docs.docker.com/get-started/overview/  
- Docker only run on linux, Linux VM is created on Windows or Mac
- Each containers run within a subset of resources (Harddisk/CPU/Memory/Network), this is achieved by Linux Namespace and Control Group
- Docker Image contains files and start command
- Docker Client->Docker Server(Daemon)->Docker Hub->Docker Image->Docker Container

## Installation
https://docs.docker.com/engine/install/linux-postinstall/  

**Ubuntu**  
https://docs.docker.com/engine/install/ubuntu/  
NOTE: DON'T use ubuntu snap docker. If installed, run "sudo snap remove docker" to remove it before installation.  

```sh 
curl -fsSL https://download.docker.com/linux/ubuntu/gpg | sudo gpg --dearmor -o /usr/share/keyrings/docker-archive-keyring.gpg  
echo \
  "deb [arch=amd64 signed-by=/usr/share/keyrings/docker-archive-keyring.gpg] https://download.docker.com/linux/ubuntu \
  $(lsb_release -cs) stable" | sudo tee /etc/apt/sources.list.d/docker.list > /dev/null  
sudo apt-get update  
sudo apt-get install docker-ce docker-ce-cli containerd.io  
```
**RHEL**  
https://docs.docker.com/engine/install/centos/  
```sh
sudo dnf config-manager --add-repo https://download.docker.com/linux/centos/docker-ce.repo  
sudo dnf install docker-ce-20.10.6 docker-ce-cli-20.10.6 containerd.io --allowerasing  
```
Post Installation
```sh
sudo systemctl start docker  
sudo systemctl enable --now docker  
sudo usermod -aG docker $USER  
newgrp docker
sudo chmod 666 /var/run/docker.sock
```
**Windows**  
Uninstall Docker for Windows  
Delete folder C:\Users\username\AppData\Local\Docker*  
Install Docker for Windows

## Docker command
-- Image Commands --  
```sh
docker build [-t docker-id/project-name:latest] [-f filename] .  
docker login  
docker pull <docker-id/project-name[:version|latest]>  
docker image ls  
docker image rm  
docker image prune  
# e.g. docker image prune --filter="dangling=true"  
```

-- Container Commands --  
```sh
# -d daemon(detached/background) mode  
# -p port mapping
# --restart=always container always starts on deamon startup
docker run [-p host-port:container-port] [-d] [ --restart=always] <image> [command]
docker ps [-a]  
docker container ls  
docker exec -it <container-id> [command]   
# run sh: docker exec -it container-id /bin/sh  
docker logs <container-id>  

# Return low-level information on Docker objects  
docker inspect <container-id>  
# view STDOUT from a container  
docker attach <container-id>  

# send SIGTERM, which means graceful shutdown
docker stop <container-id>
# send SIGKILL
docker kill <container-id>
# kill all containers: docker kill $(docker ps -q)
# start a stopped container (use docker ps -a to find out the container id)
docker start <container-id>
docker rm <container-id>
docker container prune
```

-- Misc Commands -- 
```sh
docker version
# cleanup unused objects: image/container/volume/network. For each type of object, Docker provides a prune command. docker system prune to clean up multiple types of objects at once
docker system prune
```

## Dockerfile structure
- FROM
- WORKDIR
- COPY
- RUN
- CMD  

The order of instruction can be arranged or modified to use cache.

## Development
Use maven docker plugin to build docker image (see below)  

Create springboot docker  
https://spring.io/guides/topicals/spring-boot-docker/

## Q & A
Q: What is the memory size allocated for each container?  
A: can specify in command option.

## Troubleshooting
**Error**: Error response from daemon: i/o timeout  
**Solution**: 
Open Docker  Setting, click on the Resources->Network tab and change the DNS server from Automatic to Fixed "8.8.8.8".

**Error**:  
show "Docker is starting" forever  
show "Linux Containers WSL 2 has stopped"  
**Solution**:  
Open Docker Troubleshoot, clear data, reset to factory default
If not working, uninstall and reinstall  

---
Maven build plugin
```xml
<plugin>
    <groupId>org.codehaus.mojo</groupId>
    <artifactId>exec-maven-plugin</artifactId>
    <version>1.6.0</version>

    <executions>
        <!-- Remove existing image from local repo -->
        <execution>
            <id>docker-clean</id>
            <phase>package</phase>
            <goals>
                <goal>exec</goal>
            </goals>
            <configuration>
                <executable>docker</executable>
                <workingDirectory>${project.basedir}</workingDirectory>
                <arguments>
                    <argument>rmi</argument>
                    <argument>-f</argument>
                    <argument>${project.artifactId}</argument>
                </arguments>
            </configuration>
        </execution>

        <!-- Create new docker image using Dockerfile which must be present 
            in current working directory. Tag the image using maven project version information. -->
        <execution>
            <id>docker-build</id>
            <phase>package</phase>
            <goals>
                <goal>exec</goal>
            </goals>
            <configuration>
                <executable>docker</executable>
                <workingDirectory>${project.basedir}</workingDirectory>
                <arguments>
                    <argument>build</argument>
                    <argument>-t</argument>
                    <argument>${project.artifactId}</argument>
                    <argument>.</argument>
                </arguments>
            </configuration>
        </execution>
    </executions>
</plugin>
```
