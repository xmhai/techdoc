- The socket call is, at the lowest level, handled by your operating system (OS), which may impose restrictions upon the allowed values of parameters such as timeouts. See for example: Overriding the default Linux kernel 20-second TCP socket connect timeout. So, regardless of what value you set in application code, the OS may be lowering it to the highest value it actually allows.  
https://stackoverflow.com/questions/56146261/i-set-the-timeout-on-the-socket-and-i-found-that-this-value-cannot-be-greater-t  

- SimpleClientHttpRequestFactory and HttpComponentsClientHttpRequestFactory are two different implementations of the ClientHttpRequestFactory interface in the Spring Framework. They are used to create ClientHttpRequest instances, which are used by RestTemplate for making HTTP requests.  
- the default is SimpleClientHttpRequestFactory unless HttpComponents HttpClient is present on the classpath.  
- (SimpleClientHttpRequestFactory) RestTemplate -> (sun.net) HttpURLConnection -> HttpClient  
- (HttpComponentsClientHttpRequestFactory) Resttemplate -> HttpComponentsClientHttpRequestFactory -> (apache) CloseableHttpClient -> PoolingHttpClientConnectionManager/ConnectionKeepAliveStrategy  
- Both are using java.net.PlainSocketImpl for socket connection  
