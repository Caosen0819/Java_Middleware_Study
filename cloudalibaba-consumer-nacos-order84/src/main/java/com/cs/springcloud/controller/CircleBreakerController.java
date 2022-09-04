package com.cs.springcloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.cs.springcloud.entities.CommonResult;
import com.cs.springcloud.entities.Payment;
import com.cs.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import javax.security.auth.login.Configuration;

/**
 * @Author Caosen
 * @Date 2022/9/3 13:17
 * @Version 1.0
 */
@RestController
@Slf4j
public class CircleBreakerController {

    public static final String SERVICE_URL = "http://nacos-payment-provider";

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping(value = "/consumer/fallback/{id}")
    @SentinelResource(value = "fallback", fallback = "handlerFallback", blockHandler = "blockHandler",
    exceptionsToIgnore = {IllegalArgumentException.class})
    //@SentinelResource(value = "fallback", fallback = "handlerFallback", blockHandler = "blockHandler")
    //@SentinelResource(value = "fallback", fallback = "handlerFallback")
    public CommonResult<Payment> fallback(@PathVariable("id") Long id){
        CommonResult<Payment> forObject = restTemplate.getForObject(SERVICE_URL + "/paymentSQL/" + id, CommonResult.class);

        System.out.println(forObject);
        if (id == 4) {
            throw new IllegalArgumentException("参数异常");
        }
        else if (forObject.getData() == null){
            throw new  NullPointerException("空指针异常");
        }
        return forObject;
    }
    //本例是fallback
    public CommonResult handlerFallback(@PathVariable("id")  Long id,Throwable e) {
        Payment payment = new Payment(id,"null");
        return new CommonResult<>(444,"兜底异常handlerFallback,exception内容  "+e.getMessage(),payment);
    }
    //本例是blockHandler
    public CommonResult blockHandler(@PathVariable("id")  Long id, BlockException blockException) {
        Payment payment = new Payment(id,"null");
        return new CommonResult<>(445,"blockHandler-sentinel限流,无此流水: blockException  "+blockException.getMessage(),payment);
    }

    //openfeign------------------------------

    @Autowired
    private PaymentService paymentService;

    @GetMapping(value = "/consumer/paymentSQL/{id}")
    @SentinelResource(value = "fallback", fallback = "handlerFallback", blockHandler = "blockHandler")
    //@SentinelResource(value = "fallback",  blockHandler = "blockHandler")
    public CommonResult<Payment> paymentSQL(@PathVariable("id") Long id) {
        //CommonResult<Payment> paymentCommonResult = paymentService.paymentSQL(id);
        if (id == 4) {
            throw new IllegalArgumentException("参数异常");
        }
        else if (id > 5){
            throw new  NullPointerException("空指针异常");
        }
        return paymentService.paymentSQL(id);

    }
}
//经过测试在客户端的错误依旧遵循 sentinel优先级大于java异常和openfeign，而java和openfeign则考虑代码运行顺序
