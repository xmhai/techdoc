## Concept
Keycloak is both IdP and token issuer for OAuth2, can be used as SSO server.

## Quick Start
https://www.keycloak.org/docs/latest/getting_started/index.html

## Setup
https://medium.com/@hasnat.saeed/setup-keycloak-server-on-ubuntu-18-04-ed8c7c79a2d9

**MySQL setup**  
https://medium.com/@pratik.dandavate/setting-up-keycloak-standalone-with-mysql-database-7ebb614cc229  
After create the module under modules/keycloak
and configure datasource in standalone.xml, need to add module:  
$jboss-cli
module add --name=com.mysql --resources=D:/app/keycloak/keycloak-8.0.2/modules/system/layers/keycloak/com/mysql/main/mysql-connector-java-8.0.21.jar --dependencies=javax.api,javax.transaction.api

**MSSQL setup**  
https://medium.com/multinetinventiv/keycloak-mssql-database-integration-87fc46dbec2c  
NOTE:  
- When copy the code from above link, be careful about the DOUBLE QUOTE. replace it with "  
- Create SQL server user with sysadmin privilege.  

**Start at port 8180**  
$standalone -Djboss.socket.binding.port-offset=100

**Keycloak on AWS ECS**  
https://github.com/aws-samples/keycloak-on-aws

## Important Urls  
- User login url  
/auth/realms/public/account  
- Endpoint Url  
http://keycloakhost:keycloakport/auth/realms/{realm}/.well-known/openid-configuration   

## ReverseProxy
Can change web-context in standalone.xml if Apache need to support multiple backend  

## Keycloak Spring Integration
https://www.keycloak.org/docs/latest/securing_apps/index.html#_spring_security_adapter  
Define keycloak beans in spring security xml.  

