@echo off
REM Start Apache Zookeeper server:
start "ZooKeeper" /D ".\bin\windows\" "zookeeper-server-start.bat" "..\..\config\zookeeper.properties"
REM Wait 10 seconds:
timeout 10
REM Start Apache Kafka server:
start "Kafka" /D ".\bin\windows\" "kafka-server-start.bat" "..\..\config\server.properties"
