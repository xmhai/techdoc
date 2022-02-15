## Access
http://srv:31623
guest/guest

## Appendix: Kubernetes manifest
```yaml
apiVersion: apps/v1
kind: StatefulSet
metadata:
  name: rabbitmq
  namespace: default
spec:
  serviceName: "rabbitmq"
  replicas: 1
  selector:
    matchLabels:
      app: rabbitmq
  template:
    metadata:
      labels:
        app: rabbitmq
    spec:
      containers:
      - name: rabbitmq
        image: rabbitmq:3-management-alpine
        env:
          - name: RABBITMQ_ERLANG_COOKIE
            value: "1WqgH8N2v1qDBDZDbNy8Bg9IkPWLEpu79m6q+0t36lQ="
        ports:
        - containerPort: 5672
          name: amqp
        volumeMounts:
        - name: rabbitmq-data
          mountPath: /var/lib/rabbitmq
      volumes:
      - name: rabbitmq-data
        hostPath:
          path: /data/rabbitmq
          type: DirectoryOrCreate

---
apiVersion: v1
kind: Service
metadata:
  name: rabbitmq-management
  labels:
    app: rabbitmq
spec:
  ports:
  - port: 15672
    name: http
    nodePort: 31623
  selector:
    app: rabbitmq
  type: NodePort

---
apiVersion: v1
kind: Service
metadata:
  name: rabbitmq
  labels:
    app: rabbitmq
spec:
  ports:
  - port: 5672
    name: amqp
  - port: 4369
    name: epmd
  - port: 25672
    name: rabbitmq-dist
  - port: 61613
    name: stomp
  selector:
    app: rabbitmq
  type: ClusterIP
```
