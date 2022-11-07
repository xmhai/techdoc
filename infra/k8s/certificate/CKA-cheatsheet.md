## Scheduling
- nodeName: when not using kube-scheduler
  ```yaml (pod)
  nodeName: controlplane
  ```
- taint node/pod toleration
  tell node only accept pods with toleration
  ```sh
  k taint node NAME KEY_1=VAL_1:NoSchedule|NoExecute|PreferNoSchedule
  ```
  ```yaml
  tolerations:
  - key: "key1"
    operator: "Equal"
    value: "value1"
    effect: "NoSchedule"
  ```

- cordon
  tell node not to accept any pod

- label node/nodeSelector  
  simple rule to assign node
  ```sh
  k label node NAME KEY=VAL
  ```
  ```yaml
  nodeSelector:
    key: "value"
  ```

- affinity/anti-affinity  
  complex assign logic

- To ensure the pod placement on certain node, use the combination of taint and node affinity

## Cluster Maintenance
- Node Maintenance
```sh
k drain node NAME
k cordon node NAME
k uncordon node NAME
```

## RBAC
```
k create role|rolebinding|clusterrole|clusterrolebindding|sa -h
k ... --as user
k auth can-i --list --as user
```