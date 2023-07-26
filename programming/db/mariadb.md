## sql format
- Best pratice to use lower case for db objects.

## sql_mode
- set in my.cnf under mariadb home directory
- ORACLE  
  Stored procedure syntax will be using PL/SQL.
- EMPTY_STRING_IS_NULL  
  This mode causes the empty string to be treated the same as NULL for Oracle compatibility.

## case sensitive
- Whether DB objects are case-sensitive or not is partly determined by the underlying operating system.
- Whether value is case-sensitive depdends on collation of the field, if it's ci (case insensitive) or cs (case sensitive)  
  Select and index are all case insenstive when set to ci.


