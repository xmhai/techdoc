## Gen Nginx Cert
openssl req -x509 -newkey rsa:2048 -nodes -days 365 -keyout localhost.key -out localhost.crt
