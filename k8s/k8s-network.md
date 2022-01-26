## Concept
https://medium.com/google-cloud/kubernetes-nodeport-vs-loadbalancer-vs-ingress-when-should-i-use-what-922f010849e0  

## Access From External  
- Kubernetes Proxy  
    ```sh
    kubectl proxy --port=8080
    # http://localhost:8080/api/v1/proxy/namespaces/<NAMESPACE>/services/<SERVICE-NAME>:<PORT-NAME>/, e.g. 
    http://localhost:8080/api/v1/proxy/namespaces/default/services/my-internal-service:http/
    ```
  - For debug  

- NodePort
- LoadBalancer
- Ingress  
  Ingress is actually NOT a type of service. Instead, it sits in front of multiple services and act as a “smart router” or entrypoint into your cluster.
