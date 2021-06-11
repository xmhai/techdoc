# API Gateway With Cognito Setup
XXX - Backend Application/System Name
## Create User Pools
1. Create User Pools "*XXX Users*"
2. Create App Client for each user
   - Go to “App Clients”
   - Set “App Client Name” to *User Name*
   - Check “Generate Client Secret” and “Enable sign-in API for server-based authentication (ADMIN_NO_SRP_AUTH)”
   - Take note of the client id and client secret, and send to user
3. Create Resource Servers for XXX APIs
   - Go to “Resource Servers”
   - Set “Identifier” to value “*xxx-api*”
   - Add “scope”: name = “read”, Decription=”read”
4. Configure “App Client Settings”
   - Go to “App Client Settings”
   - Check “Client credentials”
   - Check All Allowed Custom Scopes
5. Configure Domain name
   - Set domain prefix to *xxx* or Use own domain

## Configure API Gateway
1. Create EC2 instances in **private** subnet
2. Create **Internal NLB** to load balance EC2 instances
3. Create VPC Link
4. Create REST API "*xxx-api*"
   - Create Method GET
   - Integration Type: VPC Link
   - Use Proxy Integration
   - Method: GET
   - VPC Link: vpclink
   - Set Endpoint to "http://api.aws.com/*path*"
5. Create Authorizer
   - Name: *XXXAuthorizer*
   - Type: Cognito
   - User Pool: *XXX Users*
   - Token Resource: Authorization
6. Create API key for each insurer
   - send to insurer together with client ID and secret
7. Create Usage Plan “*XXXPlan*”
   - Add API Stage
   - Add API Keys
8. Enable CORS from “action” dropdown list
9. Set Method Authorization
   - Authorization: *XXXAuthorizer*
   - Oath Scope: *xxx-api*/read
   - Api Key required: true
10.	Setup Custom Domain Name

## Invoke API (Use Postman to test)
1. Get Access Token Request
   - Method: POST
   - Url: https://*xxx*.auth.ap-southeast-1.amazoncognito.com/oauth2/token
   - Authorization Type: Basic Auth
   - Username: <client id>
   - Password: <client secret>
   - Body: x-www-form-urlencoded
   - KEY: grant_type
   - VALUE: client_credentials
   - Get Access Token from response
2. Invoke API
   - Method: GET
   - Url: get invoke url from Stages
   - Authorization Type: Bearer Token; token: <access_token>
   - Header: x-api-key: <api_key>

