## Concept  
https://www.openssl.org/docs/man1.1.1/man1/  
https://security.stackexchange.com/questions/90169/rsa-public-key-and-private-key-lengths  
Encoding PKCS#1/PKCS#8  
https://tls.mbed.org/kb/cryptography/asn1-key-structures-in-der-and-pem  

RSA is for signature or symentic key encryption.

### Public Key
**Execellent Article (MUST Read)** to explain the Public Key:  
https://stackoverflow.com/questions/18039401/how-can-i-transform-between-the-two-styles-of-public-key-format-one-begin-rsa  
consists **Modulus (2048)** and **Public Exponent**, which is usually quite short (3-65537, 65537 represents as 01 00 01).  
```sh
# RSA ASN.1 PKCS#1 standard
-----BEGIN RSA PUBLIC KEY-----
RSAPublicKey ::= SEQUENCE {
    modulus           INTEGER,  -- n
    publicExponent    INTEGER   -- e
}
-----END RSA PUBLIC KEY-----
```
```sh
# X.509 SubjectPublicKeyInfo/OpenSSL PEM public key standard
# Include Object Identifier to identify the algorithm
# This add extra 32 characters(BASE64) to RSA ASN.1 PKCS#1 pem
-----BEGIN PUBLIC KEY-----
asn1=SEQUENCE:pubkeyinfo

# BITWRAP here
[pubkeyinfo]
algorithm=SEQUENCE:rsa_alg
pubkey=BITWRAP,SEQUENCE:rsapubkey

[rsa_alg]
algorithm=OID:rsaEncryption
parameter=NULL

[rsapubkey]
n=INTEGER:0xBB6FE79432CC6EA2D8F970675A5A87BFBE1AFF0BE63E879F2AFFB93644\
D4D2C6D000430DEC66ABF47829E74B8C5108623A1C0EE8BE217B3AD8D36D5EB4FCA1D9
e=INTEGER:0x010001
----------------------------------------------------------------
    0:d=0  hl=4 l= 290 cons: SEQUENCE
    4:d=1  hl=2 l=  13 cons: SEQUENCE
    6:d=2  hl=2 l=   9 prim: OBJECT            :rsaEncryption
   17:d=2  hl=2 l=   0 prim: NULL
   19:d=1  hl=4 l= 271 prim: BIT STRING
----------------------------------------------------------------
```
ASN.1 DER (Type/Length/Value): 269 bytes (HSM unsigned integer) / 271 bytes (non-HSM 2's complement integer)  
OpenSSL DER: 294 bytes (include algorithm)  
OpenSSL PEM: 451 bytes (392, i.e. 294*8/6, bytes key data only excluding header/footer, it is the BASE64 encoded of DER format)  

### Private Key
includes the modulus and the public exponent (like the public key) but also the "private exponent" (a number roughly as big as the modulus) and five other values whose size is roughly half of that of the modulus.  
The consequence is that private key is expected to be about five times larger than the public key.
```txt
(PKCS#1)
-----BEGIN RSA PRIVATE KEY-----
RSAPrivateKey ::= SEQUENCE {
  version           Version,
  modulus           INTEGER,  -- n
  publicExponent    INTEGER,  -- e
  privateExponent   INTEGER,  -- d
  prime1            INTEGER,  -- p
  prime2            INTEGER,  -- q
  exponent1         INTEGER,  -- d mod (p-1)
  exponent2         INTEGER,  -- d mod (q-1)
  coefficient       INTEGER,  -- (inverse of q) mod p
  otherPrimeInfos   OtherPrimeInfos OPTIONAL
}
-----END RSA PRIVATE KEY-----

# Can be view by:
# openssl asn1parse -in key.der -inform der
```
PEM (bytes): 1708  

### Openssl Commands
Create self-signed cert
```sh
# generete private key and cert
openssl req -newkey rsa:2048 -nodes -keyout key.pem -x509 -days 365 -out cert.pem

# extract public key (PEM) from cert
openssl x509 -pubkey -noout -in cert.pem  > pubkey.pem

# convert public key from PEM to DER
openssl rsa -pubin -in pubkey.pem -inform PEM -outform DER -out pubkey.der

# convert private key from PEM to DER
openssl rsa -in key.pem -inform PEM -outform DER -out key.der

# parse DER format
# https://www.openssl.org/docs/man1.1.1/man1/openssl-asn1parse.html
openssl asn1parse -in pubkey.der -inform der
```
PEM: 1285 bytes  

### Java
https://adangel.org/2016/08/29/openssl-rsa-java/  
RSA Load key/Encryption/Decryption  
https://www.baeldung.com/java-rsa  

```sh
# Java to get public key
https://docs.oracle.com/javase/8/docs/api/index.html?java/security/spec/X509EncodedKeySpec.html  

X509EncodedKeySpec represents the ASN.1 encoding of a public key, encoded according to the ASN.1 type SubjectPublicKeyInfo. The SubjectPublicKeyInfo syntax is defined in the X.509 standard as follows:
 SubjectPublicKeyInfo ::= SEQUENCE {
   algorithm AlgorithmIdentifier,
   subjectPublicKey BIT STRING }
```
