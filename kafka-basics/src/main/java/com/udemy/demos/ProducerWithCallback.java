package com.udemy.demos;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

@Slf4j
public class ProducerWithCallback {


    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        KafkaProducer<String, String> producer = new KafkaProducer<>(properties);

        for (int i = 0; i < 10; i++) {
            ProducerRecord<String, String> producerRecord =
                    new ProducerRecord<>("demo_java", "hello word " + i);

            producer.send(producerRecord, (metadata, e) -> {
                if (e == null) {
                    log.info("Received new metadata " +
                            "\nTopic: " + metadata.topic() +
                            "\nPartition: " + metadata.partition() +
                            "\nOffset: " + metadata.offset() +
                            "\nTimestamp: " + metadata.timestamp());
                } else {
                    log.error("Error while producing", e);
                }
            });
        }

        producer.close();
    }
}
