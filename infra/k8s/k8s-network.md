## Concept
https://medium.com/google-cloud/kubernetes-nodeport-vs-loadbalancer-vs-ingress-when-should-i-use-what-922f010849e0  
https://theithollow.com/2019/02/05/kubernetes-service-publishing/  

- containerPort (yaml)  
  - For informational purposes only.

- ClusterIP
  - Kubernetes will create EndPoint under the hood
  - Endpoint is created to store Pod IP and port

- NodePort
  - Expose through Node port (on every node)
  - Randomly assigned if not specified

- LoadBalancer
  - Kubernetes will create NodePort under the hood.
  - The actual load balancer point to the Node port.
  - Option 1: External LB + NodePort (30000+)
  - Option 2: MetalLB

- Ingress Controller  
  - Act as reverse proxy, to reduce the cost of load balancer.

- EndPoint
  - Can point to multiple external IP address.

- On-Premise Setup  
  - Load Balancer (HAProxy) -> Ingress NodePort (Host Port) -> Ingress Controller (DaemonSet) -> Services

- Manifest defintion
  - containerPort: (indicative) container listening port
  - port: clusterIP service listening port
  - targetPort: target container listening port
  - nodePort: host port

## Service (NodePort) 
External-IP you will see when you using a LoadBalancer mode, not a NodePort. In NodePort you have no dedicated external IP for you service, all your nodes will handle the connection to your service.  

## Service & Endpoints
https://kubernetes.io/docs/concepts/services-networking/service/  
- Kubernetes assigns Service an IP address (sometimes called the "cluster IP"), which is used by the Service proxies.  
- The controller for the Service selector continuously scans for Pods that match its selector, and then POSTs any updates to an Endpoint object with same name as Service.
- Services most commonly abstract access to Kubernetes Pods, but they can also abstract other kinds of backends. e.g. External Database.  
  Because this Service has no selector, the corresponding Endpoints object is not created automatically. You can manually map the Service to the network address and port where it's running, by adding an Endpoints object manually

## Debug  
https://kubernetes.io/docs/tasks/debug-application-cluster/debug-service/  

## Access from External  
- Kubernetes Proxy  
    ```sh
    kubectl proxy --port=8080
    # http://localhost:8080/api/v1/proxy/namespaces/<NAMESPACE>/services/<SERVICE-NAME>:<PORT-NAME>/, e.g. 
    http://localhost:8080/api/v1/proxy/namespaces/default/services/my-internal-service:http/
    ```
  - For debug  

- NodePort
  - For on-premise
  - A reverse proxy (HA) can be setup in front of cluster nodes, and forward the requests to node NodePorts.
- LoadBalancer
  - For cloud env
  - NodePort and cloud load balancer will be automatically created for each microservice.
- Ingress  
  - For cloud env
  - For multiple microservices, instead of using multiple load balancer and proxy, and to save the trouble of configuration, Ingress is a kubernetes solutions for this.  
  - Ingress Controller is implemented using Nginx, HAProxy, Istio or Traefik etc, and not deployed by default. It has built-in intelligence to detect Ingress resource in cluster and configure controller automatically.
  - Ingress is actually NOT a type of service. Instead, it sits in front of multiple services and act as a “smart router” or entrypoint into your cluster.  
  - Ingress Controller still need one loadbalancer or proxy outside cluster to point to it. But it is a one-time configuration.
  - 
  - Need deploy Ingress Controller (Nginx is k8s supported). It is intelligent to monitor cluster, IngressResource and configure Nginx automatically.

## Access to External
- Option 1: Use direct IP
- Option 2: Define an Endpoint and create a Service for this EndPoint  
https://ksingh7.medium.com/kubernetes-endpoint-object-your-bridge-to-external-services-3fc48263b776  
  ```yaml
  ---
  apiVersion: v1
  kind: Service
  metadata:
    name: mysql-service
  spec:
    ports:
      - protocol: TCP
        port: 3306
        targetPort: 3306

  ---
  apiVersion: v1
  kind: Endpoints
  metadata:
    name: mysql-service
  subsets:
    - addresses:
        - ip: 192.168.86.43
      ports:
        - port: 3306
  ```