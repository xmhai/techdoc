## Useful Commands
```sh
# Run pod as interactive shell  
kubectl run -i --tty busybox --image=busybox -- sh  
kubectl attach busybox -c busybox -i -t  

# run nignx
kubectl create deployment --image=nginx nginx
kubectl expose deployment nginx --type=NodePort --port=80
```
