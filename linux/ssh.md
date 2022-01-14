# Important files for SSH configuration
https://www.techrepublic.com/article/the-4-most-important-files-for-ssh-connections/  

- Local  
    ```sh
    ssh-keygen -t rsa
    # add public key to authorized_keys
    cat id_rsa.pub >> authorized_keys
    ```

- Remote (copy key from remote machine)  
This is for multiple machine setup, e.g. Rancher    
    ```sh
    ssh-copy-id -i ~/.ssh/id_rsa user@host
    # ~, ~/.ssh, ./ssh/authorized_keys must be only readable and writable by the remote user  
    chmod 600 ~/.ssh/authorized_keys  
    chmod 700 ~/.ssh
    ```
- ./ssh/authorized_keys  
  - An authorized key in SSH is a public key used for granting login access to users. The authentication mechanism is called public key authentication.
  - ./ssh/authorized_keys file specifies the SSH keys that can be used for logging into the user account.  
  - Each line contains a public SSH key.  

- ./ssh/known_hosts
  - contains the SSH fingerprints of machines you've logged into.
  - These fingerprints are generated from the remote server's SSH key.
  - during that connection process, if the remote fingerprint is not found, the SSH client will ask if you want to continue, and (when you say yes) save the fingerprint to ~/.ssh/known_hosts.
  - So the purpose is to skip the confirmation of server login.  

- sshd config to allow public key authentication

- Putty configuration
  - sftp copy id_rsa to windows machine
  - puttygen to convert id_rsa to putty key .ppk
  - specify putty connection->data and connection->SSH->auth

- Troubleshooting  
  - Server refused our key  
    Caused by authorized_keys not created.  