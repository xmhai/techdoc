# Concept
- Application logs should output to stdout.
- For application logs that are not directed to standard output (stdout) and standard error (stderr), a common way is to deploy a sidecar logging agent within the application pods, and use volumes to share the logs folder with the agent container which can then access and process the logs and send them to your log-aggregation system.

# How it works
node log files location:  
/var/log/pods/  
/var/log/containers contains the softlink to /var/log/pods/

# Log rotation


# Rancher Logging (2.0-2.4.x)
Two daemonset are created:  
- rancher-logging-fluentd-linux (2 pods)  
  rancher-logging-fluentd-reloader (sidecar to container rancher-logging-fluentd)
- rancher-logging-log-aggregator-linux (1 pod)

# Rancher Logging (2.5.x)
- banzaicloud-logging-operator
- (daemonset) rancher-logging-fluentbit
  In the configMap, output points to rancher-logging-fluentd.cattle-logging-system.svc
- rancher-logging-fluentd
- (daemonset) rancher-logging-rke-aggregator