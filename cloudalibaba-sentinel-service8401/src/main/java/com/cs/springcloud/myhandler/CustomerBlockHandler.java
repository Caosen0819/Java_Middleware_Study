package com.cs.springcloud.myhandler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.cs.springcloud.entities.CommonResult;

/**
 * @Author Caosen
 * @Date 2022/9/2 22:21
 * @Version 1.0
 */
public class CustomerBlockHandler {
    public static CommonResult handlerException(BlockException exception) {
        return new CommonResult(4444,"按客戶自定义,global handlerException----1");
    }

    public static CommonResult handlerException2(BlockException exception) {
        return new CommonResult(4444,"按客戶自定义,global handlerException----2");
    }
}
