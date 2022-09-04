package com.cs.springcloud.service;

import com.cs.springcloud.entities.CommonResult;
import com.cs.springcloud.entities.Payment;
import org.springframework.stereotype.Component;

/**
 * @Author Caosen
 * @Date 2022/9/3 13:49
 * @Version 1.0
 */

@Component
public class PaymentFallbackService implements PaymentService{
    @Override
    public CommonResult<Payment> paymentSQL(Long id) {
        return new CommonResult<>(44444,"服务降级返回,---PaymentFallbackService",new Payment(id,"errorSerial"));

    }
}
