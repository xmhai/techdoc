## yaml
```yaml
# Explanation: The command ["/bin/sh", "-c"] says "run a shell, and execute the following instructions". The args are then passed as commands to the shell. In shell scripting a semicolon separates commands, and && conditionally runs the following command if the first succeed.
command: ['/bin/sh', '-c']
args: ["echo Configuring application... && \
              mkdir -p /usr/shared/app && \
              echo -e Hello CKAD > /usr/shared/app/hello.txt"]
# Use variable
args: ["mkdir -p /var/app/data & \
counter=1; while true; do touch /var/app/data/$counter-data.txt; counter=$((counter+1)); sleep 10; done"]
```