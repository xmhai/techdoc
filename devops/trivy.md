## Scan
- File System
  - trivy fs -s HIGH,CRITICAL -f template -t html.tpl -o trivy-report.html \<folder>
  - For **WAR file** scan, better approach is to unzip and scan, so that issues can be reported for each individual jar file.  
  - Trivy will look for vulnerabilities based on lock files such as Gemfile.lock and package-lock.json.  
  (I think the reason is Trivy, as a static code analyzer, will only look into meta data and based on the artifact information to check against the vulnerability database).
  - For **Java**, the lock files are under META-INF and its sub-folders, files like MANIFEST.MF, maven\**\pom.xml, pom.properties. 