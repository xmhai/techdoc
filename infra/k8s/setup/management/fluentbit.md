## Installation
https://docs.fluentbit.io/manual/installation/kubernetes  

Note:
- Create elasticsearch service before create pods.
  See Appendix.  
- Check ConfigMap to confirm the Logstash_Format on|off  

## Verify  
k logs fluent-bit-w4nz6 -n logging  
GET _cat/indices (logstashxxx)

## Troubleshooting
Error: logs shows "tail.0 paused (mem buf overlimit)"  
change ConfigMap Mem_Buf_Limit to 50M  

## Appendix  
```yaml
---
apiVersion: v1
kind: Service
metadata:
  name: elasticsearch
  namespace: logging
spec:
  ports:
    - protocol: TCP
      port: 9200
      targetPort: 9200

---
apiVersion: v1
kind: Endpoints
metadata:
  name: elasticsearch
  namespace: logging
subsets:
  - addresses:
      - ip: 192.168.86.43
    ports:
      - port: 9200
```


