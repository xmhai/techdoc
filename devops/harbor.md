## Concept

## Installation
Refer to official installation.  
https://goharbor.io/docs/2.4.0/install-config/

Install v2.x on Centos7.x
- Install pip3/docker-compose  
    ```sh
    sudo yum update -y
    sudo yum install python3-pip -y
    pip3 -V
    sudo pip3 install --upgrade pip
    sudo pip3 install docker-compose
    docker-compose version
    sudo ln -s /usr/local/bin/docker-compose /usr/bin/docker-compose
    ```

- Get harbor binary  
    ```sh
    # download
    wget https://github.com/goharbor/harbor/releases/download/v2.4.1/harbor-offline-installer-v2.4.1.tgz
    tar -xvf harbor-offline-installer-v2.4.1.tgz

    # generate SSL for HTTPS

    cd harbor
    cp harbor.yml.tmpl harbor.yml
    # Modify hostname/port/password
    # For HTTP, comment out https port for HTTP access
    # For HTTPS, set cert and key location
    ```

- Install  
    ```sh
    sudo ./install.sh
    ```

## Access
http://srv:9080/
admin/admin

## Push
```sh
# example
docker login srv:9080
docker tag pfa-frontend 192.168.86.43:9080/library/pfa-frontend
docker push 192.168.86.43:9080/library/pfa-frontend
```

## Troubleshooting
- Error: Docker push error "http: server gave HTTP response to HTTPS client"
Reason: docker default use https to access docker registry.  
https://stackoverflow.com/questions/49674004/docker-repository-server-gave-http-response-to-https-client/54190375  
- For windows, Docker Desktop -> Setting -> Docker Engine,  
  Modify "insecure-registries": ["192.168.86.43:**9080**"],  
- For linuxm, /etc/docker/daemon.json  