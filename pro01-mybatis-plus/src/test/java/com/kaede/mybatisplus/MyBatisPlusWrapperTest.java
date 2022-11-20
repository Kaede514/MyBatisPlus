package com.kaede.mybatisplus;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.kaede.mybatisplus.mapper.UserMapper;
import com.kaede.mybatisplus.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

/**
 * @author kaede
 * @create 2022-10-03
 */

@SpringBootTest
public class MyBatisPlusWrapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    void test01() {
        //查询用户名包含k，年龄在20-30之间，邮箱信息不为null的用户信息
        //SELECT uid AS id,user_name AS name,age,email,is_deleted FROM t_user
        //WHERE is_deleted=0 AND (user_name LIKE ? AND age BETWEEN ? AND ? AND email IS NOT NULL)
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("user_name", "a")
            //between是 >= 和 <=
            .between("age", 20, 30)
            .isNotNull("email");
        List<User> users = userMapper.selectList(queryWrapper);
        users.forEach(System.out::println);
    }

    @Test
    void test02() {
        //查询用户信息按照年龄的降序排序，若年龄相同则按照id升序排序
        //SELECT uid AS id,user_name AS name,age,email,is_deleted FROM t_user
        //WHERE is_deleted=0 ORDER BY age DESC,uid ASC
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("age").orderByAsc("uid");
        List<User> users = userMapper.selectList(queryWrapper);
        users.forEach(System.out::println);
    }

    @Test
    void test03() {
        //删除邮箱地址不为null的用户信息
        //UPDATE t_user SET is_deleted=1 WHERE is_deleted=0 AND (email IS NOT NULL)
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.isNotNull("email");
        int result = userMapper.delete(queryWrapper);
        System.out.println("result = " + result);
    }

    @Test
    void test04() {
        //将年龄大于25并且用户名中包含k 或者 邮箱不为null 的用户信息修改
        //UPDATE t_user SET email=? WHERE is_deleted=0 AND (age > ? AND user_name LIKE ? OR email IS NOT NULL)
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.gt("age", 25)
            .like("user_name", "k")
            .or()
            .isNotNull("email");
        User user = new User();
        user.setEmail("xx@qq.com");
        int result = userMapper.update(user, queryWrapper);
        System.out.println("result = " + result);
    }

    @Test
    void test05() {
        //将用户名中包含有k 并且 年龄大于25或邮箱不为null 的用户信息修改
        //lambda中的条件优先执行
        //UPDATE t_user SET email=? WHERE is_deleted=0 AND (user_name LIKE ? AND (age > ? OR email IS NULL))
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("user_name","k")
            .and(i -> i.gt("age",25).or().isNotNull("email"));
        User user = new User();
        user.setEmail("test05@qq.com");
        int result = userMapper.update(user, queryWrapper);
        System.out.println("result = " + result);
    }

    @Test
    void test06() {
        //查询邮箱不为空的用户的用户名和年龄信息
        //SELECT user_name,age FROM t_user WHERE is_deleted=0 AND (email IS NOT NULL)
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("user_name","age").isNotNull("email");
        List<Map<String, Object>> maps = userMapper.selectMaps(queryWrapper);
        maps.forEach(System.out::println);
    }

    @Test
    void test07() {
        //查询id小于等于4的用户信息（使用子查询）
        //SELECT uid AS id,user_name AS name,age,email,is_deleted FROM t_user
        //WHERE is_deleted=0 AND (uid IN (select uid from t_user where uid <= 4))
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.inSql("uid", "select uid from t_user where uid <= 4");
        List<User> users = userMapper.selectList(queryWrapper);
        users.forEach(System.out::println);
    }

    @Test
    void test08() {
        //将 用户名中包含有k 并且 年龄大于20或邮箱为null 的用户信息修改
        //UPDATE t_user SET email=?,age=? WHERE is_deleted=0 AND (user_name LIKE ? AND (age > ? OR email IS NULL))
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        updateWrapper.like("user_name","k")
            .and(i -> i.gt("age",20).or().isNull("email"));
        updateWrapper.set("email","test09@qq.com");
        userMapper.update(null, updateWrapper);
    }

    @Test
    void test09() {
        //模拟开发中组装条件的情况
        //SELECT uid AS id,user_name AS name,age,email,is_deleted FROM t_user
        //WHERE is_deleted=0 AND (age >= ? AND age <= ?)
        String userName = "";
        Integer ageBegin = 20;
        Integer ageEnd = 24;
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        //mybatisplus中的StringUtils判断某个字符串是否不为 空字符串 或 null
        if(StringUtils.isNotBlank(userName)) {
            queryWrapper.like("user_name", userName);
        }
        if(ageBegin != null) {
            queryWrapper.ge("age", ageBegin);
        }
        if(ageEnd != null) {
            queryWrapper.le("age", ageEnd);
        }
        List<User> users = userMapper.selectList(queryWrapper);
        users.forEach(System.out::println);
    }

    @Test
    void test10() {
        //SELECT uid AS id,user_name AS name,age,email,is_deleted FROM t_user
        //WHERE is_deleted=0 AND (age >= ? AND age <= ?)
        String userName = "";
        Integer ageBegin = 20;
        Integer ageEnd = 24;
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(StringUtils.isNotBlank(userName),"user_name", userName)
            .ge(ageBegin != null,"age", ageBegin)
            .le(ageEnd != null,"age", ageEnd);
        List<User> users = userMapper.selectList(queryWrapper);
        users.forEach(System.out::println);
    }

    @Test
    void test11() {
        String userName = "";
        Integer ageBegin = 20;
        Integer ageEnd = 24;
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(StringUtils.isNotBlank(userName),User::getName, userName)
            .ge(ageBegin != null,User::getAge, ageBegin)
            .le(ageEnd != null,User::getAge, ageEnd);
        List<User> users = userMapper.selectList(queryWrapper);
        users.forEach(System.out::println);
    }

    @Test
    void test12() {
        //将 用户名中包含有k 并且 年龄大于26或邮箱为null 的用户信息修改
        //UPDATE t_user SET email=?,age=? WHERE is_deleted=0 AND (user_name LIKE ? AND (age > ? OR email IS NULL))
        LambdaUpdateWrapper<User> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.like(User::getName,"k")
            .and(i -> i.gt(User::getAge,26).or().isNull(User::getEmail));
        updateWrapper.set(User::getEmail,"test09@qq.com");
        userMapper.update(null, updateWrapper);
    }
}
