## ARP
## VRRP
https://raymii.org/s/tutorials/Keepalived-Simple-IP-failover-on-Ubuntu.html
- A fundamental brick for router failover.
- Ensures that one of participating nodes is master.  
  The backup node(s) listens for multicast packets from a node with a higher priority.  
  If the backup node fails to receive VRRP advertisements for a period longer than three times of the advertisement timer, the backup node takes the master state and assigns the configured IP(s) to itself.  
  In case there are more than one backup nodes with the same priority, the one with the highest IP wins the election.