package com.javaee.sys.controller;


import com.javaee.sys.service.UserService;
import com.javaee.sys.vo.user.*;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import static com.javaee.framework.utils.QiNiuUtils.downLoad;
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
    public Integer Login(@Validated@RequestBody LoginVo loginVo)
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

    @PostMapping("/photoupload")
    public boolean photoUpload(Integer userId, MultipartFile file)
    {
        return userService.uploadPhoto(userId,file);
    }
}
