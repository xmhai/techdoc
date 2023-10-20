## Syslog
https://www.gilesorr.com/blog/rsyslog-intro.html  

- It is protocol
- use UDP
- include two labels: facility and priority, other data are headers like host, timestamp and msg
- pcf syslog  
  https://www.casesup.com/category/knowledgebase/howtos/how-to-install-elasticsearch-logstash-kibana-and-syslog-to-manage-logs  
  - It will send each line as a separate message.
  - Syslog server (Linux built-in rsyslog) forward output to Logstash
- syslog client
  - Create rsyslogd modules to collect application logs.
  - Configure server address: /etc/rsyslog.conf
  - Configure client /etc/rsyslog.d/apache.conf
- syslog server 
  - Configure server (to forward to Logstash) /etc/rsyslog.d/logstash_apache.conf

