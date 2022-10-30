## StatefulSet  
Techworld Nana Statefulset
- Each pod has unique identity.
- Persistent Volume must be sync by the applications (e.g. MySQL).
- Persistent Volume must be avaliable to all nodes (i.e. LocalStorage is cannot be used for StatefulSet). 
- Only the master node is readable/writable, others are only readable.
- When a new pod added in, it will get a new PVC and sync data from previous pod.

## Kubernetes Operator
Custom Defined Resource and Controller
