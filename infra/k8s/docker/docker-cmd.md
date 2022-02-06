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
