package com.cs.springcloud.lb;

import org.springframework.cloud.client.ServiceInstance;

import java.util.List;

/**
 * @Author Caosen
 * @Date 2022/8/27 22:52
 * @Version 1.0
 */
public class MyLB implements LoadBalanced {
    @Override
    public ServiceInstance instance(List<ServiceInstance> serviceInstances) {
        return null;
    }
}
