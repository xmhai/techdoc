## Architecture  
https://howtodoinjava.com/tomcat/tomcats-architecture-and-server-xml-configuration-tutorial/

## Components
- connector
  - HTTP connector  
    - Incoming requests for all applications go through a single instance of this connector. Each new request causes the instantiation of a new thread that remains alive within the connector for the duration of the request.  
    - Incoming requests that require Secure Socket Layer (SSL) transport are redirected to port 8443.