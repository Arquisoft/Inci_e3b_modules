@echo off
start cmd /k bin\windows\zookeeper-server-start.bat config\zookeeper.properties
timeout 4
start cmd /k bin\windows\kafka-server-start.bat config\server.properties