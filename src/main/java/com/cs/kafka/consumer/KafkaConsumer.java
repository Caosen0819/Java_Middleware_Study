package com.cs.kafka.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.PartitionOffset;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.kafka.listener.ConsumerAwareListenerErrorHandler;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author Caosen
 * @Date 2022/9/23 12:33
 * @Version 1.0
 */

@Component
public class KafkaConsumer {




    @KafkaListener(topics = {"topic1"}, groupId = "felix-group0" ,errorHandler = "consumerAwareErrorHandler")
    public void onMessage1(ConsumerRecord<?,?> record){
        System.out.println("简单消费：" + record.topic() + "--" + record.partition() + "--" + record.value());

    }

    @KafkaListener(id = "comsumer1", groupId = "felix-group1", topicPartitions = {
            @TopicPartition(topic = "topic1", partitions = {"0"}),
            @TopicPartition(topic = "topic2", partitionOffsets = @PartitionOffset(partition = "0", initialOffset = "8"))
    } ,errorHandler = "consumerAwareErrorHandler")
    public void onMessage2(ConsumerRecord<?, ?> record){
        System.out.println("topic:"+record.topic()+"|partition:"+record.partition()+"|offset:"+record.offset()+"|value:"+record.value());
    }
    @KafkaListener(id = "consumer2",groupId = "felix-group2", topics = "topic1" ,errorHandler = "consumerAwareErrorHandler")
    public void onMessage3(List<ConsumerRecord<?, ?>> records) {
        System.out.println(">>>批量消费一次，records.size()="+records.size());
        for (ConsumerRecord<?, ?> record : records) {
            System.out.println(record.value());
        }
    }

    // 消息过滤监听
    @KafkaListener(topics = {"topic1"}, containerFactory = "filterContainerFactory")
    public void onMessage6(ConsumerRecord<?, ?> record) {
        System.out.println(record.value());
    }

}
