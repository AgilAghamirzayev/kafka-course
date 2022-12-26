# Replace "kafka-console-consumer.sh" 
# by "kafka-console-consumer" or "kafka-console-consumer.bat" based on your system # (or bin/kafka-console-consumer.sh or bin\windows\kafka-console-consumer.bat if you didn't setup PATH / Environment variables)

# start one consumer
kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic first_topic --group first-application

# start one producer and start producing
kafka-console-producer.sh --bootstrap-server localhost:9092 --topic first_topic

# start another consumer part of the same group. See messages being spread
kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic first_topic --group first-application

# start another consumer part of a different group from beginning
kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic first_topic --group second-application --from-beginning

kafka-topics.sh --bootstrap-server localhost:9092 --describe --topic first_topic
kafka-console-producer.sh --bootstrap-server localhost:9092 --topic first_topic
kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic first_topic