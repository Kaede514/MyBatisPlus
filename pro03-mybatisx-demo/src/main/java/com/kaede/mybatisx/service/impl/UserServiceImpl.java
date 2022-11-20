package com.kaede.mybatisx.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kaede.mybatisx.pojo.User;
import com.kaede.mybatisx.service.UserService;
import com.kaede.mybatisx.mapper.UserMapper;
import org.springframework.stereotype.Service;

/**
* @author hufeng
* @description 针对表【t_user】的数据库操作Service实现
* @createDate 2022-11-20 17:40:34
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

}




