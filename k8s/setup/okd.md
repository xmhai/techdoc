## Installation  
https://computingforgeeks.com/setup-openshift-origin-local-cluster-on-centos/  
**NOTE:** To allow remote access:  
oc cluster up --public-hostname=\<Public IP>  
ref: https://github.com/openshift/origin/issues/20726  

## Clean up
```sh
for i in $(mount | grep openshift | awk '{ print $3}'); do sudo umount "$i"; done && sudo rm -rf ./openshift.local.clusterup
```

## Run
```sh
oc cluster up --public-hostname=\<Public IP>
oc login -u system:admin
oc cluster down
```