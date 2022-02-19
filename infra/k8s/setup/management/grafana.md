## Data 
- Grafana needs a database to store users and dashboards (and other things).
- By default it is configured to use sqlite3 which is an embedded database, data in /var/lib/grafana (database and plugins)

## Deployment
-  Very lightweight in use of memory and CPU,  
Minimum recommended memory: 255 MB Minimum recommended CPU: 1
- For Rancher, it will install Prometheus and Grafana on k8s.
- Running separately is recommended??? My points:
  - It is dashboard for different parts of the systems, so it should be running in Management zone.
  - Use MySQL as storage

## Installation
https://grafana.com/docs/grafana/latest/administration/configuration/

**Set context**  
To make Grafana run under context path like '/grafana'
Need to add below to the values.yaml for helm installation
```yaml
grafana.ini:
  server:
    root_url: https://subdomain.example.com/grafana
```
## Access Grafana
For installation on VM:  
http://ip:3000  
admin/admin

### Access (installation on k8s):  
**Option 1**  
Use port forward to access grafana:
kubectl -n loki port-forward svc/loki-grafana 3000:80  
NOTE: port forwading only works from local machine, which means you cannot remote access the port-forward machine.

Visit localhost:3000  
- Username is admin
- To find out password, run below command:  
kubectl -n loki get secrets  
kubectl -n loki get secret **loki-grafana** -o yaml  
echo "*password_base64_encoded*" | base64 -d; echo

**Option 2**  
Setup ingress (Refer to k8s project grafana-ingress.yaml)