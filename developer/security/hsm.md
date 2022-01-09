## Thales
https://stackoverflow.com/questions/26756077/thales-hsm-pin-related-operations  
https://github.com/Hairi81/HThalesAdaptor  
```java
    private String HSM_IP = "10.100.2.4";
    private String HSM_PORT = "9998";
    private Socket socket = null;    

    try{
        socket = new Socket(HSM_IP,HSM_PORT);
        socket.connect();
        System.out.println("Connection Success");
    }catch (IOException iex){
        System.out.println("Connection Failed : " + iex.getMessage());
    }
```