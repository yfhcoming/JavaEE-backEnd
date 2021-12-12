package com.javaee.sys.mapper;

import com.javaee.sys.entity.CollectionHasAudio;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.javaee.sys.po.AudioPo;
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
public interface CollectionHasAudioMapper extends BaseMapper<CollectionHasAudio> {

    @Results(id = "findAllAudiosById",
            value = {
                    @Result(property = "audio_id", column = "audioId", id = true),
                    @Result(property = "audio_name", column = "audioName"),
                    @Result(property = "score", column = "score"),
                    @Result(property = "des", column = "des"),
                    @Result(property = "create_time", column = "createTime"),
                    @Result(property = "update_time", column = "updateTime"),
            })
    @Select("select audio_id,audio_name,score,des,create_time,update_time from collection_has_audio_view where collection_id=#{collectionId} order by audio_id")
    List<AudioPo> findAllAudiosById(@Param("collectionId")Integer collectionId);

}
