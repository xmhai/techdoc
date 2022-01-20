## Concept  
https://sshn.medium.com/how-to-get-started-with-ansible-in-10-minutes-e174037341f3  

- folder structure must be correct
- remote 
- inventory (file)
  - list the infrastructure that you want to manage under groups.
  - use [group.var] to specify extra information about the server.
  
## Troubleshooting
- Error: to use the 'ssh' connection type with passwords, you must install the sshpass program.  
Solution: https://stackoverflow.com/questions/42835626/ansible-to-use-the-ssh-connection-type-with-passwords-you-must-install-the-s  
sshpass is a command-line tool that allows you to do non-interactive ssh login.
The issue was because of use of attribute ansible_password (or ansible_ssh_pass???) in inventory file /defaults/main.yml (or /etc/ansible/host).  
having "ansible_password" defined will supersede your private key setting, and cause ansible to want to use sshpass -- usually this is due to group_vars combining in an unexpected way.  