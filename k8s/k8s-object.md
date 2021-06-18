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
