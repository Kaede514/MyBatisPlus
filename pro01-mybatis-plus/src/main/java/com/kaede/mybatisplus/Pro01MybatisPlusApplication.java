package com.kaede.mybatisplus;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//扫描指定包下的mapper接口
@MapperScan("com.kaede.mybatisplus.mapper")
public class Pro01MybatisPlusApplication {

    public static void main(String[] args) {
        SpringApplication.run(Pro01MybatisPlusApplication.class, args);
    }

}
