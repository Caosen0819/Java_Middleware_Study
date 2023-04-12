package com.cs.springcloud.controller;

import com.cs.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author Caosen
 * @Date 2022/8/28 21:23
 * @Version 1.0
 */
@RestController
@Slf4j
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverport;

    @GetMapping(value = "/payment/hystrix/ok/{id}")
    public String paymentOk(@PathVariable("id") Long id){

        String s = paymentService.paymentInfo_OK(id);
        log.info("****result :" + s);
        return s;
    }

    @GetMapping(value = "/payment/hystrix/timeout/{id}")
    public String paymenttimeout(@PathVariable("id") Long id){

        String s = paymentService.paymentInfo_TimeOut(id);
        log.info("****result :" + s);
        return s;
    }

    //====服务熔断
    @GetMapping("/payment/circuit/{id}")
    public String paymentCircuitBreaker(@PathVariable("id") Long id)
    {
        String result = paymentService.paymentCircuitBreaker(id);
        log.info("****result: "+result);
        return result;
    }
    //测试zipkin
    @GetMapping("/payment/zipkin")
    public String paymentZipkin() {
        return "hi ,i'am paymentzipkin server fall back，welcome to here, O(∩_∩)O哈哈~";
    }

}
