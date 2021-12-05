package com.javaee.audio.service.impl;

import com.javaee.audio.entity.Audio;
import com.javaee.audio.mapper.AudioMapper;
import com.javaee.audio.service.AudioService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
public class AudioServiceImpl extends ServiceImpl<AudioMapper, Audio> implements AudioService {

}
