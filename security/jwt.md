## Concept
https://medium.facilelogin.com/jwt-jws-and-jwe-for-not-so-dummies-b63310d201a3  
- In fact a JWT does not exist itself — either it has to be a JWS or a JWE.  
- Nimbus is a JWT implementation in Java.  
- We call a JWS or JWE, a JWT only if it follows the compact serialization (base64url-encoded).  

## JWT
defines a container to transport data between interested parties (e.g. ID token).  
- JOSE Header (Javascript Object Signing and Encryption)  
  - typ (type): media type, recommend "JWT".  
  - cty (content type): only used in nested JWT.  
- payload  
  is known as the JWT claims set. It’s up to the each application of JWT to define mandatory and optional claims.  
  **The payload is not necessarily JSON.**  

## JWS
is a signed JWT.  
- JOSE Header (Javascript Object Signing and Encryption)  
  base64 encoded 
  - alg:  
    All applicable algorithms for signing are defined under the JSON Web Algorithms (JWA) specification.  
  - kid (key indicator):  
    Looking at the kid, the recipient of the message should know where and how to lookup for the key and find it.
- payload
- signature
  - If signature not presented, it is an unsecured JWT.  
  - is computed over the complete JOSE header and JWS payload.

## JWE
- JOSE header  
  - enc: content encryption algorithm.
  - alg: encryption algorithm to encrypt the Content Encryption Key (CEK).  
- JWE Encrypted Key:  
  is a encrypted symmetric key which is 256 bits in size, and used to encrypt the message.  
- Initialization vector:  
  is a randomly generated number, which is used along with a secret key to encrypt data.  
- JWE Additional Authentication Data (AAD) (Optional???)  
  is ASCII value of the encoded JOSE header.
- Ciphertext:  
  is computed by encrypting the plaintext JSON payload using the Content Encryption Key (CEK), the JWE initialization vector and the Additional Authentication Data (AAD) value, with the encryption algorithm defined by the header element enc.
- Authentication Tag:  
  is produced during the AEAD encryption process, along with the ciphertext.
  to ensures the integrity of the ciphertext and the Additional Authenticated Data (AAD).  
