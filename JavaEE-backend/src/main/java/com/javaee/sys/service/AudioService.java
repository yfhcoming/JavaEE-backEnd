package com.javaee.sys.service;

import com.javaee.sys.entity.Audio;
import com.baomidou.mybatisplus.extension.service.IService;
import io.swagger.models.auth.In;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
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
    List findAllAudios();

    boolean isAudioIn(Integer audioId);

    BigDecimal findScoreById(Integer audioId);

    List searchByName(String name);

    Audio getRandomAudio();
}
