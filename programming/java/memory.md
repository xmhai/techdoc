## OOM troubleshooting
- Possible causes
  - Memory leak
  - Peak memory spike

- Performance Testing
  - JMeter
  - SoapUI
  - VisualVM
  - JConsole

- Troubleshooting  
Script to show memory usage over time.
``` sh
pids=`jps | awk '{print $1}'`
for line in $pids; do
	jps -v | grep $line
	jcmd $line GC.run;
    jcmd $line GC.heap_info;
	echo "==============================================="
done
```