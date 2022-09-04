package com.cs.springcloud.dao;

import com.cs.springcloud.domain.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Author Caosen
 * @Date 2022/9/3 17:06
 * @Version 1.0
 */
@Mapper
public interface OrderDao {
    void create(Order order);

    void update(@Param("userId") Long userId, @Param("status") Integer status);
}
