## Concept
- pom  
  Include spring-boot-starter-validation
- Controller  
  Add @Valid to controller method parameter, so that the validation will be triggered.
- Bean  
  Add javax.validation.constraints.* to Bean properties. e.g. @NotNull.
- Nested Object  
  Add @Valid to Bean Properies, so that nested object can be validated. 
- Array Object
- Display Binding Error  
  server.error.include-message=always or write ControllerAdvice
- Spring group validation @Validated can validate parts of the attributes.  
  https://www.baeldung.com/spring-valid-vs-validated
- Custom Validator  
  https://www.baeldung.com/spring-mvc-custom-validator


## Controller
- Request and Response Handler  
https://stackoverflow.com/questions/29517613/how-exactly-works-requestbody-annotation-and-how-it-is-related-to-the-httpmessa
- Default message converter  
https://www.baeldung.com/spring-httpmessageconverter-rest
  - Client side send request with "Content-Type" Header.
  - HttpMessageConverter#canRead check the converter can handle the request.

## @RequestBody
- MappingJackson2HttpMessageConverter/MappingJacksonHttpMessageConverter maps the HttpRequest body to java object.

## @ResponseBody
- Can set the content type that our method returns. e.g. produces = MediaType.APPLICATION_XML_VALUE to return XML.
- Based on the "Accept" header or produce???

## Validation  
https://progressivecoder.com/handling-validations-in-spring-boot-application/  
https://self-learning-java-tutorial.blogspot.com/2021/03/spring-boot-rest-handle-method-argument.html  


