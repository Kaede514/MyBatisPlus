package com.kaede.mybatisplus.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kaede.mybatisplus.mapper.UserMapper;
import com.kaede.mybatisplus.pojo.User;
import com.kaede.mybatisplus.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @author kaede
 * @create 2022-10-02
 */

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
