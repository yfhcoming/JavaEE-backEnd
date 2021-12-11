package com.javaee.sys.controller;


import com.javaee.framework.utils.BeanConvertUtils;
import com.javaee.sys.entity.Audio;
import com.javaee.sys.entity.Collection;
import com.javaee.sys.po.CollectionPo;
import com.javaee.sys.service.CollectionService;
import com.javaee.sys.vo.collection.CollectionAddVo;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author T12
 * @since 2021-12-05
 */
@RestController
@RequestMapping("/collection")
public class CollectionController {

    @Autowired
    CollectionService collectionService;

    @PostMapping("/add")
    @ApiOperation(value = "set a new collection")
    public boolean addCollection(@Validated CollectionAddVo vo){

        return collectionService.addCollection(vo);
    }

    @GetMapping("/find/{collectionId}")
    @ApiOperation(value = "find the collection by id")
    public CollectionPo findById(@Validated @NotNull @PathVariable("collectionId")Integer collectionId)
    {
        return collectionService.findById(collectionId);
    }

    @GetMapping("/findAll")
    @ApiOperation(value = "find all collection")
    public List findAllCollections() {
        return collectionService.findAllCollections();
    }

    @GetMapping("/get/{userid}")
    @ApiOperation(value = "get collections by user ID")
    public List getByUserId(@PathVariable("userid") Integer userId)
    {
        return collectionService.findByUserId(userId);
    }
}
