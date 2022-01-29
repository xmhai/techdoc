# Installation
- Windows
	```sh
	start nginx
	nginx -s stop
	nginx -s reload
	```
- CentOS 7  
  https://www.digitalocean.com/community/tutorials/how-to-install-nginx-on-centos-7  
	```sh
	sudo yum install nginx
	sudo systemctl start nginx
	sudo systemctl enable nginx
	```
- Docker (HTTPS)
	```sh
	mkdir ssl  
	cd ssl  
	openssl req -x509 -newkey rsa:2048 -nodes -days 365 -keyout private.key -out certificate.pem  
	docker run -it -d -v $PWD:/etc/ssl/private/ -v $PWD:/etc/ssl/certs/ -e CRT=certificate.pem -e KEY=private.pem -p 80:80 -p 443:443 
	mpineault/nginx-alpine-ssl  
	```

# Configuration
## Homepage
default server root: /usr/share/nginx/html

## HTTPS
```sh
# generate self-signed cert
openssl req -x509 -newkey rsa:2048 -nodes -days 365 -keyout srv.key -out srv.crt
# copy files to conf folder
# update nginx.conf
```

## Proxy setup
- *nginx.conf* is the top level congiuration file which includes Virtual Host Configs in "/etc/nginx/conf.d/*.conf" and "/etc/nginx/sites-enabled/\*".  
- (Preferred way to define vhost) "*conf.d*" does the same job
- (Ubuntu) "*sites-available*" folder is for storing all of your vhost configurations, whether or not they're currently enabled.  
- (Ubuntu) "*sites-enabled*" folder contains symlinks to files in the sites-available folder. This allows you to selectively disable vhosts by removing the symlink.  

## Load Balancer  
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
