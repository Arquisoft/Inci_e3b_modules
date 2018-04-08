@echo off
start cmd /k "kafka\bin\windows\zookeeper-server-start.bat kafka\config\zookeeper.properties"
timeout 4

start cmd /k "kafka\bin\windows\kafka-server-start.bat kafka\config\server.properties"
timeout  5

start cmd /k "title Agents_e3b & cd Agents_e3b & mvn spring-boot:run"
start cmd /k "title InciManager_e3b & cd InciManager_e3b & mvn spring-boot:run"
start cmd /k "title InciDashboard_e3b & cd InciDashboard_e3b & mvn spring-boot:run"