package com.javaee.sys.mapper;

import com.javaee.sys.entity.AudioHasLabel;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.javaee.sys.entity.Label;
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
 * @since 2021-12-07
 */
public interface AudioHasLabelMapper extends BaseMapper<AudioHasLabel> {

    @Results(id = "findLabelsById",
            value = {
            @Result(property = "labelId", column = "label_id", id = true),
            @Result(property = "name", column = "name"),
            })
    @Select("select L.label_id,L.name from audio_has_label as H join audio as A on H.audio_id=A.audio_id join label as L on H.label_id=L.label_id where A.audio_id=#{audioId}")
    List<LabelPo> findAllLabelsById(@Param("audioId")Integer audioId);

    @Results(id = "findAudiosByLabelId",
            value = {
                    @Result(property = "audioId", column = "audio_id", id = true),
            })
    @Select("select A.audio_id from audio_has_label as H join audio as A on H.audio_id=A.audio_id join label as L on H.label_id=L.label_id where L.label_id=#{LabelId}")
    List<String> findAllAudiosByLabelId(@Param("LabelId")Integer LabelId);
}
