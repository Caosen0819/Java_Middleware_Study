package com.cs.springcloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.cs.springcloud.entities.CommonResult;
import com.cs.springcloud.entities.Payment;
import com.cs.springcloud.myhandler.CustomerBlockHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @Author Caosen
 * @Date 2022/9/2 14:13
 * @Version 1.0
 */

@RestController
@Slf4j
public class FlowLimitController {

    @GetMapping(value = "/testA")
    public String testA(){

        try {
            TimeUnit.MILLISECONDS.sleep(800);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        log.info("**** testa" + Thread.currentThread() + "*****AA");
        return  "testa";
    }
    @GetMapping(value = "/testB")
    public String testB(){
        log.info("**** testb" + Thread.currentThread() + "*****BB");
        return  "testb";
    }

    @GetMapping(value = "/testD")
    public String testD(){
        log.info("------testD 异常比例");
        int age=10/0;
        log.info("**** testd" + Thread.currentThread() + "*****dd");
        return  "testD";
    }

    @GetMapping(value = "/testHotKey")
    @SentinelResource(value = "testHotKey", blockHandler = "fallback_TestHotKey")
    public String testHotKey(@RequestParam(value = "p1",required = false) Integer p1,
                             @RequestParam(value = "p2", required = false) Integer p2){
        return "yes";
    }
    public String fallback_TestHotKey(BlockException exception){

        return exception.getMessage();
    }

    public CommonResult fallback(BlockException exception){
        System.out.println("asdf");
        return new CommonResult(234, "asdfasdf", exception.getMessage());
    }

    @GetMapping(value = "/byResource")
    @SentinelResource(value = "byResource", blockHandler = "fallback")
    public CommonResult byResource(){
        return new CommonResult(333, "asdfasdf", new Payment(2020L, "sf"));
    }

    @GetMapping("/rateLimit/byUrl")
    @SentinelResource(value = "byUrl")
    public CommonResult byUrl()
    {
        return new CommonResult(200,"按url限流测试OK",new Payment(2020L,"serial002"));
    }


    @GetMapping("/rateLimit/customerBlockHandler")
    @SentinelResource(value = "customerBlockHandler",
            blockHandlerClass = CustomerBlockHandler.class,
            blockHandler = "handlerException2")//<-----------
    public CommonResult customerBlockHandler()
    {
        return new CommonResult(200,"按客戶自定义",new Payment(2020L,"serial003"));
    }


}
