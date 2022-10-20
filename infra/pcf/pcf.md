- Articles
  - https://blogs.sap.com/2021/01/20/get-certified-cloud-foundry-certified-developer-cfcd/#:~:text=To%20actually%20start%20preparing%20for,sample%20code%2C%20and%20video%20tutorials.&text=This%20course%20is%20highly%20recommended,Illustration%20from%20the%20course.

## Learning Path
- Course  
  https://www.edx.org/course/introduction-to-cloud-foundry
  https://learning.edx.org/course/course-v1:LinuxFoundationX+LFD132x+3T2021/home  
- Concept  
  https://docs.cloudfoundry.org/concepts/index.html
- Developer Guide  
  https://docs.cloudfoundry.org/devguide/index.html
- Cloud Foundry CLI Reference Guide
  http://cli.cloudfoundry.org/en-US/v6/push.html

## CF commands
```sh
cf push [-f MANIFEST]
cf app NAME
cf scale NAME -i 2
cf logs NAME
cf delete NAME
# When operator update the buildpack or base image, this command uses the same app code, but creates a new container image.
cf restage NAME
# When container env changed or memory, this command starts a new instance and destory old one.
cf restart NAME
# Can deployment
cf cancel-deployment NAME
```

## Concept
- Stage: Build the image
- Container Runtime: Diego
- Scaling: Vertical scaling (CPU and Memory requirement) are discovered during development phase; Horizonal scaling to handle workload.