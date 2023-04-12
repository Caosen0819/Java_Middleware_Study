package com.cs.springcloud.service.impl;

import com.cs.springcloud.dao.PaymentDao;
import com.cs.springcloud.entities.Payment;
import com.cs.springcloud.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author Caosen
 * @Date 2022/8/25 17:52
 * @Version 1.0
 */
@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentDao paymentDao;


    public int create(Payment payment){
        return paymentDao.create(payment);
    }

    public Payment getPaymentById(Long id) {
        return paymentDao.getPaymentById(id);
    }
}
