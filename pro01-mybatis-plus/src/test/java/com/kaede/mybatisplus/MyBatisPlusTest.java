package com.kaede.mybatisplus;

import com.kaede.mybatisplus.mapper.UserMapper;
import com.kaede.mybatisplus.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author kaede
 * @create 2022-10-02
 */

@SpringBootTest
public class MyBatisPlusTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    void testSelectList() {
        //通过条件构造器查询一个list集合，若无条件，则可以设置null为参数查询所有数据
        //SELECT id,name,age,email FROM user
        List<User> users = userMapper.selectList(null);
        users.forEach(x -> System.out.println(x));
    }

    @Test
    void testInsert() {
        //实现新增用户信息
        //INSERT INTO user ( id, name, age, email ) VALUES ( ?, ?, ?, ? )
        User user = new User();
        user.setName("张三");
        user.setAge(23);
        int insert = userMapper.insert(user);
        System.out.println("insert = " + insert);
        System.out.println("user = " + user);
    }

    @Test
    void testDelete() {
        //通过id删除用户信息
        //DELETE FROM user WHERE id=?
        int result = userMapper.deleteById(1593967669407371266L);
        System.out.println("result = " + result);
    }

    @Test
    void testDelete2() {
        //根据map集合中所设置的条件删除用户信息
        //DELETE FROM user WHERE name = ? AND age = ?
        Map<String, Object> map = new HashMap<>();
        map.put("name", "张三");
        map.put("age", "23");
        int result = userMapper.deleteByMap(map);
        System.out.println("result = " + result);
    }

    @Test
    void testDelete3() {
        //通过多个id实现批量删除
        //DELETE FROM user WHERE id IN ( ? , ? , ? )
        List<Long> list = Arrays.asList(1L, 2L, 3L);
        int result = userMapper.deleteBatchIds(list);
        System.out.println("result = " + result);
    }

    /*@Test
    void testUpdate() {
        //修改用户信息
        //UPDATE user SET name=?, email=? WHERE id=?
        User user = new User();
        user.setId(4L);
        user.setName("李四");
        user.setEmail("lisi@qq.com");
        int result = userMapper.updateById(user);
        System.out.println("result = " + result);
    }*/

    @Test
    void testSelect() {
        //根据id查询用户信息
        //SELECT id,name,age,email FROM user WHERE id=?
        User user = userMapper.selectById(2L);
        System.out.println("user = " + user);
    }

    @Test
    void testSelect2() {
        //根据id查询多个用户信息
        //SELECT id,name,age,email FROM user WHERE id IN ( ? , ? , ? , ? )
        List<Long> list = Arrays.asList(1L, 2L, 3L, 4L);
        List<User> users = userMapper.selectBatchIds(list);
        users.forEach(x -> System.out.println(x));
    }

    @Test
    void testSelect3() {
        //根据map集合中所设置的条件查询用户信息
        //SELECT id,name,age,email FROM user WHERE name = ? AND age = ?
        Map<String, Object> map = new HashMap<>();
        map.put("name", "李四");
        map.put("age", "21");
        List<User> users = userMapper.selectByMap(map);
        users.forEach(x -> System.out.println(x));
    }

    @Test
    void testSelect4() {
        Map<String, Object> map = userMapper.selectMapById(4L);
        System.out.println("map = " + map);
    }

}
