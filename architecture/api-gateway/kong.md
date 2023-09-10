```sh
# Download the RPM Package
curl -Lo kong-3.4.0.rhel7.amd64.rpm $(rpm --eval https://download.konghq.com/gateway-3.x-rhel-7/Packages/k/kong-3.4.0.rhel7.amd64.rpm)

# Install Kong
sudo yum install ./kong-3.4.0.rhel7.amd64.rpm

# (For Enterpise Edition) Prepare Kong Enterprise License
copy license.json /etc/kong/license.json

# Copy Kong Configuration
sudo cp /etc/kong/kong.conf.default /etc/kong/kong.conf

# Prepare Declarartive Configuration File
# Create a sample kong.yml using following command
kong config -c kong.conf init
sudo vi /etc/kong/kong.yml
# copy the sample content from https://docs.konghq.com/gateway/latest/production/deployment-topologies/db-less-and-declarative-config/

# Modify Kong Configuration to point to above file
sudo vi /etc/kong/kong.conf
# Enable DBless Mode
database=off
declarative_config={PATH_TO_KONG.YML}

#######################################################################################################
### Below steps are for running service as kong user. However user service is not supported in Centos.
#######################################################################################################

# Prepare Service File
sudo cp /lib/systemd/system/kong.service /home/kong
sudo chown kong:kong /home/kong/kong.service
sudo chmod 600 /home/kong/kong.service

# Update Service File to ensure it can be run by the kong user without requiring elevated privilledges
sudo vi /home/kong/kong.service
# Comment out User directive
[Service]
#User=root
# start the service on boot
[Install]
WantedBy=default.target

# Enable Lingering for Kong User (allow user to run long-run service)
sudo loginctl enable-linger kong

# Prepare Service File Location
sudo mkdir -p /home/kong/.config/systemd/user
sudo cp /home/kong/kong.service /home/kong/.config/systemd/user

# Finally enable and start kong service (as kong user)
systemctl --user enable kong.service
systemctl --user start kong.service

#######################################################################################################
### Install as system service
#######################################################################################################
sudo cp /lib/systemd/system/kong.service /etc/systemd/system/
sudo systemctl enable kong.service
sudo systemctl start kong.service
```

## Route
test with Postman, url: https://srv:8443/test, Header: apikey:my-key
