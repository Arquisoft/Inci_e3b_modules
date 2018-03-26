#!/bin/bash
# Start Apache Zookeeper server:
nohup bash -c "bin/zookeeper-server-start.sh config/zookeeper.properties &"
# Wait 10 seconds:
sleep 10
# Start Apache Kafka server:
nohup bash -c "bin/kafka-server-start.sh config/server.properties &"
