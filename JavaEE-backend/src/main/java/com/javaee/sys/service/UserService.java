package com.javaee.sys.service;

import com.javaee.sys.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.javaee.sys.vo.user.*;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author T12
 * @since 2021-12-05
 */
public interface UserService extends IService<User> {

    String userLogin(LoginVo loginVo);

    String userRegister(RegisterVo registerVo);

    String passwordUpdate(PasswordUpdateVo passwordUpdateVo);

    String infoUpdate(InfoUpdateVo infoUpdateVo);

    UserInfoVo infoView(Integer id);

    boolean sendEmail(String email);

    boolean checkEmail(String email,String code);

    String randomCode();

    boolean isUserIn(Integer userId);
}
