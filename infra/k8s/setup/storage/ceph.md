## Concept  
[1] https://www.digitalocean.com/community/tutorials/how-to-set-up-a-ceph-cluster-within-kubernetes-using-rook  
[2] https://cloudopsofficial.medium.com/the-ultimate-rook-and-ceph-survival-guide-eff198a5764a  
[3] https://elastisys.com/elastisys-engineering-how-to-set-up-rook-with-ceph-on-kubernetes/  

- Ceph is a distributed storage system
- Rook is an orchestrator for a diverse set of storage solutions including Ceph.  

Ceph
- Since Ceph requires extra drives to store the data, it is recommended to have a set of dedicated storage nodes.
- Follow [3] installation, we can see the physical storage is specified in cluster.yaml
```yaml
  storage: # cluster level storage configuration and selection
    useAllNodes: true
    useAllDevices: true
    #deviceFilter:
    config:
      # crushRoot: "custom-root" # specify a non-default root label for the CRUSH map
      # metadataDevice: "md0" # specify a non-rotational storage so ceph-volume will use it as block db device of bluestore.
      # databaseSizeMB: "1024" # uncomment if the disks are smaller than 100 GB
      # journalSizeMB: "1024"  # uncomment if the disks are 20 GB or smaller
      # osdsPerDevice: "1" # this value can be overridden at the node or device level
      # encryptedDevice: "true" # the default value for this option is "false"
# Individual nodes and their config can be specified as well, but 'useAllNodes' above must be set to false. Then, only the named
# nodes below will be used as storage resources.  Each node's 'name' field should match their 'kubernetes.io/hostname' label.
#    nodes:
#    - name: "172.17.4.201"
#      devices: # specific devices to use for storage can be specified for each node
#      - name: "sdb"
#      - name: "nvme01" # multiple osds can be created on high performance devices
#        config:
#          osdsPerDevice: "5"
#      - name: "/dev/disk/by-id/ata-ST4000DM004-XXXX" # devices can be specified using full udev paths
#      config: # configuration can be specified at the node level which overrides the cluster level config
#        storeType: filestore
#    - name: "172.17.4.301"
#      deviceFilter: "^sd."
```