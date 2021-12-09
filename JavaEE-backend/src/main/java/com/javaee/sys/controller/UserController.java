package com.javaee.sys.controller;


import com.javaee.sys.service.UserService;
import com.javaee.sys.vo.user.*;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import static com.javaee.framework.utils.QiNiuUtils.upLoad;

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
    @ApiOperation(value = "login by email or telephone")
    public String Login(@Validated@RequestBody LoginVo loginVo)
    {
        return userService.userLogin(loginVo);
    }

    @PostMapping("/register")
    @ApiOperation(value = "user register")
    public String Register(@Validated@RequestBody RegisterVo registerVo)
    {
        return userService.userRegister(registerVo);
    }

    @PostMapping("/pswupdate")
    @ApiOperation(value = "update user password")
    public String pswUpdate(@Validated@RequestBody PasswordUpdateVo passwordUpdateVo)
    {
        return userService.passwordUpdate(passwordUpdateVo);
    }

    @PostMapping("/infoupdate")
    @ApiOperation(value = "update user information")
    public String infoUpdate(@Validated@RequestBody InfoUpdateVo infoUpdateVo)
    {
        return userService.infoUpdate(infoUpdateVo);
    }

    @PostMapping("/infoview")
    @ApiOperation(value = "view user information by id")
    public UserInfoVo infoView(Integer id)
    {
        return userService.infoView(id);
    }

    @PostMapping("/sendemail")
    @ApiOperation(value = "send verification code by email")
    public boolean sendEmail(String email)
    {
        return userService.sendEmail(email);
    }

    @PostMapping("/checkemail")
    public boolean checkEmail(String email,String code)
    {
        return userService.checkEmail(email,code);
    }

    @GetMapping("/filetest")
    public String uploadfile()
    {
        File file=new File("C:\\Users\\22123\\Videos\\Captures\\New Unity Project_test - FirstScene - PC, Mac & Linux Standalone - Unity 2021.1.16f1c1 Personal _DX11_ 2021-08-08 15-51-17.mp4");
        FileInputStream filestream= null;
        try {
            filestream = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return upLoad(filestream,"testvedio");
    }
}
