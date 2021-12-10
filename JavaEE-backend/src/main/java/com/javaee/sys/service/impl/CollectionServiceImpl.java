package com.javaee.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.javaee.framework.enums.AppCode;
import com.javaee.framework.exception.APIException;
import com.javaee.framework.utils.BeanConvertUtils;
import com.javaee.sys.entity.Audio;
import com.javaee.sys.entity.Collection;
import com.javaee.sys.entity.CollectionHasAudio;
import com.javaee.sys.mapper.CollectionMapper;
import com.javaee.sys.service.CollectionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.javaee.sys.service.UserService;
import com.javaee.sys.vo.collection.CollectionAddVo;
import com.javaee.sys.vo.collection.CollectionHasAudioVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author T12
 * @since 2021-12-05
 */
@Service
public class CollectionServiceImpl extends ServiceImpl<CollectionMapper, Collection> implements CollectionService {

    @Autowired
    CollectionMapper collectionMapper;

    @Autowired
    UserService userService;

    public boolean isCollectionIn(Integer collectionId){
        LambdaQueryWrapper<Collection> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Collection::getCollectionId, collectionId);
        Integer integer = collectionMapper.selectCount(wrapper);
        boolean result = (integer == 0)?false:true;
        return result;
    }

    public boolean isUserHasCollectionIn(CollectionAddVo dto)
    {
        LambdaQueryWrapper<Collection> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Collection::getUserId, dto.getUserId())
                .eq(Collection::getCollectionName,dto.getCollectionName());
        Integer integer = collectionMapper.selectCount(wrapper);
        boolean result = (integer == 0)?false:true;
        return result;
    }



    public boolean addCollection(@Validated CollectionAddVo dto){
        if(!userService.isUserIn(dto.getUserId())){
            throw new APIException(AppCode.USER_NOT_EXIST, "用户不存在：userId - " + dto.getUserId());
        }
        if(this.isUserHasCollectionIn(dto)){
            throw new APIException(AppCode.USER_HAS_COLLECTION_HAS_IN, "用户已有该收藏夹：userId - " + dto.getUserId()
                    +", name - " + dto.getCollectionName());
        }

        return save(BeanConvertUtils.convertTo(dto, Collection::new));
    }

    public List findAllCollections(){
        LambdaQueryWrapper<Collection> wrapper = new LambdaQueryWrapper<>();
        List<Collection> collections = collectionMapper.selectList(null);
        return collections;
    }

}
