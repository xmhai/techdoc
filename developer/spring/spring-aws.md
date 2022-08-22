## Spring AWS History
https://spring.io/blog/2021/03/17/spring-cloud-aws-2-3-is-now-available  
On the 17th of April, 2020, it was announced that Spring Cloud GCP and Spring Cloud AWS are no longer part of the Spring Cloud release train. Not being a part of the release train also means moving from the Spring Cloud organization on Github and, as an effect, having new Maven coordinates and package names.

## Reference  
https://docs.awspring.io/spring-cloud-aws/docs/current/reference/html/index.html  
https://docs.awspring.io/spring-cloud-aws/docs/2.3.5/reference/html/index.html  
Old way of AWS integration:  
https://aws.amazon.com/blogs/opensource/getting-started-with-spring-boot-on-aws-part-1/  

## SQS
- If Queue name is not found, it will return "Status Code: 400; Error Code: AWS.SimpleQueueService.NonExistentQueue".
- Must include the IAM user in Access policy, otherwise it will return "Status Code: 403; Error Code: InvalidClientTokenId":  
```json
      "Principal": {
        "AWS": [
          "arn:aws:iam::752871168029:user/app",
          "arn:aws:iam::752871168029:root"
        ]
      },
```
- 