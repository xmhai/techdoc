## Useful Commands
```sh
# overall information
kubectl config view
kubectl cluster-info
kubectl get nodes

# Run pod as interactive shell  
# For troubleshooting of network etc.
kubectl run -i --tty busybox --image=busybox -- sh  
kubectl attach busybox -c busybox -i -t  

# inspect image content
docker inspect image_name
docker run -it --entrypoint sh image_name
jar xvf filename

# Trouble springboot application
kubectl logs ...
# check spring active profile in log output
# define env SPRING_PROFILES_ACTIVE in deployment.yaml
https://www.tutorialworks.com/spring-boot-kubernetes-override-properties/  

# run nignx
kubectl create deployment --image=nginx nginx
kubectl expose deployment nginx --type=NodePort --port=80

# edit object definition
kubectl get deploy deploymentname -o yaml > object.yaml
```
