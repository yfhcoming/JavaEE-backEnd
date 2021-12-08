package com.javaee.sys.controller;


import com.javaee.framework.utils.BeanConvertUtils;
import com.javaee.sys.entity.Audio;
import com.javaee.sys.entity.Collection;
import com.javaee.sys.service.CollectionService;
import com.javaee.sys.vo.collection.CollectionAddVo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public boolean addCollection(CollectionAddVo vo){
        return collectionService.save(BeanConvertUtils.convertTo(vo, Collection::new));
    }

    @GetMapping("/find/{collectionId}")
    @ApiOperation(value = "find the collection by id")
    public Collection findById(@PathVariable("collectionId")Integer collectionId)
    {
        return collectionService.getById(collectionId);
    }




}
