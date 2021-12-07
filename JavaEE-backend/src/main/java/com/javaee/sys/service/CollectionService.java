package com.javaee.sys.service;

import com.javaee.sys.entity.Collection;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.bind.annotation.RequestParam;

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
}
