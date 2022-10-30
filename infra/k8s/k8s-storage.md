## Concept
- Persistent Volume Claim
- Persistent Volume
- Storage Class
- Physical storage  

## Volume vs. Persist Volume
- Volume is defined within a pod, and shared across contains inside a pod.
- Persist Volume is the first class citizen in Kubernetes, shared across pods.

## Persistent Volume
- It is just an interface, the acutal physical storage need to be created and managed by k8s admin (e.g. Setup NFS Server, Google Storage).
- The storage is defined in PV yaml.
- PV are **NOT** tied to any node, and it is **NOT** namespaced.  
While PVC is namespaced.
- Created by k8s admin before POD.
- Storage Class provisions PV dynamatically. In PVC, specify the StorageClass.

## Provision a Persistent volume: Manual vs Dynamic
https://medium.com/swlh/kubernetes-storage-explained-558e85596d0c#:~:text=Storage%20classes%20are%20created%20as,kubernetes%20cluster%20by%20the%20administrator.&text=Users%20can%20then%20request%20storage,from%20a%20set%20of%20parameters.  
- Manual:  
  - Create the storage (e.g. gcloud compute disks create ...)
  - In the GCE, define the PersistVolume to refer to the storage (GCE is able to find the storage by name)  
- Dynamic:
  - uses an API object called storage class (“StorageClass”) which define the Provisioner (e.g. rancher.io/local-path, kubernetes.io/gce-pd) and extra parameters (e.g. SSD, thus it is named as class)
  - Storage Slass use Provisioner dynamically create persistent volumes when requested using a PVC.

## Volume Binding Mode
- Immediate: PVC to PV binding happen immediately.
- WaitForFirstCustomer: only when pod is created and using the PVC, the binding will happen.

## PersistVolume  
- hostPath  
Kubernetes supports hostPath for development and testing on a single-node cluster. A hostPath PersistentVolume uses a file or directory on the Node to emulate network-attached storage.  
https://kubernetes.io/docs/tasks/configure-pod-container/configure-persistent-volume-storage/#create-a-persistentvolume  
```yaml
apiVersion: v1
kind: PersistentVolume
metadata:
  name: pv-zookeeper
  labels:
    name: pv-zookeeper
spec:
  storageClassName: manual
  capacity:
    storage: 10Mi
  accessModes:
    - ReadWriteOnce
  hostPath:
    path: "/mnt/data" # sudo chmod 777 /mnt/data
```

## Local Persistent Volume vs hostPath
- Kubernetes scheduler ensures that a pod using a Local Persistent Volume is always scheduled to the same node.  

## PersistVolumeClaim
- When using **storageClass** Kubernetes is going to enable "Dynamic Volume Provisioning" which is not working with the local file system.
- Kubernetes - pod has unbound immediate PersistentVolumeClaims  
PersistentVolumeClaims will remain unbound indefinitely if a matching PersistentVolume does not exist. The PersistentVolume is matched with **accessModes** and **capacity**.  
```yaml
apiVersion: v1
kind: PersistentVolumeClaim
metadata:
  name: data-my-cluster-zookeeper-0
  namespace: my-kafka-project
spec:
  accessModes:
    - ReadWriteOnce
  storageClassName: manual
  resources:
    requests:
      storage: 10Mi
```

