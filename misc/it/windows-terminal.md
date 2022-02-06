## Windows Terminal
**Font**   
Settings -> Open JSON file, add setting to profiles defaults section  
```json
        "defaults": {
			"fontFace": "Consolas",
			"fontSize": 11,
		},
```

**Tab Icon**  
To get icon file, we can browser to the website and find the url of favi.ico in page source.  
https://www.redhat.com/misc/favicon.ico

**SSH without password**  
```sh
# From cmd
ssh-keygen -t rsa
# From WSL sh
scp /mnt/c/Users/linhai/.ssh/id_rsa.pub hai@192.168.10.11:~/.ssh/id_rsa.pub
# From remote sh
cat id_rsa.pub >> authorized_keys
chmod 700 ~/.ssh
chmod 600 ~/.ssh/authorized_keys
```
