package com.javaee.sys.controller;


import com.javaee.sys.service.AudioService;
import com.javaee.sys.service.CollectionService;
import com.javaee.sys.service.UserService;
import com.javaee.sys.vo.user.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import javax.servlet.http.HttpSession;

import java.util.List;

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
@RequestMapping("v1/users")
@Api(tags = "User")
public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    CollectionService collectionService;

    @Autowired
    AudioService audioService;
    @PostMapping("/actions/login")
    @ApiOperation(value = "login by email or telephone")
    public Integer Login(@Validated@RequestBody LoginVo loginVo, HttpSession httpSession)
    {
        return userService.userLogin(loginVo,httpSession);
    }

    @PostMapping("/actions/register")
    @ApiOperation(value = "user register")
    public String Register(@Validated@RequestBody RegisterVo registerVo)
    {
        return userService.userRegister(registerVo);
    }

    @PatchMapping("/password")
    @ApiOperation(value = "update user password")
    public String pswUpdate(@Validated@RequestBody PasswordUpdateVo passwordUpdateVo)
    {
        return userService.passwordUpdate(passwordUpdateVo);
    }

    @PatchMapping("/information")
    @ApiOperation(value = "update user information")
    public String infoUpdate(@Validated@RequestBody InfoUpdateVo infoUpdateVo)
    {
        return userService.infoUpdate(infoUpdateVo);
    }

    @GetMapping("/information/{userid}")
    @ApiOperation(value = "view user information by id")
    public UserInfoVo infoView(@PathVariable("userid") Integer id)
    {
        return userService.infoView(id);
    }

    @PostMapping("actions/sendemail")
    @ApiOperation(value = "send verification code by email")
    public boolean sendEmail(String email)
    {
        return userService.sendEmail(email);
    }

    @PatchMapping("/photo")
    @ApiOperation(value = "upload photo by user ID")
    public boolean photoUpload(@Validated PhotoVo photoVo)
    {
        return userService.uploadPhoto(photoVo);
    }


    @GetMapping("/{userId}/collections")
    @ApiOperation(value = "find all collections by user ID")
    public List findCollectionsByUserId(@PathVariable("userId") Integer userId)
    {
        return collectionService.findByUserId(userId);
    }

    @GetMapping("/{userId}/audios")
    @ApiOperation(value = "get all audio by user id")
    public List findAudiosByUserId(@PathVariable("userId") Integer userId){
        return audioService.findByUserId(userId);
    }
}
