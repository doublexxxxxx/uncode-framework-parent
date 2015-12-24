echo off
cmd.exe /c mysql.exe -uroot -proot < create_db_mysql.sql
cmd.exe /c mysql.exe --default-character-set=utf8 -uwmsdb -pwmsdb123456 uncode4wms < install_db_mysql.sql