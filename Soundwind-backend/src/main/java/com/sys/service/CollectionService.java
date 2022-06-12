package com.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sys.entity.Collection;
import com.sys.po.CollectionPo;
import com.sys.vo.collection.CollectionAddVo;

import java.util.List;


public interface CollectionService extends IService<Collection> {

    boolean isCollectionIn(Integer collectionId);

    boolean addCollection(CollectionAddVo dto);

    List findAllCollections();

    CollectionPo findById(Integer collectionId);

    List findByUserId(Integer userId);

    List findAllSortedByTime();

    List findAllOrderByHeat();
}
