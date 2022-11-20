package com.kaede.mybatisplus.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kaede.mybatisplus.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * @author kaede
 * @create 2022-10-02
 */

public interface UserMapper extends BaseMapper<User> {

    //根据id查询用户信息为map集合
    Map<String, Object> selectMapById(@Param("id") Long id);

    //通过年龄查询用户信息并分页
    //MyBatis-Plus所提供的分页对象必须位于第一个参数的位置
    Page<User> selectPageVo(@Param("page")Page<User> page, @Param("age") Integer age);

}
