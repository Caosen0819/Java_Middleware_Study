package com.cs.springcloud.domain;

import lombok.Data;

/**
 * @Author Caosen
 * @Date 2022/9/3 19:07
 * @Version 1.0
 */

@Data
public class Storage {
    private Long id;

    /**
     * 产品id
     */
    private Long productId;

    /**
     * 总库存
     */
    private Integer total;

    /**
     * 已用库存
     */
    private Integer used;

    /**
     * 剩余库存
     */
    private Integer residue;

}
