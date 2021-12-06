package com.javaee.sys.mapper;

import com.javaee.sys.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author T12
 * @since 2021-12-05
 */
public interface UserMapper extends BaseMapper<User> {
    @Select("select * from user where user_id=#{userid}")
    User getUserById(@Param("userid") String userid);

    @Select("select * from user where email=#{email}")
    User getUserByEmail(@Param("email") String email);

    @Select("select * from user where telephone=#{telephone}")
    User getUserByTelephone(@Param("telephone") String telephone);

    @Insert("insert into user values(#{user.userId},#{user.email},#{user.password},#{user.telephone})")
    boolean addUser(@Param("user") User user);

    @Update("update user set password=#{newpassword} where user_id=#{userid} and password=#{oldpassword}")
    boolean pswUpdate(@Param("userid") String id,@Param("oldpassword") String oldpassword,@Param("newpassword") String newpassword);

    @Update("update user set email=#{email},telephone=#{telephone}where user_id=#{id}")
    boolean infoUpdate(@Param("id") String id,@Param("email") String email,@Param("telephone") String telephone);
}
