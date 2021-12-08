package com.javaee.sys.service.impl;

import com.javaee.framework.enums.AppCode;
import com.javaee.framework.exception.APIException;
import com.javaee.framework.utils.BeanConvertUtils;
import com.javaee.sys.entity.AudioHasComment;
import com.javaee.sys.entity.Comment;
import com.javaee.sys.mapper.AudioHasCommentMapper;
import com.javaee.sys.po.AudioHasCommentPo;
import com.javaee.sys.po.CommentPo;
import com.javaee.sys.po.LabelPo;
import com.javaee.sys.service.AudioHasCommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.javaee.sys.service.AudioService;
import com.javaee.sys.service.CommentService;
import com.javaee.sys.service.UserService;
import com.javaee.sys.vo.audio.addCommentVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author T12
 * @since 2021-12-08
 */
@Service
public class AudioHasCommentServiceImpl extends ServiceImpl<AudioHasCommentMapper, AudioHasComment> implements AudioHasCommentService {

    @Autowired
    AudioHasCommentMapper audioHasCommentMapper;

    @Autowired
    AudioService audioService;

    @Autowired
    UserService userService;

    @Autowired
    CommentService commentService;

    public List findCommentsById(@RequestParam("audioId") @Valid @NotNull Integer audioId){
        List<CommentPo> comments = audioHasCommentMapper.findCommentsById(audioId);
        return comments;
    }

    public boolean addCommentByUser(@Validated addCommentVo dto){
        // TODO 先判断评论 用户
        // 先创建评论 (userId )
        // 在创建audio 拥有评论 表
        if(!audioService.isAudioIn(dto.getAudioId())){
            throw new APIException(AppCode.AUDIO_NOT_EXIST, "音频不存在：audioId - " + dto.getAudioId());
        }
        if(!userService.isUserIn(dto.getUserId())){
            throw new APIException(AppCode.USER_NOT_EXIST, "用户不存在：userId - " + dto.getUserId());
        }

        // 测试之后发现 BeanConvertUtils 的功能就是把名称相同的字段进行复制，没有名称相同的字段设置为null
        Comment comment = BeanConvertUtils.convertTo(dto, Comment::new);



        commentService.save(comment);

//        AudioHasComment audioHasComment = BeanConvertUtils.convertTo(new AudioHasCommentPo(dto.getAudioId(),comment.getCommentId()),
//                AudioHasComment::new);


        return true;
    }
}
