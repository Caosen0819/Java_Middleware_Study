package com.cs.springcloud.controller;


import com.cs.springcloud.entities.CommonResult;
import com.cs.springcloud.entities.Payment;
import com.cs.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

/**
 * @Author Caosen
 * @Date 2022/8/25 21:34
 * @Version 1.0
 */

@RestController
@Slf4j
@RequestMapping(value = "/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;
    @Value("${server.port}")
    private String serverport;
    @PostMapping(value = "/create")
    @ResponseBody
    public CommonResult create(@RequestBody Payment payment) {
        int value = paymentService.create(payment);
        log.info("受到影响的行数为： {}", value);
        if (value > 0) {
            return  new CommonResult(200, "success,serverport:" + serverport, value);
        }
        else {
            return new CommonResult(404, "fail", null);
        }
    }

    @GetMapping(value = "/lb")
    public String getPaymentLB() {
        return serverport;//返回服务接口
    }

    @GetMapping(value = "/getById/{id}")
    @ResponseBody
    public CommonResult getById(@PathVariable("id") Long id) {
        Payment payment = paymentService.getPaymentById(id);
        log.info("得到的结果为： {}", payment);
        if (payment != null) {
            return  new CommonResult(200, "success,serverport:" + serverport, payment);

        }
        else {
            return new CommonResult(444, "fail", null);
        }
    }




}
