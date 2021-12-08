- Local  
ssh-keygen -t rsa
private key file must be readable and writable only by you  
cat id_rsa >> authorized_keys

- Remote  
ssh-copy-id -i ~/.ssh/id_rsa user@host
~, ~/.ssh, ./ssh/authorized_keys must be only readable and writable by the remote user  
chmod 600 ~/.ssh/authorized_keys  
chmod 700 ~/.ssh
