package com.kaede.mybatisplus;

//import com.kaede.mybatisplus.enums.SexEnum;
import com.kaede.mybatisplus.enums.SexEnum;
import com.kaede.mybatisplus.mapper.UserMapper;
import com.kaede.mybatisplus.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author kaede
 * @create 2022-10-03
 */

@SpringBootTest
public class MyBatisPlusEnumTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    void test() {
        User user = new User();
        user.setName("admin");
        user.setAge(32);
        user.setSex(SexEnum.MALE);
        int result = userMapper.insert(user);
        System.out.println("result = " + result);
    }

    @Test
    void test1() {
        User user = userMapper.selectById(106L);
        System.out.println("user = " + user);
    }

}
