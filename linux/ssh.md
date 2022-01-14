- Local  
    ```sh
    ssh-keygen -t rsa
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
- sshd config to allow public key authentication

- Putty configuration
  - sftp copy id_rsa to windows machine
  - puttygen to convert id_rsa to putty key .ppk
  - specify putty connection->data and connection->SSH->auth

- Troubleshooting  
  - Server refused our key  
    Caused by authorized_keys not created.  