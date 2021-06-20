
---
**Error**: kubectl get all - command return - Throttling request  
**Solution**: run kubectl **-v=9** get all  
find the permissions for your cache directory within .kube are invalid  
sudo chown hai ~/.kube/cache  

---
**Error**: Unable to connect to the server: x509: certificate relies on legacy Common Name field, use SANs or temporarily enable Common Name matching with GODEBUG=x509ignoreCN=0  
**Solution**: export GODEBUG=x509ignoreCN=0  

---
**Error**: WARNING: Kubernetes configuration file is group/world-readable
**Solution**: chmod 600 .kube/config

---
**Error**: pod in status of “CrashLoopBackOff”  
**Diagnosis**: run below command to view error message  
- kubectl describe -n nginx-ingress pod <pod_name>
- Find the pod running on which node, and go to the node run “docker ps -a” to get failed container
-	Docker logs <container_id>

---
**Error**: Rancher Cluster Explorer page shows "500 error" page newly created RKE cluster   
**Diagnosis**:  
```sh
# check pod cattle-cluster-agent in status of “CrashLoopBackOff”
k describe pod cattle-cluster-agent-747c5b8bbb-gmvrl -n cattle-system
docker ps -a (find the error cattle-cluster-agent container)
docker logs 86f3bf662747
find error "Invalid numeric literal at line 1, column 10"
google "cattle-cluster-agent Invalid numeric literal at line 1, column 10", and find the solution.  
```
**cause**: you probably followed the tutorial and used the standard hostname rancher.my.org as it seems - that resolves to an existing public ip. it should resolve to the local rancher ip.   
https://github.com/rancher/rancher/issues/22063  
**Solution**: 
```sh
kubectl -n cattle-system patch  deployments cattle-cluster-agent --patch '{
    "spec": {
        "template": {
            "spec": {
                "hostAliases": [
                    {
                      "hostnames":
                      [
                        "rancher.my.org"
                      ],
                      "ip": "192.168.10.10"
                    }
                ]
            }
        }
    }
}'
```
