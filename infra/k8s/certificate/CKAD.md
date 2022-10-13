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

## Docker Commands
```sh
docker run ubuntu [COMMAND]
# OR define in docker file: CMD [COMMAND], where COMMAND can written in two formats:
# - in normal string like: CMD sleep 5
# - in JSON format ["command", "param1", ...], e.g. CMD ["sleep", "5"]

# If we want to define parameter for a docker image, use ENTRYPOINT
# e.g. docker run ubuntu-sleeper 10
ENTRYPOINT ["sleep"]
# use [CMD] to define default paramter
CMD ["5"]
# result in sleep 5
# ENTRYPOINT can be overwritten by --entrypoint option.
```

## Kubernetes Commands
```sh
# Shortcut for objects: po/svc/deploy/rs/cm

kubectl cluster-info
kubectl get nodes

kubectl run nginx --image nignx
kubectl exec nginx -- whoami

kubectl create -f <pod.yaml>
kubectl get pods [-o=wide] [--watch] [-l key=value,key=value]
kubectl describe pod/<pod-name>
kubectl get pod/<pod-name> -o=yaml > pod-definition.yaml
kubectl edit pod/<pod-name>

kubectl get rs
kubectl get rs/<rs-name> -o yaml > rs.yaml
# to scale
kubectl edit rs/<rs-name>
kubectl scale --replicas=2 rs/<rs-name>

kubectl create deployment nginx --image nignx
kubectl set image deploy/<deployment-name> <name>=<image>
kubectl get all
kubectl get deploy
kubectl scale deployment nginx --replicas=4

kubectl create namespace <ns>
kubectl config set-context $(kubectl get current-context) --namespace=<ns>

# Imperative Command
--dry-run
-o yaml
k run nginx --image=nginx [--labels="tier=db,..."] [expose=true] [-- <arg1>...] [--command -- <cmd> <arg1>...] [--env="tier=db" ...] --dry-run=client -o yaml
k create deployment nginx-deployment --image=nginx --dry-run=client -o yaml
# Need to update pod selector with this approach
kubectl create service clusterip nginx-service --tcp=80:80 --dry-run=client -o yaml
kubectl create service nodeport nginx-service --tcp=80:80 --node-port=30080 --dry-run=client -o yaml

k create configmap <configmap-name> --from-literal=COLOR=blue --from-literal=SHAPE=circle
k create configmap <configmap-name> --from-file=app.properties
#envFrom/valueFrom/volumes:configMap:name:<config-map>

k create ingress <ingress-name> --rule="/path=service:port"

k taint node <node-name> <key>=<value>:[NoSchedule|PreferNoSchedue|NoExecute]
# for simple node assign
k label node <node-name> <key>=<value>

k rollout status deployment/<deployment-name>
k rollout histry deployment/<deployment-name>
k rollout undo deployment/<deployment-name>

k auth can-i create deployments

k api-resources
k explain

kubectl-convert

k proxy 8001&
curl localhost:8001/apis/authorization.k8s.io
```
## Manifest
```yaml
# POD
replicas: 3
initContainers:
- image:
  name:
  command: ["sleep", "5"]
  args:
containers:
- readinessProbe:
    httpGet:
      path: /ready
      port: 8080
    
  livenssProbe:
    httpGet:
      path: /ready
      port: 8080
securityContext:
  runAsUser: 1001
  capabilities:
serviceAccountName: sa
resources:
  requests:
    cpu:
    mem:
  limits:
    cpu:
    mem:
tolerations:
- key: "key"
  operator: "Equal"
  value: "blue"
  effect: "NoSchedule"
nodeSelector:
  <label>:<value>
restartPolicy: Always|Never

# Job
completions: 3
parallelism: 3
# CronJob
shedule:
jobTemplate:
```

## Note
UwemcUSQ@ZIHI2TN