package com.javaee.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.javaee.framework.enums.AppCode;
import com.javaee.framework.exception.APIException;
import com.javaee.framework.utils.BeanConvertUtils;
import com.javaee.framework.utils.QiNiuUtils;
import com.javaee.sys.entity.Audio;
import com.javaee.sys.entity.Collection;
import com.javaee.sys.entity.CollectionHasAudio;
import com.javaee.sys.entity.User;
import com.javaee.sys.mapper.CollectionMapper;
import com.javaee.sys.po.AudioPo;
import com.javaee.sys.po.CollectionHeatPo;
import com.javaee.sys.po.CollectionPo;
import com.javaee.sys.service.CollectionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.javaee.sys.service.UserService;
import com.javaee.sys.vo.collection.CollectionAddVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
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

    public CollectionPo findById(Integer collectionId){
        if(!isCollectionIn(collectionId)){
            throw new APIException(AppCode.COLLECTION_NOT_EXIST, "收藏夹不存在：collectionId - " + collectionId);
        }
        CollectionPo collectionPo = collectionMapper.findById(collectionId);
        return collectionPo;
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
                    +", collectionName - " + dto.getCollectionName());
        }
        String cover;
        try {
            InputStream fileInputStream= dto.getMultipartFile().getInputStream();
            cover= QiNiuUtils.upLoad(fileInputStream, dto.getMultipartFile().getName());
        } catch (IOException e) {
            throw new APIException(AppCode.FILE_UPLOAD_FAIL);
        }
        Collection collection = BeanConvertUtils.convertTo(dto, Collection::new);
        collection.setCover(cover);
        save(collection);
        return true;
    }

    public List findAllCollections(){
        List<CollectionPo> allCollections = collectionMapper.findAllCollections();
        return allCollections;
    }

    public List findAllSortedByTime(){
        List<CollectionPo> allSortedByTime = collectionMapper.findAllSortedByTime();
        return allSortedByTime;
    }

    public List findAllOrderByHeat(){
        List<CollectionHeatPo> allOrderByHeat = collectionMapper.findAllOrderByHeat();
        return allOrderByHeat;
    }

    @Override
    public List findByUserId(Integer userId){
        if(userService.isUserIn(userId))
        {
            LambdaQueryWrapper<Collection> wrapper=new LambdaQueryWrapper<>();
            wrapper.eq(Collection::getUserId,userId);
            List<Collection> collectionList=collectionMapper.selectList(wrapper);
            if(collectionList==null) throw new APIException(AppCode.USER_HAS_NO_COLLECTION);
            else return collectionList;
        }
        else throw new APIException(AppCode.USER_NOT_EXIST);
    }


}
