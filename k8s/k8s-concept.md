## Logging
- **Containerized applications** write logs to standard output and standard error streams. (The easiest and most adopted logging method) 
- Docker container engine redirects those two streams to default json-file logging driver.
- The kubelet is responsible for rotating the logs and managing the logging directory structure.
- The kubelet sends this information to the CRI container runtime and the runtime writes the container logs to the given location.
- The logging agent (FluentD) running on every node has access to logging directory, and pushes logs to a backend.
