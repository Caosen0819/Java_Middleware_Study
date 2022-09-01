package com.cs.springcloud.controller;

import com.cs.springcloud.service.OrderNacosService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author Caosen
 * @Date 2022/9/1 13:12
 * @Version 1.0
 */
@RestController
@Slf4j
@DefaultProperties(defaultFallback = "payment_Global_FallbackMethod")
public class OrderNacosController {

    @Value("${server.port}")
    private String serverPort;

    @Value("${service-url.nacos-user-service}")
    private String serverURL;

    @Autowired
    private OrderNacosService orderNacosService;

    @GetMapping(value = "/consumer/payment/nacos/{id}")
    public String paymentInfo(@PathVariable("id") Integer id)
    {
        return orderNacosService.paymentInfo(id);
    }
    //全局默认
    public String payment_Global_FallbackMethod(){
        return "Global异常处理信息，请稍后再试，/(ㄒoㄒ)/~~";

    }
}
