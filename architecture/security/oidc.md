https://developer.okta.com/blog/2019/10/21/illustrated-guide-to-oauth-and-oidc
https://www.youtube.com/watch?v=SvppXbpv-5k

The OpenID Connect flow looks the same as OAuth. The only differences are
- in the initial request, a specific scope of openid is used.
- in the final exchange the Client receives both an Access Token and an ID Token.

