package com.cs.springcloud.service;

import org.checkerframework.framework.qual.ConditionalPostconditionAnnotation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @Author Caosen
 * @Date 2022/9/1 13:16
 * @Version 1.0
 */
@Component
@FeignClient(value = "nacos-payment-provider", fallback = OrderNacosFallbackService.class)
public interface OrderNacosService {

    @GetMapping(value = "/payment/nacos/{id}")
    public String paymentInfo(@PathVariable("id") Integer id);

}
