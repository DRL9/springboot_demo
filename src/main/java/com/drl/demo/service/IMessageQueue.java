package com.drl.demo.service;

import java.util.function.Consumer;

public interface IMessageQueue {

    void addSubscriber(Consumer<Object> subscriber);

    void sendMessage(String topic, Object data);
}
