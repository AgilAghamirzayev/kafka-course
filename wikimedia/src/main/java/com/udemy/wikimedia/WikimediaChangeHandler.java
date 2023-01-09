package com.udemy.wikimedia;

import com.launchdarkly.eventsource.MessageEvent;
import com.launchdarkly.eventsource.background.BackgroundEventHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

@Slf4j
@RequiredArgsConstructor
public class WikimediaChangeHandler implements BackgroundEventHandler {

    private final KafkaProducer<String, String> producer;
    private final String topic;

    @Override
    public void onOpen() throws Exception {
        // nothing there
    }

    @Override
    public void onClosed() throws Exception {
        producer.close();
    }

    @Override
    public void onMessage(String event, MessageEvent messageEvent) throws Exception {
        log.info(messageEvent.getData());

        //asynchronous
        producer.send(new ProducerRecord<>(topic, messageEvent.getData()));
    }

    @Override
    public void onComment(String comment) throws Exception {
        // nothing there
    }

    @Override
    public void onError(Throwable t) {
        log.error("Error is stream reading ", t);
    }
}
