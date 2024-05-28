- The socket call is, at the lowest level, handled by your operating system (OS), which may impose restrictions upon the allowed values of parameters such as timeouts. See for example: Overriding the default Linux kernel 20-second TCP socket connect timeout. So, regardless of what value you set in application code, the OS may be lowering it to the highest value it actually allows.  
https://stackoverflow.com/questions/56146261/i-set-the-timeout-on-the-socket-and-i-found-that-this-value-cannot-be-greater-t  

- (Default) RestTemplate -> (sun.net) HttpURLConnection -> HttpClient  
- (Option apache) Resttemplate -> HttpComponentsClientHttpRequestFactory -> (apache) CloseableHttpClient -> PoolingHttpClientConnectionManager/ConnectionKeepAliveStrategy  
- Both are using java.net.PlainSocketImpl for socket connection  
