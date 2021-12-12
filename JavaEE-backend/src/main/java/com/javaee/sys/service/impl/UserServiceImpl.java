package com.javaee.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.javaee.framework.enums.AppCode;
import com.javaee.framework.exception.APIException;
import com.javaee.framework.utils.BeanConvertUtils;
import com.javaee.framework.utils.QiNiuUtils;
import com.javaee.sys.entity.User;
import com.javaee.sys.mapper.UserMapper;
import com.javaee.sys.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.javaee.sys.vo.user.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

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

    @Autowired
    JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String from;

    private Map<String,String> codeMap=new HashMap<>();

    @Override
    public boolean isUserIn(Integer userId){
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getUserId, userId);
        Integer integer = userMapper.selectCount(wrapper);
        boolean result = (integer == 0)?false:true;
        return result;
    }

    @Override
    public Integer userLogin(LoginVo loginVo, HttpSession httpSession){
        User user1=getByEmail(loginVo.getLoginKey());
        User user2=getByTelephone(loginVo.getLoginKey());
        if(user1!=null){
            if(user1.getPassword().equals(loginVo.getPassword()))
            {
                httpSession.setAttribute("userId",user1.getUserId());
                return user1.getUserId();
            }
            else throw new APIException(AppCode.PASSWORD_ERROR);
        }
        else if(user2!=null){
            if(user2.getPassword().equals(loginVo.getPassword()))
            {
                httpSession.setAttribute("userId",user2.getUserId());
                return user2.getUserId();
            }
            else throw new APIException(AppCode.PASSWORD_ERROR);
        }
        else throw new APIException(AppCode.USER_NOT_EXIST);
    }

    @Override
    public String userRegister(RegisterVo registerVo){
        User user1=getByEmail(registerVo.getEmail());
        User user2=getByTelephone(registerVo.getTelephone());
        if(user1!=null) throw new APIException(AppCode.EMAIL_HAS_EXIST);
        else if(user2!=null) throw new APIException(AppCode.TELEPHONE_HAS_EXIST);
        else
        {
            String verificationCode=codeMap.get(registerVo.getEmail());
            if(verificationCode!=null)
            {
                if(verificationCode.equals(registerVo.getCode()))
                {
                    boolean result=this.save(BeanConvertUtils.convertTo(registerVo,User::new));
                    if (result)
                    {
                        codeMap.remove(registerVo.getEmail());
                        return "注册成功！";
                    }
                    else throw new APIException(AppCode.REGISTER_FAIL);
                }
                else throw new APIException(AppCode.VERIFICATION_CODE_ERROR);
            }
            else throw new APIException(AppCode.VERIFICATION_CODE_ERROR);
        }
    }

    @Override
    public String passwordUpdate(PasswordUpdateVo passwordUpdateVo){
        User user=this.getById(passwordUpdateVo.getId());
        if(user!=null){
            if(user.getPassword().equals(passwordUpdateVo.getOldPassword()))
            {
                LambdaQueryWrapper<User> wrapper=new LambdaQueryWrapper<>();
                wrapper.eq(User::getUserId,passwordUpdateVo.getId());
                User user1=new User();
                user1.setPassword(passwordUpdateVo.getNewPassword());
                boolean result=this.update(user1,wrapper);
                if(result) return "密码修改成功！";
                else throw new APIException(AppCode.PASSWORD_UPDATE_FAIL);
            }
            else throw new APIException(AppCode.PASSWORD_ERROR);
        }
        else throw new APIException(AppCode.USER_NOT_EXIST);
    }

    @Override
    public String infoUpdate(InfoUpdateVo infoUpdateVo){
        User user=this.getById(infoUpdateVo.getId());
        if(user!=null){
            if(getByTelephone(infoUpdateVo.getTelephone())==null)
            {
                LambdaQueryWrapper<User> wrapper=new LambdaQueryWrapper<>();
                wrapper.eq(User::getUserId,infoUpdateVo.getId());
                User user1=new User();
                user1.setUserName(infoUpdateVo.getUserName());
                user1.setTelephone(infoUpdateVo.getTelephone());
                boolean result= this.update(user1,wrapper);
                if(result) return "用户信息修改成功！";
                else throw new APIException(AppCode.USER_INFO_UPDATE_FAIL);
            }
            else throw new APIException(AppCode.TELEPHONE_HAS_EXIST);
        }
        else throw new APIException(AppCode.USER_NOT_EXIST);
    }

    @Override
    public UserInfoVo infoView(Integer id){
        User user=this.getById(id);
        UserInfoVo userInfoVo;
        if(user!=null) userInfoVo=BeanConvertUtils.convertTo(user,UserInfoVo::new);
        else throw new APIException(AppCode.USER_INFO_GET_FAIL);
        return userInfoVo;
    }

    @Override
    public boolean sendEmail(String email){

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(email);
        message.setSubject("JavaEE验证码");
        String code=randomCode();
        message.setText("尊敬的用户,您的验证码为:"+code+".请尽快验证");
        try
        {
            mailSender.send(message);
            codeMap.put(email,code);
            return true;
        }catch(MailException e){
            throw new APIException(AppCode.SEND_EMAIL_FAIL);
        }
    }

    @Override
    public boolean checkEmail(String email,String code){

        String checkedCode=codeMap.get(email);
        return checkedCode.equals(code);
    }

    @Override
    public String randomCode(){
        StringBuilder str = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 6; i++) {
            str.append(random.nextInt(10));
        }
        return str.toString();
    }

    @Override
    public User getByEmail(String email){
        LambdaQueryWrapper<User> wrapper1=new LambdaQueryWrapper<>();
        wrapper1.eq(User::getEmail,email);
        return userMapper.selectOne(wrapper1);
    }

    @Override
    public User getByTelephone(String telephone){
        LambdaQueryWrapper<User> wrapper1=new LambdaQueryWrapper<>();
        wrapper1.eq(User::getTelephone,telephone);
        return userMapper.selectOne(wrapper1);
    }

    @Override
    public boolean uploadPhoto(PhotoVo photoVo){
        if(isUserIn(photoVo.getUserId()))
        {
            String url;
            try {
                InputStream fileInputStream= photoVo.getFile().getInputStream();
                url= QiNiuUtils.upLoad(fileInputStream, photoVo.getFile().getName());
            } catch (IOException e) {
                throw new APIException(AppCode.FILE_UPLOAD_FAIL);
            }
            LambdaQueryWrapper<User> wrapper=new LambdaQueryWrapper<>();
            wrapper.eq(User::getUserId,photoVo.getUserId());
            User user1=new User();
            user1.setPhotoUrl(url);
            if(userMapper.update(user1,wrapper)>0) return true;
            else throw new APIException(AppCode.USER_PHOTO_UPDATE_FAIL);
        }
        else throw new APIException(AppCode.USER_NOT_EXIST);
    }
}
