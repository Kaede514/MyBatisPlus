package com.kaede.mybatisplus;

import com.kaede.mybatisplus.pojo.User;
import com.kaede.mybatisplus.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

/**
 * @author kaede
 * @create 2022-10-02
 */

@SpringBootTest
public class MyBatisPlusServiceTest {

    @Autowired
    private UserService userService;

    @Test
    void testCount() {
        //查询总记录数
        //SELECT COUNT( * ) FROM user
        long count = userService.count();
        System.out.println("count = " + count);
    }

    @Test
    void testInsertMore() {
        //批量添加（单个sql语句进行循环添加）
        //INSERT INTO user ( id, name, age ) VALUES ( ?, ?, ? )
        List<User> list = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            User user = new User();
            user.setName("kk" + i);
            user.setAge(20+i);
            list.add(user);
        }
        boolean b = userService.saveBatch(list);
        System.out.println("b = " + b);
    }

    @Test
    void testInsert() {
        User user = new User();
        user.setId(100L);
        user.setName("k");
        user.setAge(10);
        boolean save = userService.save(user);
        System.out.println("save = " + save);
    }

}
