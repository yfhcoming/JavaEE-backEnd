package com.javaee.sys.mapper;

import com.javaee.sys.entity.Collection;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.javaee.sys.po.AudioPo;
import com.javaee.sys.po.CollectionHeatPo;
import com.javaee.sys.po.CollectionPo;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author T12
 * @since 2021-12-05
 */
public interface CollectionMapper extends BaseMapper<Collection> {



    @Results(id = "findById",
            value = {
                    @Result(property = "collectionId", column = "collection_id", id = true),
                    @Result(property = "collectionName", column = "collection_name"),
                    @Result(property = "createUserName", column = "user_name"),
                    @Result(property = "collectionCover", column = "cover"),
                    @Result(property = "createTime", column = "create_time"),
            })
    @Select("select C.collection_id,C.collection_name,U.user_name,C.create_time from collection as C left join user as U on C.user_id =U.user_id where C.collection_id=#{collectionId}")
    CollectionPo findById(@Param("collectionId") Integer collectionId);

    @ResultMap(value = "findById")
    @Select("select C.collection_id,C.collection_name,U.user_name,C.create_time from collection as C left join user as U on C.user_id =U.user_id")
    List<CollectionPo> findAllCollections();

    @ResultMap(value = "findById")
    @Select("select C.collection_id,C.collection_name,C.cover,U.user_name,C.create_time from collection as C left join user as U on C.user_id =U.user_id order by C.create_time desc")
    List<CollectionPo> findAllSortedByTime();

    @Results(id = "findAllOrderByHeat",
            value = {
                    @Result(property = "collectionId", column = "collection_id", id = true),
                    @Result(property = "collectionName", column = "collection_name"),
                    @Result(property = "createUserName", column = "user_name"),
                    @Result(property = "createTime", column = "create_time"),
                    @Result(property = "collectionCover", column = "cover"),
                    @Result(property = "commentNumber", column = "comment_number"),
            })
    @Select("select C.collection_id,C.collection_name,C.cover,H.user_name,C.create_time,H.comment_number from collection as C natural join collection_has_comment_view as H order by H.comment_number desc,C.collection_id asc")
    List<CollectionHeatPo> findAllOrderByHeat();
}
