- For scp, copy the pem file to home directory, and chmod 400 key.pem.  
Reason: For private key on your Windows filesystem (under /mnt/), You can't modify the permissions of files on Windows's filesystem using chmod on Bash on Ubuntu on Windows. You'll have to copy the private key to your WSL home directory (~) and do it there.  
https://github.com/Microsoft/WSL/issues/81
- scp -i key.pem file azureuser@ip:~/folder
- Download the files and copy to home directory
- 