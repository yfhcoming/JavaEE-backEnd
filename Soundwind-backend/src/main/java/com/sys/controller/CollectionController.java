package com.sys.controller;


import com.sys.po.CollectionPo;
import com.sys.service.CollectionService;
import com.sys.vo.collection.CollectionAddVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;


@RestController
@RequestMapping("/v1/collections")
@Api(tags = "Collection")
public class CollectionController {

    @Autowired
    CollectionService collectionService;

    @PostMapping("/collection")
    @ApiOperation(value = "set a new collection")
    public boolean addCollection(@Validated CollectionAddVo vo){

        return collectionService.addCollection(vo);
    }

    @GetMapping("/{collectionId}")
    @ApiOperation(value = "find the collection by id")
    public CollectionPo findById(@Validated @NotNull @PathVariable("collectionId")Integer collectionId)
    {
        return collectionService.findById(collectionId);
    }

    @GetMapping()
    @ApiOperation(value = "find all collection")
    public List findAllCollections() {
        return collectionService.findAllCollections();
    }

    @GetMapping("/query/sort-by-time")
    @ApiOperation(value = "find all collection sorted in descending order")
    public List findAllSortedByTime() {
        return collectionService.findAllSortedByTime();
    }

    @GetMapping("/query/sort-by-heat")
    @ApiOperation(value = "find all collection sorted according to the heat")
    public List findAllOrderByHeat() {
        return collectionService.findAllOrderByHeat();
    }
}
