## Preparation
```sh
alias k=kubectl
--dry-run=client -o yaml
k config current-context
k config set-context
```

## Pod
```sh
# get pod
k get po -o wide --show-labels --watch
# delete pod
k delete pod <pod-name> -f
# create pod
k run NAME --image=IMAGE --env="KEY=VALUE" --labels=KEY=VALUE -- COMMAND ARGS
# run command
k exec NAME -- COMMAND
```

```yaml
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
volumes:
  - name: volume-name
    emptyDir: {}
```

## ConfigMap
```sh
k create config -h
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
k scale deploy NAME --replicas=2
k set image 
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

## Troubleshoot Pod
```sh
k get deploy
k describe po POD-NAME
k logs POD-NAME
k get ep
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
helm search hub|repo <chart>
helm install <release-name> <chart-name>
helm list
helm uninstall <release-name>
helm pull --untar <chart-name>
helm install <release-name> ./<local-folder>
```