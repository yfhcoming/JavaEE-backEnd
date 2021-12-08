package com.javaee.sys.service;

import com.javaee.sys.entity.AudioHasComment;
import com.baomidou.mybatisplus.extension.service.IService;
import com.javaee.sys.vo.audio.addCommentVo;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author T12
 * @since 2021-12-08
 */
public interface AudioHasCommentService extends IService<AudioHasComment> {

    List findAllCommentsById(Integer audioId);

    boolean addCommentByUser(addCommentVo dto);


}
