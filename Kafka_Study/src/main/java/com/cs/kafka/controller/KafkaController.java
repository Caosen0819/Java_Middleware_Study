package com.cs.kafka.controller;

import com.cs.kafka.conponent.KafkaSendResultHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;

/**
 * @Author Caosen
 * @Date 2022/9/23 12:12
 * @Version 1.0
 * 生产者
 */

@RestController
public class KafkaController {

    //工具，就类似雨estemplate
    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;


    @Autowired
    private KafkaSendResultHandler kafkaSendResultHandler;

    @GetMapping(value = "/kafka/normal/{message}")
    public void sendMessage(@PathVariable("message") String message) throws ExecutionException, InterruptedException {
        System.out.println("message接口发送消息：" + message);
        SendResult<String, Object> topic2 = kafkaTemplate.send("topic2", message).get();

    }


    @GetMapping(value = "/kafka/callbackOne/{message}")
    public void sendMessage2(@PathVariable("message") String callbackMessage) {

        kafkaTemplate.setProducerListener(kafkaSendResultHandler);
        kafkaTemplate.send("topic1", callbackMessage).addCallback(success ->{
            String topic = success.getRecordMetadata().topic();
            int partition = success.getRecordMetadata().partition();
            long offset = success.getRecordMetadata().offset();
            System.out.println("发送消息成功:" + topic + "-" + partition + "-" + offset);

        }, failure -> {
            System.out.println("发送消息失败:" + failure.getMessage());
        });
    }

    @GetMapping(value = "/kafka/callbackTwo/{message}")
    public void sendMessage3(@PathVariable("message") String callbackMessage) {
        kafkaTemplate.send("topic1", callbackMessage).addCallback(new ListenableFutureCallback<SendResult<String, Object>>() {
            @Override
            public void onFailure(Throwable ex) {
                System.out.println("发送消息失败："+ex.getMessage());

            }

            @Override
            public void onSuccess(SendResult<String, Object> result) {
                System.out.println("发送消息成功：" + result.getRecordMetadata().topic() + "-"
                        + result.getRecordMetadata().partition() + "-" + result.getRecordMetadata().offset());

            }
        });
    }
    @GetMapping("/kafka/transaction")
    public void sendMessage4(){
        // 声明事务：后面报错消息不会发出去
        kafkaTemplate.executeInTransaction(operations -> {
            operations.send("topic1","test executeInTransaction");
            throw new RuntimeException("fail");
        });
        // 不声明事务：后面报错但前面消息已经发送成功了
        kafkaTemplate.send("topic1","test executeInTransaction");
        throw new RuntimeException("fail");
    }


}
