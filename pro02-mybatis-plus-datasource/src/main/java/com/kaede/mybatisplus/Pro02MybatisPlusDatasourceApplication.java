package com.kaede.mybatisplus;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.kaede.mybatisplus.mapper")
@SpringBootApplication
public class Pro02MybatisPlusDatasourceApplication {

    public static void main(String[] args) {
        SpringApplication.run(Pro02MybatisPlusDatasourceApplication.class, args);
    }

}
