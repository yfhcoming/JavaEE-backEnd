package com.javaee.sys.service;

import com.javaee.sys.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author T12
 * @since 2021-12-05
 */
public interface UserService extends IService<User> {
    User getUserById(String userid);

    User getUserByEmail(String email);

    User getUserByTelephone(String telephone);

    boolean pswUpdate(String id,String oldpassword,String newpassword);

    boolean infoUpdate(String id,String email,String telephone);
}
