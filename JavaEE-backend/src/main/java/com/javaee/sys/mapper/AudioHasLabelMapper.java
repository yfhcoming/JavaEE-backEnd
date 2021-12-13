package com.javaee.sys.mapper;

import com.javaee.sys.entity.AudioHasLabel;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.javaee.sys.entity.Label;
import com.javaee.sys.po.AudioPo;
import com.javaee.sys.po.LabelPo;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author T12
 * @since 2021-12-07
 */
public interface AudioHasLabelMapper extends BaseMapper<AudioHasLabel> {

    @Results(id = "findLabelsById",
            value = {
            @Result(property = "labelId", column = "label_id", id = true),
            @Result(property = "labelName", column = "label_name"),
            })
    @Select("select L.label_id,L.label_name from audio_has_label as H join audio as A on H.audio_id=A.audio_id join label as L on H.label_id=L.label_id where A.audio_id=#{audioId}")
    List<LabelPo> findAllLabelsById(@Param("audioId")Integer audioId);

    @Results(id = "findAudiosByLabelId",
            value = {
                    @Result(property = "audioId", column = "audio_id", id = true),
                    @Result(property = "audioName", column = "audio_name"),
                    @Result(property = "score", column = "score"),
                    @Result(property = "des", column = "des"),
                    @Result(property = "cover", column = "cover"),
                    @Result(property = "uploadUserName", column = "user_name"),
                    @Result(property = "createTime", column = "create_time"),
            })
    @Select("select A.audio_id,A.audio_name,A.score,A.des,A.cover,U.user_name,A.create_time from audio_has_label as H join audio as A on H.audio_id=A.audio_id join label as L on H.label_id=L.label_id left join user as U on A.user_id =U.user_id where L.label_id=#{LabelId}")
    List<AudioPo> findAllAudiosByLabelId(@Param("LabelId")Integer LabelId);

}
