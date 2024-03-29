## Preparation
```sh
alias k=kubectl
--dry-run=client -o yaml
k config current-context
k config set-context
-h for help
```

## Pod
```sh
# create pod
k run NAME --image=IMAGE --env="KEY=VALUE" --env="KEY=VALUE" --labels="KEY=VALUE,KEY=VALUE" -- COMMAND ARGS
# run temporary pod
# busybox must set restart=Never
k run tmp --image=busybox --rm -it --restart=Never -- nslookup POD-IP > output.txt
# get pod
k get po -o wide --show-labels --watch
# delete pod
k delete pod <pod-name> -f
# run command
k exec NAME -- COMMAND
```

```yaml (Pod)
containers:
- command: ["/bin/sh", "-c", "<script>"]
  args: ["$(env-name)"]
  env:
    - name: env-name
      value: env-value
  readinessProbe:
  volumeMounts:
    - name: volume-name
      mountPath: /path
# Major types of volumes: emptyDir(lifecycle as POD)/hostPath/ConfigMap/Secret/PVC
volumes:
  - name: volume-name
    emptyDir: {}
```

## ConfigMap
```sh
k create config -h
# --from-file is used for mount
# --from-literal and --from-env-file is used for setting env
```

```yaml
# use in env
  env:
    - name: name
      valueFrom:
        configMapRef:
          name: name
          key: key

# mount as volume
volumes:
  - name: volume-name
    configMap:
      name: configMap
```

## Secret
replace configMap with secret

## PV/PVC/Mount
```yaml
# pv yaml
hostPath:
  path: /path

# pod yaml - container section
volumes:
- name: volume-name
  persistentVolumeClaim:
    claimName: pvc-name
```

## Deployment
```sh
k create deploy NAME --image=IMAGE --replicas=1 --port=5701
k expose deploy NAME --port=8080 --targetPort=8080
k scale deploy NAME --replicas=2
k set image deploy NAME CONTAINER-NAME=IMAGE-NAME --record
k autoscale deploy NAME --cpu-percent=80 --min=3 --max=5
```

## Deployment Rollout
```sh
k rollout -h
k rollout history deploy NAME
# view details
k rollout history deploy NAME --revision=1
# undo
k rollout undo deploy NAME
k rollout undo deploy NAME --to-revision=1
```

## Troubleshoot
```sh
# Pod troubleshooting
k get deploy
# check READY, STATUS and RESTARTS column
k get pod NAME
k describe TYPE NAME
k logs NAME [-f] [-p]
# Service troubleshooting
k get ep [SERVICE-NAME]
```

## Service
```sh
k create svc clusterip NAME --tcp=<port>:<targetPort>
k run NAME --image=IMAGE --env="KEY=VALUE" expose --port=PORT --targetPort=80 --labels=KEY=VALUE -- COMMAND ARGS
k expose deploy -h
k expose pod -h
```
## Node assignment
- taint node/pod toleration: tell node not to accept which pod
- cordon: tell node not to accept any pod
- label node/nodeSelector: simple rule to assign node
- affinity/anti-affinity: complex assign logic


## Helm
```sh
# repo
helm repo ls
helm repo update
# manage
helm ls [-a]
helm search hub|repo <chart>
helm show values <chart-name>
helm install <release-name> <chart-name> --set KEY=VALUE
helm upgrade <release-name> <chart-name>
helm uninstall <release-name>
# pull to local and install
helm pull --untar <chart-name>
helm install <release-name> ./<local-folder>
```