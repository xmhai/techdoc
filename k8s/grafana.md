## set context
To make Grafana run under context path like '/grafana'
Need to add below to the values.yaml
```yaml
grafana.ini:
  server:
    root_url: https://subdomain.example.com/grafana
```
