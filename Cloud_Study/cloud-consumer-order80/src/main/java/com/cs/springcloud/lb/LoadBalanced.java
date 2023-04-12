package com.cs.springcloud.lb;

import org.springframework.cloud.client.ServiceInstance;

import java.util.List;

/**
 * @Author Caosen
 * @Date 2022/8/27 22:50
 * @Version 1.0
 */
public interface LoadBalanced {

    ServiceInstance instance(List<ServiceInstance> serviceInstances);

}
