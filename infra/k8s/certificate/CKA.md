## Concept
Below are Kubernetes components which can be running as pod (manifest can be found under /etc/kubernetes/manifests/) or linux service (can be observed by ps -aux | grep ...)  

- api-server
  - running on master node
  - need to point to etcd

- etcd
  - running on master node
  - store object definition when object is created by kubectl run/apply/create etc.
  - store status which sent by kubelet

- scheduler
  - running on master node
  - need to point to api-server

- controller manager
  - running on master node
  - need to point to api-server

- kubelet
  - running on all nodes
  - communicate with api-server for status

- kube-proxy
  - running on all nodes
  - maintain ip-table for service routing (services don't have network interface, so it is not routable)

- containter runtime
  - running on all nodes

- Network (calico, flannel etc.)
  - running on all nodes
  - create overlay network on all nodes

## Scheduling
- nodeName: when not using kube-scheduler

- taint node/pod toleration
  tell node only accept pods with toleration

- cordon
  tell node not to accept any pod

- label node/nodeSelector  
  simple rule to assign node

- affinity/anti-affinity  
  complex assign logic

- To ensure the pod placement on certain node, use the combination of taint and node affinity

- Resource requests and limits
  - When cpu request exceeds limit, pod will be throttled.
  - When memory request exceeds limit, pod will be terminated.

- DaemonSet
  - By default not controled by scheduler.
  - Kubernetes internally use Node Affinity to implement daemonset.

- Static Pod
  - Owner is the Node, not ReplicaSet

- Multiple Scheduler
  - check scheduler event: k get events -o wide
  
## POD Networking
```sh
# Sample commands to create pod network

# Create network namespace (Linux)
# When container is created, kubenetes create namespaces for them, so the container has its own network interface, routing and arp

# create virtual bridge network
ip link add v-net-0 type bridge
ip link set v-net-0 up
ip addr add 192.168.15.5/24 dev v-net-0

# create virtual network cable to connect container to bridge network
ip link add veth-red type veth peer name veth-red-br

# Attach namespace to a bridge network on each node
ip link set veth-red netns red
ip link set veth-red-br master v-net-0

# Assign IP addr
# IP Range can be viewed from CNI plugin logs (e.g. weave pod logs)
ip -n red addr add 192.168.15.1 dev veth-red

# Bring interface
ip -n red link set veth-red up

# To connect between nodes, setup routing table and NAT on host node

# Service ip is stored in iptables, and managed by kube-proxy
# Service ip range can be view from api-server command line (e.g. ps aux | grep api-server)
iptables ...

# DNS
# When pod is created, Kubelet will set container dns server in resolv.conf to kube-dns (which is a service pointing to CoreDNS pods)
```

