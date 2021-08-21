## Resource
https://kubernetes.io/docs/concepts/configuration/manage-resources-containers/  
https://cloud.google.com/blog/products/containers-kubernetes/kubernetes-best-practices-resource-requests-and-limits  
https://spring-gcp.saturnism.me/deployment/kubernetes/resources  
- Each node has a maximum capacity for each of the resource types: the amount of CPU and memory it can provide for Pods.  
- However, not all resources in a Node can be used to run Pods. The operating system and the kubelet require memory and CPU too. For different k8s provider, the reserved number is different.  
  https://learnk8s.io/allocatable-resources  
- The scheduler ensures that, for each resource type, the sum of the resource requests of the scheduled Containers is less than the capacity of the node.
- It is a general practice to set a limit on CPU/Memory usage by the containers.
- Some kubernetes provider will set default limit to namespace. e.g. GCP is 100m
  kubectl describe ns default

## CPU
https://kubernetes.io/docs/tasks/configure-pod-container/assign-cpu-resource/  
- CPU limit is a compressible resource. If the application exceeds the CPU limit, it'll simply be throttled, and thus capping the latency and throughput.  
- If you do not specify CPU limit then the container will not have any upper bound on the CPU and it then can use all of the CPU available on the node.  
- Scheduler will check the sum of requested CPU (not limit CPU) for all pods in nodes. If there is no node with sufficient CPU available, the new pod will be in pending status. e.g. kubeadmin reserve 200m CPU.
- If the 

Lab  
- Get number of CPUs  
  cat /proc/cpuinfo  
- Get the pods running in node
  kubectl get pods --all-namespaces -o wide
  kubectl get pods --all-namespaces -o wide --field-selector spec.nodeName=*node*
- Get CPU Request and Limit for a pod  
  kubectl describe pod *pod*

## Memory  
- Memory is not a compressible resource. If the application exceeds the Memory limit, then the container will be killed (OOMKilled) and restarted.  
- **Troubleshooting**  
  Memory limit is easier to detect where we just need to check for the pod's last restart status if it was killed due to Out Of Memory(OOMKilled).