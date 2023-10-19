## How to set arguments or variables in Eclipse
https://www.ibm.com/docs/en/radfws/9.6.1?topic=launcher-arguments-tab  

- Arguments  
  Program Arguments, these are the values will be passed to main()

- Java options  
  https://docs.oracle.com/en/java/javase/20/docs/specs/man/java.html#overview-of-java-options
  - JVM arguments, e.g. -Xmx256M  
  - System properties (Java thing), e.g. -DpropertyName=propertyValue, System.getProperty(String key)

- Environment Variables (OS thing)
  https://stackoverflow.com/questions/7054972/java-system-properties-and-environment-variables  
  export HOME=/Users/myusername  
  SET WINDIR=C:\Windows  
  System.getenv(String name)

## Proxy using system properties
-Djava.net.useSystemProxies=true

## Troubleshooting
- Fix blurry issue on Windows 10 and fontsize 125%.  
  Right click on eclipse program -> "Compatibility" -> "High DPI"