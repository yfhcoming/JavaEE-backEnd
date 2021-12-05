package com.javaee.user.service.impl;

import com.javaee.user.entity.User;
import com.javaee.user.mapper.UserMapper;
import com.javaee.user.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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

}
