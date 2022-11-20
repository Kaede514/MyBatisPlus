package com.kaede.mybatisplus.pojo;

import com.baomidou.mybatisplus.annotation.Version;
import lombok.Data;

/**
 * @author kaede
 * @create 2022-10-03
 */

@Data
public class Product {

    private Long id;
    private String name;
    private Integer price;
    //用来标识乐观锁版本号字段
    @Version
    private Integer version;

}
