@echo off
start cmd /k "rmdir /q /s tmp_kafka & rmdir /q tmp_kafka & exit"
start cmd /k "title Base_de_datos & cd hsqldb\bin & runServer.bat"
timeout 1
start cmd /k "title Apache_Zookeeper & kafka\bin\windows\zookeeper-server-start.bat kafka\config\zookeeper.properties"
timeout 10

start cmd /k "title Apache_Kafka & kafka\bin\windows\kafka-server-start.bat kafka\config\server.properties"