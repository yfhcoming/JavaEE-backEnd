package com.javaee.sys.service.impl;

import com.javaee.sys.entity.User;
import com.javaee.sys.mapper.UserMapper;
import com.javaee.sys.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author T12
 * @since 2021-12-05
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Autowired
    UserMapper userMapper;

    @Override
    public User getUserById(String userid){
        return userMapper.getUserById(userid);
    }

    @Override
    public User getUserByEmail(String email){
        return userMapper.getUserByEmail(email);
    }

    @Override
    public User getUserByTelephone(String telephone){
        return userMapper.getUserByTelephone(telephone);
    }

    @Override
    public boolean pswUpdate(String id,String oldpassword,String newpassword){
        return userMapper.pswUpdate(id,oldpassword,newpassword);
    }

    @Override
    public boolean infoUpdate(String id,String email,String telephone){
        return userMapper.infoUpdate(id,email,telephone);
    }
}
