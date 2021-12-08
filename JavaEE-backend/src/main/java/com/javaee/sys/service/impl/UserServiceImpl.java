package com.javaee.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.exceptions.ApiException;
import com.javaee.framework.enums.AppCode;
import com.javaee.framework.exception.APIException;
import com.javaee.framework.utils.BeanConvertUtils;
import com.javaee.sys.entity.User;
import com.javaee.sys.mapper.UserMapper;
import com.javaee.sys.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.javaee.sys.vo.user.*;
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
    public String userLogin(LoginVo loginVo){
        LambdaQueryWrapper<User> wrapper1=new LambdaQueryWrapper<>();
        wrapper1.eq(User::getEmail,loginVo.getLoginKey());
        User user1=userMapper.selectOne(wrapper1);
        LambdaQueryWrapper<User> wrapper2=new LambdaQueryWrapper<>();
        wrapper2.eq(User::getTelephone,loginVo.getLoginKey());
        User user2=userMapper.selectOne(wrapper2);
        if(user1!=null){
            if(user1.getPassword().equals(loginVo.getPassword())) return "登陆成功！";
            else throw new APIException(AppCode.PASSWORD_ERROR);
        }
        else if(user2!=null){
            if(user2.getPassword().equals(loginVo.getPassword())) return "登陆成功！";
            else throw new APIException(AppCode.PASSWORD_ERROR);
        }
        else throw new APIException(AppCode.USER_NOT_EXIST);
    }

    @Override
    public String userRegister(RegisterVo registerVo){
        LambdaQueryWrapper<User> wrapper1=new LambdaQueryWrapper<>();
        wrapper1.eq(User::getEmail,registerVo.getEmail());
        User user1=userMapper.selectOne(wrapper1);
        LambdaQueryWrapper<User> wrapper2=new LambdaQueryWrapper<>();
        wrapper2.eq(User::getTelephone,registerVo.getTelephone());
        User user2=userMapper.selectOne(wrapper2);
        if(user1!=null) throw new APIException(AppCode.EMAIL_HAS_EXIST);
        else if(user2!=null) throw new APIException(AppCode.TELEPHONE_HAS_EXIST);
        else
        {
            boolean result=this.save(BeanConvertUtils.convertTo(registerVo,User::new));
            if (result) return "注册成功！";
            else throw new APIException(AppCode.REGISTER_FAIL);
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
            LambdaQueryWrapper<User> wrapper=new LambdaQueryWrapper<>();
            wrapper.eq(User::getUserId,infoUpdateVo.getId());
            User user1=new User();
            user1.setEmail(infoUpdateVo.getEmail());
            user1.setTelephone(infoUpdateVo.getTelephone());
            boolean result= this.update(user1,wrapper);
            if(result) return "用户信息修改成功！";
            else throw new APIException(AppCode.USER_INFO_UPDATE_FAIL);
        }
        else throw new APIException(AppCode.USER_NOT_EXIST);
    }

    @Override
    public UserInfoVo infoView(Integer id){
        User user=this.getById(id);
        UserInfoVo userInfoVo=null;
        if(user!=null) userInfoVo=BeanConvertUtils.convertTo(user,UserInfoVo::new);
        else throw new APIException(AppCode.USER_INFO_GET_FAIL);
        return userInfoVo;
    }
}
