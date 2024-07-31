# Rolling update
- Default strategy implemented by Deployment Resource.
- Implementation: k8s create two replicaset, add new pod to new ReplicaSet and delete old pod from old ReplicaSet until all old pods are removed from old ReplicaSet, then delete the old ReplicaSet.

# Canary
- One service, two deployment (old and Canary deployment), the service selector the pods from both deployment.
- The Canary Deployment start from 1, so some traffic will go to the Canary Pod and we can verify, if it works, scale the Canary deployment to normal number of Pods, and remove the old Deployment.

# Blue Green
- Blue Test Service, Blue Deployment, Public Service, Green Test Service, Green Deployment
- Deploy the green version, test from Green Test Service, if it works, switch the public service to point to green Pods.
- Can keep Blue deployment for rollback.
- Require identical hardware.
