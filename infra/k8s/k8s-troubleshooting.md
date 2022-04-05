## pod
```sh
k describe pod <pod>
# view realtime log
k log <pod> -f
# view previous terminated pod log for the reason of pod restart
k log <pod> -p
# sh into container
k exec -it <pod> -- /bin/sh
cat /etc/os-release
k exec <pod> -- env
```
## service