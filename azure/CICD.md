## Azure Pipeline to build and push docker image to AWS ECR
https://medium.com/@TimvanBaarsen/build-your-spring-boot-project-using-azure-pipelines-in-azure-devops-3305977991d  

https://www.devguides.dev/how-to-build-and-deploy-docker-images-from-azure-devops-to-aws-ecr/  
There are two errors in this article:
- Variable name is AWS_SECRET_ACCESS_KEY instead of AWS_SECRET_ACCESS_KEY_ID
- Should include aws configure commands in script (refer to azure-pipelines.yml)
