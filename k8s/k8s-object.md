## Edit k8s object
kubectl edit svc \<service_name> -n <namespace>  
kubectl get deploy \<deploymentname> -o yaml

## Port  
https://theithollow.com/2019/02/05/kubernetes-service-publishing/  
- Deployment  
  containerPort: is only informational purposes.
- Service (ClusterIP)  
  targetPort: port of your app.  
  port: port of the service. To be used by other pods to access the service. Best pratice to use protocol default port???  
- Service (NodePort)
  targetPort: port of your app.  
  port: port of the service.  
  nodePort: node port (randomly assigned if not specified).
- Service (LoadBalancer)
  Built for cloud. On-premise use MetalLB.
- To access from outside for On-premise k8s  
  - Option 1: External LB + NodePort (30000+)
  - Option 2: MetalLB

## Service (NodePort) 
External-IP you will see when you using a LoadBalancer mode, not a NodePort. In NodePort you have no dedicated external IP for you service, all your nodes will handle the connection to your service.  

## Service & Endpoints
https://kubernetes.io/docs/concepts/services-networking/service/  
- Kubernetes assigns Service an IP address (sometimes called the "cluster IP"), which is used by the Service proxies.  
- The controller for the Service selector continuously scans for Pods that match its selector, and then POSTs any updates to an Endpoint object with same name as Service.
- Services most commonly abstract access to Kubernetes Pods, but they can also abstract other kinds of backends. e.g. External Database.  
  Because this Service has no selector, the corresponding Endpoints object is not created automatically. You can manually map the Service to the network address and port where it's running, by adding an Endpoints object manually

## StatefulSet  
Techworld Nana Statefulset
- Each pod has unique identity.
- Persistent Volume must be sync by the applications (e.g. MySQL).
- Persistent Volume must be avaliable to all nodes (i.e. LocalStorage is cannot be used for StatefulSet). 
- Only the master node is readable/writable, others are only readable.
- When a new pod added in, it will get a new PVC and sync data from previous pod.

## Persistent Volume
- It is just an interface, the acutal physical storage need to be created and managed by k8s admin (e.g. Setup NFS Server, Google Storage).
- The storage is defined in PV yaml.
- PV are **NOT** tied to any node, and it is **NOT** namespaced.  
While PVC is namespaced.
- Created by k8s admin before POD.
- Storage Class provisions PV dynamatically. In PVC, specify the StorageClass.

## Kubernetes Operator
Custom Defined Resource and Controller
