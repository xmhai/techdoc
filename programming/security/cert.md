## Concept  
https://en.wikipedia.org/wiki/Public_key_certificate  
https://www.ssl.com/guide/pem-der-crt-and-cer-x-509-encodings-and-conversions/  

The certificate, in addition to containing the public key, contains additional information such as issuer, what the certificate is supposed to be used for, and other types of metadata.  

- Typically, a certificate is itself signed by a certificate authority (CA) using CA's private key. This verifies the authenticity of the certificate.  
- Base64 encoding. It's done that way probably for a similar reason that email attachments are converted to that - basically to ensure they can transport through protocols and mechanisms designed for ASCII only.  

## Process
https://www.baeldung.com/openssl-self-signed-cert  
- (Optional) Create a Self-Signed Root CA  
- Creating a Private Key
- Creating a Certificate Signing Request
- (Optional) Creating a Self-Signed Certificate

## File Formats  
  https://docs.microfocus.com/SM/9.51/Hybrid/Content/security/concepts/what_are_pem_files.htm  
- PEM: .pem, .crt, .cer, .key  
  a common format for X.509 certificate, CSRs, and cryptographic keys.  
  -----BEGIN CERTIFICATE----- and -----END CERTIFICATE-----
- DER: .der
- PKCS#7  
- PKCS#12

## Tools
- OpenSSL  
  a command-line toolkit for working with X.509 certificates, certificate signing requests (CSRs), and cryptographic keys. 

## keystore format
- cacerts  
  https://docs.microfocus.com/SM/9.51/Hybrid/Content/security/concepts/what_is_a_cacerts_file.htm  
  is a collection of trusted certificate authority (CA) certificates.  
- keytool  
  http://tutorials.jenkov.com/java-cryptography/keytool.html  
