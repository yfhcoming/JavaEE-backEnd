package com.javaee.sys.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.javaee.sys.entity.User;
import com.javaee.sys.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.sql.Wrapper;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author T12
 * @since 2021-12-05
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/login")
    public String Login(String emailortelephone,String password)
    {
        User user1=userService.getUserByEmail(emailortelephone);
        User user2=userService.getUserByTelephone(emailortelephone);
        if(user1!=null){
            if(user1.getPassword().equals(password)) return "登陆成功！";
            else return "密码错误！";
        }
        else if(user2!=null){
            if(user2.getPassword().equals(password)) return "登陆成功！";
            else return "密码错误！";
        }
        else return "用户不存在！";
    }

    @PostMapping("/register")
    public String Register(@RequestBody User user)
    {
        User user1=userService.getUserByEmail(user.getEmail());
        User user2=userService.getUserByTelephone(user.getTelephone());
        if(user1!=null)
        {
            return "该邮箱已被注册！";
        }
        else if(user2!=null)
        {
            return "该手机号已被注册！";
        }
        else
        {
            boolean result=userService.save(user);
            if (result) return "注册成功！";
            else return "注册失败！请重试！";
        }
    }

    @PostMapping("/pswupdate")
    public String pswUpdate(String id,String oldpassword,String newpassword)
    {
        User user=userService.getUserById(id);
        if(user!=null){
            if(user.getPassword().equals(oldpassword))
            {
                boolean result=userService.pswUpdate(id,oldpassword,newpassword);
                if(result) return "密码修改成功！";
                else return "密码修改失败！";
            }
            else return "密码错误！";
        }
        else return "用户不存在！";
    }

    @PostMapping("/infoupdate")
    public String infoUpdate(String id,String email,String telephone)
    {
        User user=userService.getUserById(id);
        if(user!=null){
            boolean result= userService.infoUpdate(id,email,telephone);
            if(result) return "用户信息修改成功！";
            else return "用户信息修改失败！";
        }
        else{
            return "用户不存在！";
        }
    }

    @PostMapping("/infoview")
    public User infoView(String id)
    {
        User user=userService.getUserById(id);
        if(user!=null) user.setPassword("密码已隐藏");
        return user;
    }
}
