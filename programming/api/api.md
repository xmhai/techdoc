## API
- Endpoint
  - /api/{version}/{system}/{resource}/{id}/{dependent-resource}/{id}
  - Use nones for resources.  
    e.g. /accounts
  - Use all lowercase letters and using hyphens in place of spaces.
  - POST method for complex request and security.  
    e.g. POST /{resource}/search
  - Map API Operations to HTTP Methods. PUT/DELETE is vulnerable during pen test, so try to avoid.
  - Major version in url, e.g. v1, minor version in HTTP header,  
    e.g. X-MYAPI-VERSION: 1.1

- HTTP Header
  - API implementation should not introduce or depend on headers.
  - Header must not include API or domain specific values.
  - Accept/Accept-Charset (utf-8)/Content-Language/Content-Type
  - API in HTTP header to identify client.

- Request
  - Create separate jar module or package for API request&response. e.g. api
  - Define proper type for request.
  - Use @Valid to do proper validation.

- Response
  - Return null value or not depends on projects. Don't return null value is preferred if there is partial response for different client.
  - Define proper type for response.
  - For array, return empty collection instead of null.

- Error Handling
  - Define a standard error response with controller advice.

- Documentation
  - Swagger

- Logging
  - Use annotation on controller methods for logging.

- API Test
  - Contract Test (tcase): to make sure validation is applied on the API request.
  - Automation Test (Postman): 

- Security
  - Traffic & Transaport
    - DDOS (Cloud Flare/AWS Shield/IP Whitelisting/Request Throttling)
    - Firewall (CloudFlare/AWS WAF/Firewall)
    - TLS
  - Authentication
    - API Key (HTTP Header)
  - Authorisation
    - OAuth2 (Bearer Token in HTTP Header)
    - ACL (API Gateway)
  - Integity
    - Digital Signiture (e.g. JWS)
  - Confidentiality
    - Encryption (e.g. JWE)

- Backward Compatiblity
  - All changes must be additive.
  - All changes must be optional.
  - Sementic of fields must not change.
  