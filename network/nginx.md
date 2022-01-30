# Installation
- Windows
	```sh
	start nginx
	nginx -s stop
	nginx -s reload
	```
- CentOS 7  
  https://www.digitalocean.com/community/tutorials/how-to-install-nginx-on-centos-7  
  https://linuxize.com/post/how-to-install-nginx-on-centos-7/  
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
https://www.digitalocean.com/community/tutorials/how-to-create-a-self-signed-ssl-certificate-for-nginx-on-centos-7  
```sh
# generate self-signed cert
sudo mkdir /etc/ssl/private
sudo chmod 700 /etc/ssl/private
sudo openssl req -x509 -nodes -days 365 -newkey rsa:2048 -keyout /etc/ssl/private/srv.key -out /etc/ssl/certs/srv.crt

openssl req -x509 -newkey rsa:2048 -nodes -days 365 -keyout srv.key -out srv.crt
# copy files to conf folder
# update nginx.conf
```

## Proxy pass
https://www.nginx.com/resources/wiki/start/topics/examples/full/  
- *nginx.conf* is the top level congiuration file which includes Virtual Host Configs in "/etc/nginx/conf.d/*.conf" and "/etc/nginx/sites-enabled/\*".  
- (Preferred way to define vhost) "*conf.d*" does the same job
- (Ubuntu) "*sites-available*" folder is for storing all of your vhost configurations, whether or not they're currently enabled.  
- (Ubuntu) "*sites-enabled*" folder contains symlinks to files in the sites-available folder. This allows you to selectively disable vhosts by removing the symlink.  

https://stackoverflow.com/questions/32542282/how-do-i-rewrite-urls-in-a-proxy-response-in-nginx  
```sh
location /some_dir/ {
    proxy_pass http://some_server/;
}
```

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

## Troubleshooting
- Log files under /var/log/nginx
- Error: failed (13: Permission denied) while connecting to upstream
  ```sh
  cat /var/log/audit/audit.log | grep nginx | grep denied
  # SELinux policy rules specify how processes and users interact with each other as well as how processes and users interact with files. By default it is enabled.
  sudo setenforce 0
  # Open the /etc/selinux/config file and set the SELINUX mod to disabled
  sudo nano /etc/selinux/config
  ```
