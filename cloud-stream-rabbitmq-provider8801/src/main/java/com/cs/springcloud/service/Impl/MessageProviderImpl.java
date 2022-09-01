package com.cs.springcloud.service.Impl;

import com.cs.springcloud.service.IMessageProvider;
import com.sun.xml.internal.ws.api.config.management.ManagedEndpointFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.MessageChannel;

import java.util.UUID;

/**
 * @Author Caosen
 * @Date 2022/8/31 11:12
 * @Version 1.0
 */
@EnableBinding(Source.class)
public class MessageProviderImpl implements IMessageProvider {

    @Autowired
    private MessageChannel output;
    @Override
    public String send() {
        String string = UUID.randomUUID().toString();
        output.send(MessageBuilder.withPayload(string).build());
        System.out.println("******serial:" + string);

        return null;
    }
}
