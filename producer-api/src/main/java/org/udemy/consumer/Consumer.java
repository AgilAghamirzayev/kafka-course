package org.udemy.consumer;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.IntegerDeserializer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

public class Consumer {
    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, IntegerDeserializer.class.getName());
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        properties.put(ConsumerConfig.GROUP_ID_CONFIG, "group-3");



        try (KafkaConsumer<Integer, String> consumer = new KafkaConsumer<>(properties)) {
            consumer.subscribe(Arrays.asList("test-1"));
            while (true) {
                ConsumerRecords<Integer, String> records = consumer.poll(Duration.ofSeconds(2));
                for (ConsumerRecord<Integer, String> record : records) {
                    String message = record.value();
                    String topic = record.topic();
                    int partition = record.partition();
                    System.out.println("DATA: " + message + " TOPIC: " + topic + " PARTITION " + partition);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
}
