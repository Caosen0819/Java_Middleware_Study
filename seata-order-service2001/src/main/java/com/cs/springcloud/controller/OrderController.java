package com.cs.springcloud.controller;

import com.cs.springcloud.domain.CommonResult;
import com.cs.springcloud.domain.Order;
import com.cs.springcloud.service.OrderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author Caosen
 * @Date 2022/9/3 17:19
 * @Version 1.0
 */
@RestController
public class OrderController
{
    @Resource
    private OrderService orderService;


    @GetMapping("/order/create")
    public CommonResult create(Order order)
    {
        orderService.create(order);
        return new CommonResult(200,"订单创建成功");
    }
}
