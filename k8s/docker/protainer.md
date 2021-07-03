## Feature
Provides UI to manage dockers on Linux (Similar to Docker for Windows). With docker agent, can manage docker on multiple nodes.

## installation
https://documentation.portainer.io/v2.0/deploy/ceinstalldocker/
https://www.youtube.com/watch?v=ljDI5jykjE8  
https://www.youtube.com/watch?v=kKDoPohpiNk

For Server node installation:  
- Install portainer
- http://localhost:9000  

For Agent node installation:
- Install portainer agent
  On windows 10, need to change port mapping to -p 9101:9001, port 9001 is used by system process.  
- Add Endpoint->Select "Agent", enter:  
  - Name: IP address (or descriptive name)
  - Endpoint Url: IP address:port
  - Public IP: IP address