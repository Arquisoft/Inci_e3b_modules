@echo off
start cmd /k "rmdir /q /s tmp_kafka & rmdir /q tmp_kafka & exit"
start cmd /k "title Base_de_datos & cd hsqldb\bin & runServer.bat"
timeout 1
start cmd /k "title Apache_Zookeeper & kafka\bin\windows\zookeeper-server-start.bat kafka\config\zookeeper.properties"
timeout 10

start cmd /k "title Apache_Kafka & kafka\bin\windows\kafka-server-start.bat kafka\config\server.properties"
timeout  5

start cmd /k "title Agents_e3b & cd Agents_e3b & mvn spring-boot:run"
start cmd /k "title InciManager_e3b & cd InciManager_e3b & mvn spring-boot:run"
start cmd /k "title InciDashboard_e3b & cd InciDashboard_e3b & mvn spring-boot:run"