## Gen SSL Cert
openssl req -x509 -newkey rsa:2048 -nodes -days 365 \\  
-subj "/CN=rancher.my.org" \\  
-addext "subjectAltName = DNS:rancher.my.org" \\  
-keyout rancher.my.org.key -out rancher.my.org.crt
