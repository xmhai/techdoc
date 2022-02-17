## Concept
https://medium.com/google-cloud/kubernetes-nodeport-vs-loadbalancer-vs-ingress-when-should-i-use-what-922f010849e0  

## Access from External  
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

## Access to External
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