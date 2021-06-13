## Configuration
After installation, check HOST in Listener.ora to make sure connection is allowed:
```txt
LISTENER =
  (DESCRIPTION_LIST =
    (DESCRIPTION =
        (ADDRESS = (PROTOCOL = TCP)(HOST = 192.168.56.101)(PORT = 1521))
        (CONNECT_DATA=(SID=orcl))
    )
  )

SID_LIST_LISTENER =
  (SID_LIST =
    (SID_DESC =
        (SID_NAME = orcl)
        (ORACLE_HOME = C:\app\Hai\product\11.2.0)
    )
  )
```