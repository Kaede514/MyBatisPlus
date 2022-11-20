package com.kaede.mybatisplus;

import com.kaede.mybatisplus.service.ProductService;
import com.kaede.mybatisplus.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Pro02MybatisPlusDatasourceApplicationTests {

    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;

    @Test
    void test() {
        System.out.println(userService.getById(1));
        System.out.println(productService.getById(1));
    }

}
