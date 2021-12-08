package com.javaee.sys.mapper;

import com.javaee.sys.entity.AudioHasComment;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.javaee.sys.po.CommentPo;
import com.javaee.sys.po.LabelPo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author T12
 * @since 2021-12-08
 */
public interface AudioHasCommentMapper extends BaseMapper<AudioHasComment> {
    @Results(id = "findLabelsById",
            value = {
                    @Result(property = "commentId", column = "comment_id", id = true),
                    @Result(property = "userId", column = "user_id"),
                    @Result(property = "name", column = "name"),
                    @Result(property = "content", column = "content"),
                    @Result(property = "createTime", column = "create_time"),
            })
    @Select("select C.comment_id,C.user_id,U.name,C.content,C.create_time from audio_has_comment as H join audio as A on H.audio_id=A.audio_id join comment as C on H.comment_id=C.comment_id join user as U on C.user_id =U.user_id where A.audio_id=#{audioId}")
    List<CommentPo> findCommentsById(@Param("audioId")Integer audioId);
}
