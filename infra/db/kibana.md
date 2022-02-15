## Installation
https://www.elastic.co/guide/en/kibana/current/rpm.html  

Note:  
- Kibana version must be **exactly** same version as ElasticSearch. So follow the steps in "Download and install the RPM manually":  
    ```sh
    wget https://artifacts.elastic.co/downloads/kibana/kibana-7.16.2-x86_64.rpm
    sudo rpm --install kibana-7.16.2-x86_64.rpm
    ```
- Configure
    ```sh
    sudo nano /etc/kibana/kibana.yml  
    # Set server.host  
    ```

## Access
http://srv:5601  

## Uninstall
```sh
sudo systemctl stop kibana.service
sudo yum remove kibana
```

## Troubleshooting
```sh
sudo cat /var/log/kibana/kibana.log
```