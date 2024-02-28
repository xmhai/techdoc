https://www.youtube.com/watch?v=bCyjiMjPO_U
https://www.tokenex.com/blog/credit-card-life-cycle-what-is-it-and-how-can-you-manage-it/

## Concept
Payment System
- MANIC (merchant, acquirer, network, issuer, card holder)
- Cardholder
- Credit Card  
  16 digits. First digit represents card network, 6 digit BIN, rest digit are unique and linked to card holder account.
- Terminal  
  POS/ATM/e-commerce
- Merchant
- Switch/Gateway
- Acquirer
- Payment Processor
- Payment Gateway
- Payment Network/Payment Scheme
- Issuer

ISO8583
- Data Element (DE) is stored in Binary-coded Decimal (BCD) format.  
  e.g. MTI 0100 is stored in two bytes.
- DE0: MTI (Message Type Indicator) 
- DE1: Bitmap 
- DExxx
  
Security
- CVC/CVV  
  To provide another level of verification when you make a purchase where the card isn't present, such as a telephone or online transaction
- PIN  
  pin is encrypted and decrypted along the way from terminal to issuer.
- ZMK
- ZPK
- AWK ()
- IWK

POS
- Authorization (MTI0100)
- Offline Transaction
- Batch Open & Close (End of the Day) & Reconciliation (MTI0500)
- Exception handling  
  https://www.youtube.com/watch?v=ZMBLLv4RXbw  
  1. POS sends MTI0500 Reconciliation and ACQ returns MTI0510 with DE39=95 (NOT MATCHED) and make the batch as FAILED.  
  2. POS sends MTI0320 Batch upload for each txn and ACQ returns MTI0330. ACQ will create a new batch for it and mark each txn to 'Y'.   
  3. POS sends MTI0500 Reconciliation and ACQ returns MTI0510 with DE39 (00 MATCHED)
- Settlement  
  After reconciliation, Merchant Management System (MMS) will generate Merchant Payment File, which contains Merchant Account and amount information, and submit to other systems (ESB/BUS/Clearing)  
