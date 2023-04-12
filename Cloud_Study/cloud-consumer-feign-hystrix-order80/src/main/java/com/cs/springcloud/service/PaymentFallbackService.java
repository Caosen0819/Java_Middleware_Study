package com.cs.springcloud.service;

import org.springframework.stereotype.Component;

/**
 * @Author Caosen
 * @Date 2022/8/29 14:48
 * @Version 1.0
 */
@Component
public class PaymentFallbackService implements PaymentHystrixService{
    @Override
    public String paymentInfo_OK(Long id) {
        return "-----PaymentFallbackService fall back-paymentInfo_OK ,o(╥﹏╥)o";
    }

    @Override
    public String paymentInfo_TimeOut(Long id) {
        return "-----PaymentFallbackService fall back-paymentInfo_TimeOut ,o(╥﹏╥)o";
    }

    @Override
    public String paymentZipkin() {
        return "zipkin fallback";
    }
}
