@echo off

start cmd /k "rmdir /q /s tmp_kafka & rmdir /q tmp_kafka"
timeout 2
start cmd /k "bin\windows\zookeeper-server-start.bat config\zookeeper.properties"
timeout 6
start cmd /k "bin\windows\kafka-server-start.bat config\server.properties"