## Install PostgreSQL client
dnf install postgresql  
psql -h \<host> -p \<port> -d \<database> -U \<username>

## Allow remote access
sudo nano /var/lib/pgsql/11/data/pg_hba.conf  
Add:  
host    all             all             0.0.0.0/0               scram-sha-256

Open OS firewall to allow port 5432

## Create user and database
```sql
CREATE USER gitlab WITH ENCRYPTED PASSWORD 'gitlab';
CREATE DATABASE "gitlab" WITH OWNER "gitlab" ENCODING 'UTF8';
GRANT ALL PRIVILEGES ON DATABASE gitlab TO gitlab;
```