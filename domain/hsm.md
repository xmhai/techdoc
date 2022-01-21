## Concept  
https://github.com/snowch/hsm-guide/blob/master/book.md  

## Key Management
https://netzts.in/retail-payments-domain/hsm-lmk-zmk-tmk-pvk-cvk/  
Three level key system
### Level 1: 
- LMK  
  - Local Master Keys are a sets of 40 DES keys.  
  - HSM does not store any keys except set of LMK pairs. And with these LMK pairs are used to decrypt those keys (which you sumbit in according command) for further calculation.  

### Level 2 (key-encrypting key)  
- ZMK (Zone Master Key)  
  - Unlike an LMK which does not leave the HSM, ZMKs are intended to be shared between sites to create secure Zones.
  - The ZMK is distributed manually between the sites.  
  - The ZMK allow future (data encrypting) keys to be automatically shared between sites by encrypting those future keys with the ZMK.  
  - In that regard, they work very much like the LMKs with the important exception that they can be shared between users.
  ```sh
  command: Generate Component (GC) - Generate three ZMK components
  # Each of these clear components are kept by a separate custodian that works for the first party and are delivered to different custodians of the second party.
  command: FK - Generate the ZMK from the components (XOR usually)
  # When generating the ZMK, the first party also gives the KCV of the ZMK to the second party (for the example key the KCV is 6CE4CF). That way, the second party can verify the correct reception and data entry of the ZMK components.
  ```
- TMK (Terminal Master Key)  
  - is a key-encrypting key which is distributed manually, or automatically under a previously installed TMK.
  - It is used to distribute data-encrypting keys, within a local (non-shared) network, to an ATM or POS terminal or similar.  

### Level 3 (data-encrypting key)  
- ZAK
- ZPK(Zone PIN Key)  
  - also known as a A PIN Protection Key (PPK).  
  - encrypted under ZMK and exchanged via online messages.
  - The ZPK is a symmetric data encrypting key. It is used to encrypt the data that is transmitted in a security zone.
  - The ZPK will be used to encrypt PIN data (pin block to be more precise) and send it in the transaction request.  
  - The ZPK was historically used to encrypt PINs for transfer between sites (e.g. between Acquirer and Issuer).
  - One party, then, generates a random ZPK. The party then encrypts this ZPK under the ZMK to safely transmit this to the other party.  
- TAK (Terminal Authentication Key)  
  - The TAK is a data-encrypting key which is used to generate and verify a Message Authentication Code (MAC) when data is transmitted, within a local network, between a terminal and the terminal data acquirer.
- TPK (Terminal PIN Key)  
  - The TPK is a data-encrypting key which is used to encrypt PINs for transmission, within a local network, between a terminal and the terminal data acquirer.

## Consideration
- What Key to be used? 
  Bank and Visa are not within security zone.  

## Host Command
- EI: Generate a Public/Private Key Pair  
  The private key that is returned from a Thales HSM keypair generation command is encrypted under LMK keypair 34-35. You will never see this in the clear i.e unencrypted form.
- EO: Import public key to HSM  
- EK: Load a RSA Private Key  
- GK: Export Key under RSA public key
- A0: generate DEK key
- L6: Import an RSA Private Key

- To specific LMK key using host commands  
  - Specify the LMK id in the host command  
  - Specific tpc/udp port to talk with the host following this schema:   
    1500 -> default LMK, 1501 -> LMK id 0, port 1502 -> LMK id 1 and so on.

## Console Command
- IK: Import Key, not really ''importing'' anything in the HSM storage area
- FK: Form Key, not really ''importing'' anything in the HSM storage area