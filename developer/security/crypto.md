## Terms  
- ASN.1  
  https://gist.github.com/awood/9338235  
  Much of the data in the PKI world is stored in ASN.1. It is like XSD for XML.  
  Following the example ASN.1 definition for RSA Public Key:  
    ```sh
    # RSA Public Key file (PKCS#1)
    RSAPublicKey ::= SEQUENCE {
        modulus           INTEGER,  -- n
        publicExponent    INTEGER   -- e
    }
    # Public Key file (PKCS#8)
    PublicKeyInfo ::= SEQUENCE {
        algorithm       AlgorithmIdentifier,
        PublicKey       BIT STRING
    }

    AlgorithmIdentifier ::= SEQUENCE {
        algorithm       OBJECT IDENTIFIER,
        parameters      ANY DEFINED BY algorithm OPTIONAL
    }    
    ```
  Common pattern in ASN.1 is TLV, Type / Length / Value.  
  https://stackoverflow.com/questions/55357924/asn-1-der-encoding-of-integers    

- DER  
  https://adangel.org/2016/08/29/openssl-rsa-java/  

- PEM
  is DER BASE64 encoded.