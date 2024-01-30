https://spring.io/projects/spring-authorization-server
https://docs.spring.io/spring-security/reference/servlet/oauth2/resource-server/jwt.html

https://www.digitalocean.com/community/tutorials/an-introduction-to-oauth-2

# Key Concept
- Resource Owner
- Client
- Resource Server
- Authorization Server
- Authorization Grant:
  - Authorization Code: Server-side applications, redirection-based flow (for Browser)

# Case 1

# Case 2 (RCT)
- Resource Owner:       business user
- Client (Application): oauth2-service
- Resource Server:      rct-backend-services
- Authorization Server: EUA

Step 1: Register RCT with EUA, provides following information:
- Application Name
- Application WebSite
- Redirect URI or Callback URI: need to match with the redirect_uri in authorize request url

Step 2: Once your application is registered, the service will issue client credentials in the form of a client identifier and a client secret.
- Client Identifier: use as part of EUA auth request url
- Client Secret:

Step 3: (Browser Redirection) Application generate auth request url, and redirect the BROWSER to this url
https://forgerock.com/v1/oauth/authorize?response_type=code&client_id=CLIENT_ID&redirect_uri=CALLBACK_URL&scope=read
- client_id=client_id: the application’s client ID (how the API identifies the application)
- redirect_uri=CALLBACK_URL: where the service redirects the user-agent after an authorization code is granted, must match the registerd url
- response_type=code: specifies that your application is requesting an authorization code grant
- scope=read: specifies the level of access that the application is requesting

Step 4: (Browser Redirection) After user authorize at the EUA side, EUA redirect back: 
https://rct.com/callback?code=AUTHORIZATION_CODE

Step 5: (Server to Server) Application Requests Access Token, and EUA return access token
The application requests an access token from the API by passing the authorization code along with authentication details, including the client secret, to the API token endpoint.
https://forgerockcom/v1/oauth/token?client_id=CLIENT_ID&client_secret=CLIENT_SECRET&grant_type=authorization_code&code=AUTHORIZATION_CODE&redirect_uri=CALLBACK_URL

Step 6: (Browser Redirection) Application generate redirect URL to frontend.
The redirect URL contains the access token, and frontend will store locally (e.g. React State or Local Storeage) and use it for future API call.

Step 7: (Browser call Server API) Application make http call with access token in Authroization header.

Step 8: (Server) Server validate access token.
The token can be verified via introspect endpoint or by signature.
