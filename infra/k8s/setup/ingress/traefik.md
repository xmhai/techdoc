- Version 1 and version 2 is different, refer to pfa project
- Version 2 can use IngressRoute or Ingress
- View traefik pod log can see the routing problem
- To connect to backend https:
  - Add "--serversTransport.insecureSkipVerify=true" to traefix deployment arg section  
  https://stackoverflow.com/questions/49412376/internal-server-error-with-traefik-https-backend-on-port-443
  - 
- To connect to kubernetes-dashboard
  The solution is to use hostname (e.g. dashboard.srv), otherwise throw 404 error for the js and css file.
