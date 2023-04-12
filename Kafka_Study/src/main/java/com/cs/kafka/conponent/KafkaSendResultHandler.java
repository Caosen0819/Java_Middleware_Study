package com.cs.kafka.conponent;


import com.cs.kafka.exception.exception;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.kafka.support.ProducerListener;
import org.springframework.stereotype.Component;

/**
 * @Author Caosen
 * @Date 2022/9/24 13:45
 * @Version 1.0
 */
@Component
public class KafkaSendResultHandler implements ProducerListener {


    @Override
    public void onSuccess(ProducerRecord producerRecord, RecordMetadata recordMetadata) {
        System.out.println("Message send success : " + producerRecord.toString());
    }

    @Override
    public void onError(ProducerRecord producerRecord, RecordMetadata recordMetadata, Exception exception) {
        System.out.println("Message send error : " + producerRecord.toString());
    }
}
