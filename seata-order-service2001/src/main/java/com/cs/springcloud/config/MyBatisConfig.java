package com.cs.springcloud.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @Author Caosen
 * @Date 2022/9/3 17:19
 * @Version 1.0
 */
@Configuration
@MapperScan({"com.cs.springcloud.dao"})
public class MyBatisConfig {
}