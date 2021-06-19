## Installation
https://www.digitalocean.com/community/tutorials/how-to-use-apache-as-a-reverse-proxy-with-mod_proxy-on-ubuntu-16-04

**Installation on Windows**  
Download from ApacheLounge, check ReadMe.txt

**URL rewrite before proxypass**  
https://serverfault.com/questions/609203/rewrite-rule-before-proxypass

**Setup LoadBalancer**  
- open httpd.conf
- remove comment to enable  
    - proxy_module
    - proxy_http_module
    - proxy_balancer_module
    - slotmem_shm_module
    - lbmethod_bytraffic_module
    - lbmethod_byrequests_module
- add proxy section  
```sh
<IfModule proxy_module>

<Proxy balancer://mycluster>
    BalancerMember http://127.0.0.1:8180 route=node1
    BalancerMember http://127.0.0.1:8280 route=node2
    # apache will extract the route information (node2) from below value b4cd54f1-bd77-49c0-9f3c-e80f3767bd08.node2

    #ProxySet lbmethod=byrequests
    #sticky session example for keycloak basic on AUTH_SESSION_ID
    ProxySet stickysession=AUTH_SESSION_ID
</Proxy>

# path rewrite,http://127.0.0.1/keycloak/realms/master/.well-known/openid-configuration will be re-written to http://127.0.0.1:8180/auth/realms/master/.well-known/openid-configuration
ProxyPass "/keycloak"  "balancer://mycluster/auth"
ProxyPassReverse "/keycloak"  "balancer://mycluster/auth"

</IfModule>
```
**Setup Https Load Blanacer**   
Move the \<Proxy\> section to ssl config, and add SSLProxyEngine On. SSL config file location can be in httpd.conf, for ApacheLounge, the file is conf\httpd-ahssl.conf.

**WebSocket**  
Difficult to setup, use Nginx is much easier.

**To turn on debug**   
Add below line to httpd.conf:
LogLevel debug proxy:trace5
