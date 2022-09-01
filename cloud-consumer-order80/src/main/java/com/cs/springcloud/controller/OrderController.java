package com.cs.springcloud.controller;

import com.cs.springcloud.entities.CommonResult;
import com.cs.springcloud.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

/**
 * @Author Caosen
 * @Date 2022/8/25 22:26
 * @Version 1.0
 */
@RestController
@Slf4j
public class OrderController {

//    public static final String PAYMENT_URL = "http://localhost:8001";
    public static final String PAYMENT_URL = "http://CLOUD-PAYMENT-SERVICE";

    @Autowired
    private RestTemplate restTemplate;

    @PostMapping(value = "/consumer/payment/create")
    public CommonResult<Payment> create(Payment payment) {

        return restTemplate.postForObject(PAYMENT_URL + "/payment/create", payment, CommonResult.class);

    }

    @GetMapping(value = "/consumer/payment/getById/{id}")
    public CommonResult<Payment> getById(@PathVariable("id") Long id) {
        return restTemplate.getForObject(PAYMENT_URL + "/payment/getById/" + id, CommonResult.class);
    }

    @GetMapping(value = "/consumer/payment/getById2/{id}")
    public CommonResult<Payment> getById2(@PathVariable("id") Long id) {
        ResponseEntity<CommonResult> entity =  restTemplate.getForEntity(PAYMENT_URL + "/payment/getById/" + id, CommonResult.class);

        if (entity.getStatusCode().is2xxSuccessful()) {
            return new CommonResult(200, "entity陈工了 ", entity);
        }
        else {
            return new CommonResult(433, "entity返回失败", null);
        }
    }
}
