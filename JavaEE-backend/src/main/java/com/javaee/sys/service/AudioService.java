package com.javaee.sys.service;

import com.javaee.sys.entity.Audio;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author T12
 * @since 2021-12-05
 */
public interface AudioService extends IService<Audio> {
    List getAllAudios();
}
