# Installation

## Infra Setup
- Create Kubernetes Service
- Create Azure Database  
  Access via public dns: dns/username/password 
- Create Azure Redis  
  Access via public dns: dns/access-key
- Create RabbitMQ Service  
  - Application access via k8s Local DNS: rabbitmq-0.rabbitmq.default.svc.cluster.local (username/password: guest/guest)
- Create RabbitMQ Management Service  
  - Access via Loadbalancer Service (public IP is added to azure FrontEnd IP)
- Create Ambassador Ingress Controller

## Application Setup
- Push application images to Container Registry.
- Create ConfigMap from application.yaml & redisson.yaml
  https://kubernetes.io/docs/tasks/configure-pod-container/configure-pod-configmap/#create-configmaps-from-directories  
- Create Deployment  
  Mount configMap to path
  https://kubernetes.io/docs/tasks/configure-pod-container/configure-pod-configmap/#populate-a-volume-with-data-stored-in-a-configmap  
```yaml
      volumes:
        - name: some-config
          configMap:
            name: <config-name>
            defaultMode: 420
...
      containers:
          volumeMounts:
            - name: <config-name>
              mountPath: /usr/local/config
```
