package com.javaee.sys.service.impl;

import com.javaee.sys.entity.Audio;
import com.javaee.sys.mapper.AudioMapper;
import com.javaee.sys.service.IAudioService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author javaee
 * @since 2021-12-04
 */
@Service
public class AudioServiceImpl extends ServiceImpl<AudioMapper, Audio> implements IAudioService {

}
