## How it works  
https://www.alibabacloud.com/help/en/container-service-for-kubernetes/latest/configure-nodelocal-dnscache  

1. pod node-local-dns  
   - It is installed separately and running as DaemonSet 
   - This local caching agent will query kube-dns service for cache misses of cluster hostnames ("cluster.local" suffix by default).
   - StubDomains and upstream servers specified in the kube-dns ConfigMap in the kube-system namespace are automatically picked up by node-local-dns pods.  
2. CoreDNS  
   Only resolve cluster.local domain???  
   How to specify external DNS Server
3. Node
4. External DNS Server 

## resov.conf (pod)
- kube-dns does modify this file (via Kubelet)
- To overide kube-dns, use pod configMap
  ```yaml
  dnsPolicy: "None"
  dnsConfig:
    nameservers:
      - 8.8.8.8
  ```