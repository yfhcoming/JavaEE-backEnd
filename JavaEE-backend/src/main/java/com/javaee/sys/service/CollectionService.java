package com.javaee.sys.service;

import com.javaee.sys.entity.Collection;
import com.baomidou.mybatisplus.extension.service.IService;
import com.javaee.sys.vo.collection.CollectionAddVo;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author T12
 * @since 2021-12-05
 */
public interface CollectionService extends IService<Collection> {

    boolean isCollectionIn(Integer collectionId);

    boolean addCollection(CollectionAddVo dto);

    List findAllCollections();

    List findByUserId(Integer userId);
}
