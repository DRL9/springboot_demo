package com.drl.demo.service

import com.alibaba.fastjson.JSONObject
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

import java.util.function.Consumer

@SpringBootTest
class KafkaMessageQueueImplTest extends Specification {

    @Autowired
    KafkaMessageQueueImpl kafkaMessageQueue

    def "sendMessage will success"() {
        given:
        def message = new JSONObject().fluentPut("data", "i am a data.")
        def subscriber = Mock(Consumer)
        kafkaMessageQueue.addSubscriber(subscriber)
        when:
        kafkaMessageQueue.sendMessage("topicTest1", message)
//        假设收到kafka的消息有1s延时
        sleep(1000)
        then:
        1 * subscriber.accept(message.toJSONString())
    }
}
