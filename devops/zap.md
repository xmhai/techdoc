## Installation
- To run with latest java version, using **zap.bat** instead of zap.exe
- Install ZAP root CA under "Tools"->"Option"->Dynamic SSL

## Configuration
- ZAP Mode
  - Safe
  - **Protected**
  - Standard
  - ATTACK

- Scan Policy for Active Scan
  - Policy: to control the number of scan
  - Technology: to select related technology
  - Input Vector
  - Custom Vector

## Concept
- Configure Context  
  Only url in scope can be scanned.

- Passive Scan  
  - Does't modify the request and response.
- Active Scan  
  - Attempts to find potential vulnerabilities by using known attacks against the selected targets. You must perform active scan only if you have permission to test the application
  - 
- Automated Scan
  - Trigger spider to find the seeds
  - Perform passive scan.
  - Perform active scan.
- Manual Explore

- Spider
  - Traditional Spider vs AJAX Spider  
    AJAX Spider use for javascript test.
  - Spider Attack will perform passive scan as well.

- Fuzzer

## Scan
- Option
  - Configure "Global Exclude Url..."
- Manual Explorer
  - Get the root node.
  - Add root node to new context.
- Spider the site
  - Configure Authentication and Session
  - "Attack" -> "Spider..."
- Authentication
  - Form-based authentication
  - JWT-based
    Get the jwt by manual login.
    Use "Option"->"Replacer" to replace "Authorization" header value with jwt.
    or (for Automation Test)
    set Authentication Header Environmental Variables: ZAP_AUTH_HEADER_VALUE
    https://www.zaproxy.org/docs/desktop/start/features/authentication/  
    https://groups.google.com/g/zaproxy-users/c/U9Yla0bArtQ?pli=1  
  - Use script for Authentication  
    https://www.devonblog.com/continuous-delivery/owasp-zap-for-apis-using-custom-script-based-authentication/
- Attack
  - "Attack" -> "Active Scan..." 
    - Configure "Technology"
  - "Attack" -> "Fuzz..."

## Tips
- For JWT authentication, make sure the token is valid, check Option->Replacer. Remove the "Authorization" http header before doing the manual testing (Need to double-check **Option->Replacer** after each restart!!!).
- Validate JWT token in website.
- Firefox is preferred for ZAP.
- Import ZAP certificate in to browser.
- To completely uninstall ZAP, need to delete C:\Users\\\<user>\OWASP ZAP folder.  
  https://www.zaproxy.org/faq/what-is-the-default-directory-that-zap-uses/
- ZAP error log for problem like Chrome not startup.

## Troubleshooting
- Spring Login which has a redirection return
  https://stackoverflow.com/questions/33767728/how-to-perform-authentication-with-zap-and-http-302  
  - ZAP Detects the user is not loged in (because, say the log in indicator string is not present) 
  - ZAP automatically sends the POST request that I have flagged as form-based authentication
  - The post request returns a HTTP 302 with an empty body - As ZAP cannot find in the HTTP 302 body neither the login nor the logout indicator, I am returned to the login page.
  - so auto login does not work.

- csrf
  Use HttpSender to fire a request to get and replace the csrf.