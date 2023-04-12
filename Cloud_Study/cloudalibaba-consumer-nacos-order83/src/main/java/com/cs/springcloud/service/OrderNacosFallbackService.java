package com.cs.springcloud.service;

import org.springframework.stereotype.Component;

/**
 * @Author Caosen
 * @Date 2022/9/1 13:19
 * @Version 1.0
 */

@Component
public class OrderNacosFallbackService implements OrderNacosService {

    @Override
    public String paymentInfo(Integer id) {
        System.out.println("fallback");
        return null;
    }
}
