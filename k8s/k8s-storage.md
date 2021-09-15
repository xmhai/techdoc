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