There are two ways to call Keycloak admin api:
- Keycloak admin client library (keycloak-admin-client)
Must enable service account, use KeycloakBuilder to construct Keycloak object
or
- use RestAPI call (https://www.keycloak.org/docs/latest/server_development/#admin-rest-api)
POST: http://localhost:8180/auth/realms/master/protocol/openid-connect/token Get the access token, put in Authorization Header "Bearer Token"POST: http://localhost:8180/auth/admin/realms/public/users

## Configuration
**FrontendUrl and BackendUrl configuration**  
FrontendUrl is the public url to be redirected, it can be configured in standalone.xml or jboss startup argument for all realms or it can be configured in admin console for individual realm.

BackendUrl is used for server-to-server call in keycloak cliend api, it is configured in keyloak.json serverUrl.

https://issues.redhat.com/browse/KEYCLOAK-6073?_sscc=t
https://issues.redhat.com/browse/KEYCLOAK-11728?_sscc=t
https://github.com/keycloak/keycloak-community/blob/master/design/hostname-default-provider.md
https://www.keycloak.org/docs/8.0/server_installation/#manage-subsystem-configuration
search "url" in above link

**Keycloak Lock Out**  
https://github.com/keycloak/keycloak-documentation/blob/master/server_admin/topics/threat/brute-force.adoc  
Keycloak support permanent and temporary lock-out (config on admin console), the wait in admin console UI means temporary lock-out

**Template**  
You can look for the template providers in Keycloak's code.  
All the templates are "ftl" files filled with a map called "attributes". Keycloak has a couple of classes which fill those templates with Beans depending on the page or action. For example:  
- FreeMarkerEmailTemplateProvider class fills the email templates.  
- FreeMarkerLoginFormsProvider class fills the login templates.  

## Apache ReverseProxy Setup
- Apache httpd.conf
ProxyPass "/auth"  "http://127.0.0.1:8180/auth"
ProxyPassReverse "/auth"  "http://127.0.0.1:8180/auth"
ProxyPass "/portal"  "http://127.0.0.1:8080/portal"
ProxyPassReverse "/portal"  "http://127.0.0.1:8080/portal"

- Keycloak standalone.xml
Add proxy-address-forwarding="true"

- Keycloak startup script
Add -Dkeycloak.frontendUrl=http://localhost/auth 
Check Endpoint Url to confirm endpoints point to FrontendUrl

## Cluster Setup
https://www.keycloak.org/docs/latest/server_installation/#_standalone-ha-mode  
https://www.keycloak.org/docs/latest/server_installation/index.html#_clustering  
https://medium.com/@just_insane/keycloak-sso-part-2-setting-up-keycloak-746be8b937b7  
https://www.keycloak.org/2017/09/cross-datacenter-support-in-keycloak.html  

https://www.keycloak.org/2019/04/keycloak-cluster-setup.html  
https://github.com/fit2anything/keycloak-cluster-setup-and-configuration

- Standalone clustered operation mode.
- It is generally wise to configure your environment to use loadbalancer with sticky sessions.
- Set X-forward-for in standalone-ha.xml
- Sample startup script
standalone.bat --server-config=standalone-ha.xml -Dkeycloak.profile=preview -Djboss.socket.binding.port-offset=100 -Djboss.node.name=node1 -Dkeycloak.frontendUrl=http://localhost/auth
- Session information is kept at individual server, can use sticky session to improve cluster performance so the don't need to look up session information from other server. This can be done by adding below to apache load balance config:
ProxySet stickysession=AUTH_SESSION_ID

from the node1 log, we can see below informaion:
2020-10-06 21:25:59,151 INFO  [org.jgroups.protocols.pbcast.GMS] (ServerService Thread Pool -- 60) node1: no members discovered after 3005 ms: creating cluster as first member
...(after node2 started)
2020-10-06 21:26:37,476 INFO  [org.infinispan.CLUSTER] (thread-9,ejb,node1) ISPN000094: Received new cluster view for channel ejb: [node1|1] (2) [node1, node2]
2020-10-06 21:26:37,481 INFO  [org.infinispan.CLUSTER] (thread-9,ejb,node1) ISPN100000: Node node2 joined the cluster

from the node2 log, we can see below informaion:
2020-10-06 21:26:38,327 INFO  [org.infinispan.CLUSTER] (MSC service thread 1-4) ISPN000094: Received new cluster view for channel ejb: [node1|1] (2) [node1, node2]

## Exceptions
**Error**: In cluster mode, encounter below error:
2020-02-03 01:50:27,622 WARN  [org.keycloak.events] (default task-1) type=CODE_TO_TOKEN_ERROR, realmId=master, clientId=security-admin-console, userId=null, ipAddress=118.201.157.170, error=expired_code, grant_type=authorization_code, code_id=6b41d1b1-973c-4bde-b8c7-1cf3e8882f44, client_auth_method=client-secret  
**Solution**:  
Have you checked that the time is synchronized on both hosts where Keycloak is running?  
The error expired_code most of the time means either time difference or what may solve this (at least what I did for my Keycloak instance) is to increase the timeouts in the admin interface by a factor (depending on what it was) 1.25-1.5.  
https://github.com/galexrt/kubernetes-keycloak/issues/1

## 2FA
https://www.keycloak.org/docs/latest/server_development/#_auth_spi
https://github.com/keycloak/keycloak/tree/master/examples/providers/authenticator
https://github.com/gwallet/keycloak-sms-authenticator  
Refer to my implementation in github

**Error**: Keycloak server cannot access internet  
**Solution**: Add startup argument -Dkeycloak.frontendUrl=https://UELB-EXT-INTER-1126783463.us-east-1.elb.amazonaws.com/auth  
Because Spring keycloak Adapter will get the backend keycloak server url from keycloak.json, and it is the value of internal server ip like elb ip, this parameter is for spring keycloak adapter to get correct frontend url when generate redirect url in case of login

## Redirect to another Id Provider
https://www.keycloak.org/docs/latest/server_admin/#default_identity_provider
