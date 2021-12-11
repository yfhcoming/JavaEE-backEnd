package com.javaee.sys.mapper;

import com.javaee.sys.entity.Audio;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.javaee.sys.po.AudioPo;
import com.javaee.sys.po.CommentPo;
import com.javaee.sys.vo.audio.getOneVo;
import org.apache.ibatis.annotations.*;

import java.math.BigDecimal;
import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author T12
 * @since 2021-12-05
 */
public interface AudioMapper extends BaseMapper<Audio> {

    @Results(id = "findAudiosByLabelId",
            value = {
                    @Result(property = "audioId", column = "audio_id", id = true),
            })
    @Select("SELECT A.audio_id FROM audio_has_label as H join audio as A on H.audio_id=A.audio_id join label as L on H.label_id=L.label_id where L.label_id=#{LabelId}")
    List<String> findAllAudiosByLabelId(@Param("LabelId")Integer LabelId);

    @Results(id = "findAllScoresById",
            value = {
                    @Result(property = "score", column = "score", id = true),
            })
    @Select("SELECT S.score FROM user_score_audio as S join audio as A on S.audio_id=A.audio_id join user as U on S.user_id=U.user_id where A.audio_id=#{audioId}")
    List<BigDecimal> findAllScoresById(Integer audioId);


    @Select("SELECT * FROM audio AS a1 JOIN (SELECT ROUND(RAND()*(SELECT MAX(audio_id)FROM audio)) AS audio_id) AS a2 WHERE a1.audio_id>=a2.audio_id ORDER BY a1.audio_id LIMIT 1")
    Audio getRandomAudio();

    @Results(id = "findAllCommentsById",
            value = {
                    @Result(property = "commentId", column = "comment_id", id = true),
                    @Result(property = "userId", column = "user_id"),
                    @Result(property = "user_name", column = "userName"),
                    @Result(property = "content", column = "content"),
                    @Result(property = "createTime", column = "create_time"),
            })
    @Select("select C.comment_id,C.user_id,U.user_name,C.content,C.create_time from audio_has_comment as H join audio as A on H.audio_id=A.audio_id join comment as C on H.comment_id=C.comment_id join user as U on C.user_id =U.user_id where A.audio_id=#{audioId}")
    List<CommentPo> findAllCommentsById(@Param("audioId")Integer audioId);


    @Results(id = "findById",
            value = {
                    @Result(property = "audioId", column = "audio_id", id = true),
                    @Result(property = "audioName", column = "audio_name"),
                    @Result(property = "score", column = "score"),
                    @Result(property = "des", column = "des"),
                    @Result(property = "uploadUserName", column = "user_name"),
                    @Result(property = "createTime", column = "create_time"),
            })
    @Select("select A.audio_id,A.audio_name,A.score,A.des,U.user_name,A.create_time from audio as A left join user as U on A.user_id =U.user_id where A.audio_id=#{audioId}")
    AudioPo findById(@Param("audioId")Integer audioId);

    @ResultMap(value = "findById")
    @Select("select A.audio_id,A.audio_name,A.score,A.des,U.user_name,A.create_time from audio as A left join user as U on A.user_id =U.user_id ")
    List<AudioPo> findAllAudios();

}
