## Naming Convention
- Singular names for tables and columns
- Use lower case
- Use '_' to separate multiple words
- Use full spelled words

## Table Naming Convention
- Prefix table by module name

## Column Naming Convention
- Use uuid as primary key for distributed database
- Don't use generic name such as "code"
- Audit columns: created_at/created_time/creation_time, created_by, updated_at/updated_time/last_modified_time/last_modification_time, updated_by, is_deleted
- Common column names: is_* (for 0,1 value)