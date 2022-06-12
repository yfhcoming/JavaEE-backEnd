package com.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sys.entity.User;
import com.sys.vo.user.*;

import javax.servlet.http.HttpSession;


public interface UserService extends IService<User> {

    Integer userLogin(LoginVo loginVo, HttpSession httpSession);

    String userRegister(RegisterVo registerVo);

    String passwordUpdate(PasswordUpdateVo passwordUpdateVo);

    String infoUpdate(InfoUpdateVo infoUpdateVo);

    UserInfoVo infoView(Integer id);

    boolean sendEmail(String email);

    boolean checkEmail(String email,String code);

    String randomCode();

    boolean isUserIn(Integer userId);

    User getByEmail(String email);

    User getByTelephone(String telephone);

    boolean uploadPhoto(PhotoVo photoVo);
}
