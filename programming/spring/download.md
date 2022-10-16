## File Download
https://medium.com/swlh/streaming-data-with-spring-boot-restful-web-service-87522511c071  

- Without AsyncConfiguration, it will throw exception because current thread has been interrupted:
```txt
Caused by: java.io.IOException: The current thread was interrupted
	at org.apache.tomcat.util.net.NioChannel.checkInterruptStatus(NioChannel.java:236) ~[tomcat-embed-core-9.0.63.jar:9.0.63]
	at org.apache.tomcat.util.net.NioChannel.write(NioChannel.java:134) ~[tomcat-embed-core-9.0.63.jar:9.0.63]
```

## Write large amount of DB records
https://stackoverflow.com/questions/61442121/how-to-write-java-util-stream-stream-to-streamingresponsebody-output-stream  
https://jvmaware.com/streaming-json-response/  
https://stackoverflow.com/questions/51845228/proper-way-of-streaming-using-responseentity-and-making-sure-the-inputstream-get  

## Sample
```java
package com.example.download;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

@SpringBootApplication
@RestController
public class DownloadApplication {
	private final Logger logger = LoggerFactory.getLogger(DownloadApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(DownloadApplication.class, args);
	}

	@GetMapping("/")
	public void sayHello(HttpServletResponse response) throws Exception {
	    int MB = 1024*1024;

	    //Getting the runtime reference from system
	    Runtime runtime = Runtime.getRuntime();

	    //Print used memory
	    response.getWriter().write("Used Memory:" + (runtime.totalMemory() - runtime.freeMemory()) / MB + "\n");

	    //Print free memory
	    response.getWriter().write("Free Memory:" + runtime.freeMemory() / MB + "\n");

	    //Print total available memory
	    response.getWriter().write("Total Memory:" + runtime.totalMemory() / MB + "\n");

	    //Print Maximum available memory
	    response.getWriter().write("Max Memory:" + runtime.maxMemory() / MB + "\n");
	}
	
	@GetMapping("/download")
	public ResponseEntity<StreamingResponseBody> downloadFile(HttpServletResponse response) throws Exception {
	    logger.info("Request for download...");
	    
	    response.setContentType("application/octet-stream");
	    response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=\"download.file\"");

	    StreamingResponseBody stream = out -> {
	    	try (FileInputStream fis = new FileInputStream(new File("C:\\Users\\hai\\Downloads\\CentOS-7-x86_64-DVD-2009.iso"))) {
		        int bytesRead;
		        byte[] buffer = new byte[65536];
		        while ((bytesRead = fis.read(buffer)) != -1) {
		    	    logger.info("write buffer to outputstream");
		    	    response.getOutputStream().write(buffer, 0, bytesRead);
		    	    response.getOutputStream().flush();
		        }
		        fis.close();
		        response.getOutputStream().close();
            } catch (final IOException e) {
                logger.error("Exception while reading and streaming data {} ", e);
            }
	    };
	    
	    logger.info("Done!!!");
	    return new ResponseEntity(stream, HttpStatus.OK);
	}
}
```