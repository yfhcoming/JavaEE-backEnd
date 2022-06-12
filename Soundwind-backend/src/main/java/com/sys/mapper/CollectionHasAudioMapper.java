package com.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sys.entity.CollectionHasAudio;
import com.sys.po.AudioPo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;


public interface CollectionHasAudioMapper extends BaseMapper<CollectionHasAudio> {

//    @Results(id = "findAllAudiosById",
//            value = {
//                    @Result(property = "audio_id", column = "audioId", id = true),
//                    @Result(property = "audio_name", column = "audioName"),
//                    @Result(property = "score", column = "score"),
//                    @Result(property = "des", column = "des"),
//                    @Result(property = "cover", column = "cover"),
//                    @Result(property = "create_time", column = "createTime"),
//                    @Result(property = "update_time", column = "updateTime"),
//            })
//    @Select("select audio_id,audio_name,score,des,cover,create_time,update_time from collection_has_audio_view where collection_id=#{collectionId} order by audio_id")
//    List<AudioPo> findAllAudiosById(@Param("collectionId")Integer collectionId);

        @Results(id = "findAllAudiosById",
         value = {
            @Result(property = "audio_id", column = "audioId", id = true),
            @Result(property = "audio_name", column = "audioName"),
            @Result(property = "score", column = "score"),
            @Result(property = "des", column = "des"),
            @Result(property = "cover", column = "cover"),
            @Result(property = "create_time", column = "createTime"),
            @Result(property = "update_time", column = "updateTime"),
         })
        @Select("select A.audio_id,A.audio_name,A.score,A.des,A.cover,A.create_time,A.update_time from collection_has_audio as H join collection as C on H.collection_id=C.collection_id join audio as A on H.audio_id=A.audio_id where C.collection_id=#{collectionId} order by A.audio_id")
        List<AudioPo> findAllAudiosById(@Param("collectionId")Integer collectionId);


}
