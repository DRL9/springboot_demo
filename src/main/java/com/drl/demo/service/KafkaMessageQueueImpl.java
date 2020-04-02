package com.drl.demo.service;

import com.alibaba.fastjson.JSONObject;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

@Service
public class KafkaMessageQueueImpl implements IMessageQueue {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private KafkaTemplate<String, String> template;
    private List<Consumer<Object>> subscribers = new ArrayList<>();


    public KafkaMessageQueueImpl(@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection") KafkaTemplate<String, String> template) {
        this.template = template;
    }

    @KafkaListener(topics = "topicTest1")
    void listen(ConsumerRecord<?, ?> cr) {
        logger.info(cr.toString());
        for (Consumer<Object> subscriber : subscribers) {
            subscriber.accept(cr.value());
        }
    }

    @Override
    public void addSubscriber(Consumer<Object> subscriber) {
        subscribers.add(subscriber);
    }

    @Override
    public void sendMessage(String topic, Object data) {
        this.template.send(topic, JSONObject.toJSONString(data));
    }
}
