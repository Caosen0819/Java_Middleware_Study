package com.cs.kafka.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author Caosen
 * @Date 2022/9/23 12:07
 * @Version 1.0
 */

@Configuration
public class KafkaConfig {

    @Bean
    public NewTopic initTopic(){
        return new NewTopic("testtopic", 8, (short)2);
    }

    @Bean
    public NewTopic updateTopic(){
        return new NewTopic("testtopic", 10, (short)2);
    }
}
