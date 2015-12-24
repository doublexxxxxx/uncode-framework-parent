--create tablespace
CREATE TABLESPACE uncode_tabspaces  datafile 'uncode.dbf' size 512M autoextend on next 10M maxsize unlimited;
--create user
CREATE USER uncode IDENTIFIED BY uncode  DEFAULT TABLESPACE uncode_tabspaces TEMPORARY TABLESPACE temp;
--create role
CREATE ROLE uncode_role;
--grant privilege to role
GRANT CREATE SESSION, CREATE TABLE, CREATE VIEW, CREATE PROCEDURE,CREATE SYNONYM, ALTER ANY TABLE,  ALTER ANY PROCEDURE, DROP ANY TABLE, DROP ANY VIEW, DROP ANY PROCEDURE, DROP ANY SYNONYM TO uncode_role;
--grant role to user
GRANT uncode_role TO uncode;
--grant default role to user
GRANT connect,resource to uncode;
commit;
/* */