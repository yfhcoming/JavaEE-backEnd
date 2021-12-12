package com.javaee.sys.service;

import com.javaee.sys.entity.Collection;
import com.baomidou.mybatisplus.extension.service.IService;
import com.javaee.sys.po.CollectionPo;
import com.javaee.sys.vo.collection.CollectionAddVo;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
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

    CollectionPo findById(Integer collectionId);

    List findByUserId(Integer userId);

    List findAllSortedByTime();

    List findAllOrderByHeat();
}
