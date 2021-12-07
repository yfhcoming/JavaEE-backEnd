package com.javaee.sys.mapper;

import com.javaee.sys.entity.Audio;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.javaee.sys.vo.audio.getOneVo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author T12
 * @since 2021-12-05
 */
public interface AudioMapper extends BaseMapper<Audio> {

//    @Results(id = "check",
//            value = {
//            @Result()
//            })
//
//
//
//    getOneVo getOne(@Param("audioId") Integer audioId);
}
