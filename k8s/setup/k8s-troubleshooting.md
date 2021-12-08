**In case you face any issue in kubernetes**  
First step is to check if kubernetes self applications are running fine or not. Command to check:-  
kubectl get pods -n kube-system  
If you see any pod is crashing, check it's logs

---
**Error**: "kubectl get nodes" show node NotReady  
**Solution**: kubectl describe node nodename
If everything is alright here, SSH into the node and observe kubelet logs to see if it reports anything.  
```sh
sudo systemctl status kubelet
sudo systemctl status docker
sudo journalctl -u kubelet
#if error is found and fixed, run  
systemctl daemon-reload  
systemctl restart kubelet
# If still didn't get the root cause, check below things:-
Make sure your node has enough space and memory. Check for /var directory space especially. command to check:-  
-df -kh, free -m
Verify cpu utilization with top command. and make sure any process is not taking an unexpected memory.    
```
---
**Error**: Kubernetes stuck on ContainerCreating
**Solution**: sudo journalctl -u kubelet -f

---
**Error**: kubectl get nodes return "the connection to the server 8080 was refused"  
**Solution**: make sure kubeconfig is correct    

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

---
**Error**: DiskPressure cause pod to be evicted  
**Diagnosis**:  
df -h  
shows that /dev/mapper/rootvg-varlv (mnt to /var) only allocated 8G and used 83% of disk space.  
sudo vgdisplay rootvg  
shows that there are still 40G aviable space.   
**Solution**
Increase the lv.  
https://docs.microsoft.com/en-us/azure/virtual-machines/linux/resize-os-disk-gpt-partition  

---
