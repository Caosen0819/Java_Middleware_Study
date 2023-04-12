package com.cs.springcloud.service;

import org.springframework.web.bind.annotation.PathVariable;

/**
 * @Author Caosen
 * @Date 2022/8/28 21:18
 * @Version 1.0
 */
public interface PaymentService {
    public String paymentInfo_OK(Long id);
    public String paymentInfo_TimeOut(Long id);
    public String paymentCircuitBreaker(Long id);
}
