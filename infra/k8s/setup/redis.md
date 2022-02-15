```yaml
apiVersion: apps/v1
kind: Deployment
metadata:
  name: redis
  labels:
    app: redisapp
spec:
  replicas: 1
  selector:
    matchLabels:
      app.kubernetes.io/name: redisapp
      app.kubernetes.io/instance: redisapp
  strategy:
    rollingUpdate:
      maxSurge: 1
      maxUnavailable: 1
    type: RollingUpdate
  template:
    metadata:
      name: redisapp
      labels:
        app: redisapp
        app.kubernetes.io/name: redisapp
        app.kubernetes.io/instance: redisapp
      annotations:
        co.elastic.logs/enabled: "true"
        co.elastic.logs/processors.1.decode_json_fields.fields: "message"
        co.elastic.logs/processors.1.decode_json_fields.add_error_key: "true"
        co.elastic.logs/processors.1.decode_json_fields.overwrite_keys: "true"
        co.elastic.logs/processors.1.decode_json_fields.target: ""
    spec:
      containers:
        - image: redis:6.0.14-alpine
          name: redis
          ports:
            - containerPort: 6379
              name: redis
          volumeMounts:
            - name: redis-data
              mountPath: /data
      volumes:
        - name: redis-data
          hostPath:
            path: /data/redis
            type: DirectoryOrCreate

---
apiVersion: v1
kind: Service
metadata:
  name: redis
  labels:
    app: redisapp
    app.kubernetes.io/name: redisapp
    app.kubernetes.io/instance: redisapp
  annotations:
spec:
  ports:
  - name: redis
    protocol: TCP
    port: 6379
    targetPort: 6379
    nodePort: 31379
  selector:
    app.kubernetes.io/name: redisapp
    app.kubernetes.io/instance: redisapp
    app: redisapp
  type: NodePort
```