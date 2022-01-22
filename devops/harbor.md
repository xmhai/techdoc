## Concept

## Installation
Refer to official installation.  
https://goharbor.io/docs/2.4.0/install-config/
https://docs.vmware.com/en/VMware-Telco-Cloud-Automation/1.9.5/com.vmware.tca.userguide/GUID-F36AFBB9-81B6-42B4-923A-5511B502E25C.html  

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
    sudo mv harbor /opt/harbor

    # generate SSL for HTTPS (Better to use HTTPS as it is docker default)
    # https://goharbor.io/docs/2.4.0/install-config/configure-https/

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
## Create linux service
https://docs.vmware.com/en/VMware-Telco-Cloud-Automation/1.9.5/com.vmware.tca.userguide/GUID-F36AFBB9-81B6-42B4-923A-5511B502E25C.html  
```sh
sudo nano /etc/systemd/system/harbor.service
```
```ini
[Unit]
Description=Harbor
After=docker.service systemd-networkd.service systemd-resolved.service
Requires=docker.service

[Service]
Type=simple
Restart=on-failure
RestartSec=5
ExecStart=/usr/local/bin/docker-compose -f /opt/harbor/docker-compose.yml up
ExecStop=/usr/local/bin/docker-compose -f /opt/harbor/docker-compose.yml down

[Install]
WantedBy=multi-user.target
```
```sh
sudo chmod 644 /etc/systemd/system/harbor.service
sudo systemctl daemon-reload
sudo systemctl enable harbor
sudo systemctl start harbor
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

## Appendix
Create cert for HTTPS access (replace harbor.home with yourdomain)  
```sh
openssl genrsa -out ca.key 4096

openssl req -x509 -new -nodes -sha512 -days 3650 \
 -subj "/C=SG/ST=Singapore/L=Singapore/O=home/OU=harbor/CN=harbor.home" \
 -key ca.key \
 -out ca.crt
 
openssl genrsa -out harbor.home.key 4096

openssl req -sha512 -new \
 -subj "/C=SG/ST=Singapore/L=Singapore/O=home/OU=harbor/CN=harbor.home" \
 -key harbor.home.key \
 -out harbor.home.csr

cat > v3.ext <<-EOF
authorityKeyIdentifier=keyid,issuer
basicConstraints=CA:FALSE
keyUsage = digitalSignature, nonRepudiation, keyEncipherment, dataEncipherment
extendedKeyUsage = serverAuth
subjectAltName = @alt_names

[alt_names]
DNS.1=harbor.home
DNS.2=harbor
DNS.3=srv.home
EOF

openssl x509 -req -sha512 -days 3650 \
    -extfile v3.ext \
    -CA ca.crt -CAkey ca.key -CAcreateserial \
    -in harbor.home.csr \
    -out harbor.home.crt

sudo mkdir /data/cert
sudo cp harbor.home.crt /data/cert/
sudo cp harbor.home.key /data/cert/	

openssl x509 -inform PEM -in harbor.home.crt -out harbor.home.cert

sudo mkdir /etc/docker/certs.d/
sudo mkdir /etc/docker/certs.d/harbor.home:9443/
sudo cp harbor.home.cert /etc/docker/certs.d/harbor.home:9443/
sudo cp harbor.home.key /etc/docker/certs.d/harbor.home:9443/
sudo cp ca.crt /etc/docker/certs.d/harbor.home:9443/

sudo systemctl restart docker
```