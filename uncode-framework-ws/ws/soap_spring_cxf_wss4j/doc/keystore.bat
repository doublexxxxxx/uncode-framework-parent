@echo off

keytool -genkeypair -alias server -keyalg RSA -dname "cn=server" -keypass serverpass -keystore server_store.jks -storepass storepass
keytool -exportcert -alias server -file server_key.rsa -keystore server_store.jks -storepass storepass
keytool -importcert -alias server -file server_key.rsa -keystore client_store.jks -storepass storepass -noprompt
del server_key.rsa

keytool -genkeypair -alias client -dname "cn=client" -keyalg RSA -keypass clientpass -keystore client_store.jks -storepass storepass
keytool -exportcert -alias client -file client_key.rsa -keystore client_store.jks -storepass storepass
keytool -importcert -alias client -file client_key.rsa -keystore server_store.jks -storepass storepass -noprompt
del client_key.rsa