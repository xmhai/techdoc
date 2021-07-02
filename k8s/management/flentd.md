# How it works

# Installation
- Install from Rancher UI, "Tools->Logging"  
  Two daemonset are created:  
  rancher-logging-fluentd-linux (2 pods) 
  rancher-logging-fluentd-reloader (sidecar to container rancher-logging-fluentd)
  rancher-logging-log-aggregator-linux (1 pod)
- Manual install