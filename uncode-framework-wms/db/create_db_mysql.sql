-- login as root
-- sudo /usr/local/mysql/bin/mysql -u root -p mysql

-- create db
create database uncode4wms;

-- create user
create user wmsdb@'%' identified by 'wmsdb123456';
create user wmsdb@'localhost' identified by 'wmsdb123456';
 
-- grant privileges
grant all privileges on uncode4wms.* to wmsdb identified by 'wmsdb123456';

-- flush
flush privileges;

alter database uncode4wms character set utf8; 

-- test connect
-- sudo /usr/local/mysql/bin/mysql -u ems -p emsdb