## Run self-signed Nginx
mkdir ssl  
cd ssl  
openssl req -newkey rsa:2048 -nodes -x509 -days 365 -keyout private.pem -out certificate.pem  
docker run -it -d -v $PWD:/etc/ssl/private/ -v $PWD:/etc/ssl/certs/ -e CRT=certificate.pem -e KEY=private.pem -p 80:80 -p 443:443 
mpineault/nginx-alpine-ssl  
