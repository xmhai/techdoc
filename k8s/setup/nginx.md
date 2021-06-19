## Run self-signed Nginx
mkdir ssl  
cd ssl  
openssl req -x509 -newkey rsa:2048 -nodes -days 365 -keyout private.key -out certificate.pem  
docker run -it -d -v $PWD:/etc/ssl/private/ -v $PWD:/etc/ssl/certs/ -e CRT=certificate.pem -e KEY=private.pem -p 80:80 -p 443:443 
mpineault/nginx-alpine-ssl  

## Installation on Windows
Nginx commands:
```sh
start nginx
nginx -s stop
nginx -s reload
```
## Configuration
Enable Https:
```sh
# generate key from WSL
openssl req -x509 -newkey rsa:2048 -nodes -days 365 -keyout rancher.my.org.key -out rancher.my.org.crt
# copy files to conf folder
# update nginx.conf
```
Loadbalancer
```sh
	upstream rancher {
		least_conn;
		server 192.168.10.13:443 max_fails=3 fail_timeout=5s;
		server 192.168.10.14:443 max_fails=3 fail_timeout=5s;
	}

	server {
		listen 443 ssl;
		server_name rancher.my.org;

		ssl_certificate rancher.my.org.crt;
		ssl_certificate_key rancher.my.org.key;

		location / {
			proxy_pass https://rancher;
			proxy_set_header Host $host;
			proxy_set_header X-Real-IP $remote_addr;
			proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
			proxy_set_header X-Forwarded-Proto $scheme;
			proxy_set_header Upgrade $http_upgrade;
			proxy_set_header Connection "Upgrade";
		}
	} 
```