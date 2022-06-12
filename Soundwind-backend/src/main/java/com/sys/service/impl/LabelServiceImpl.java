package com.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sys.entity.Label;
import com.sys.mapper.LabelMapper;
import com.sys.service.LabelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class LabelServiceImpl extends ServiceImpl<LabelMapper, Label> implements LabelService {

    @Autowired
    LabelMapper labelMapper;

    public List findAllLabels()
    {
        LambdaQueryWrapper<Label> wrapper = new LambdaQueryWrapper<>();
        List<Label> labelList = labelMapper.selectList(null);
        return labelList;
    };

    public boolean isLabelIn(Integer labelId){
        LambdaQueryWrapper<Label> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Label::getLabelId, labelId);
        Integer integer = labelMapper.selectCount(wrapper);
        boolean result = (integer == 0)?false:true;
        return result;
    }
}
