## Concept
- Master Node: api-server, etcd, scheduler and controller
- Worker Node: kubelet, container runtime, container manager.
- Containers insides a pod share network space and storage. So they can use localhost to refer each other.
- yaml
  - Four top level properties: apiVersion, kind, metadata and spec
  - Three types of data in yaml: string, dictionary and list
  - metadata only allow: name and label (dictionary type)
- Deployment automatically create a ReplicaSet
- Namespace
  - Resource limit (ResourceQuota)
  - Access resource in another namespace, use \<service-name>.\<namespace>.svc.cluster.local

## Commands
```sh
kubectl cluster-info
kubectl get nodes

kubectl run nginx --image nignx
kubectl create -f <pod.yaml>
kubectl get pods -o=wide
kubectl describe pod/<pod-name>
kubectl get pod/<pod-name> -o=yaml > pod-definition.yaml
kubectl edit pod/<pod-name>

kubectl get rs
kubectl get rs/<rs-name> -o yaml > rs.yaml
# to scale
kubectl edit rs/<rs-name>
kubectl scale --replicas=2 rs/<rs-name>

kubectl create deployment nginx --image nignx
kubectl get all
kubectl get deploy

kubectl create namespace <ns>
kubectl config set-context $(kubectl get current-context) --namespace=<ns>
```

## Note
UwemcUSQ@ZIHI2TN