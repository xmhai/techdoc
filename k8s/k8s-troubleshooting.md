
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
- **kubectl describe** -n nginx-ingress pod <pod_name>
- Find the pod running on which node, and go to the node run “**docker ps -a**” to get failed container
- **Docker logs** <container_id>
