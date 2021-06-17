**Error**: kubectl get all - command return - Throttling request  
**Solution**: run kubectl **-v=9** get all - find the permissions for your cache directory within .kube are invalid - sudo chown hai ~/.kube/cache  

