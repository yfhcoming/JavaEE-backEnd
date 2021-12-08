package com.javaee.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.javaee.sys.entity.Audio;
import com.javaee.sys.entity.Label;
import com.javaee.sys.mapper.LabelMapper;
import com.javaee.sys.service.LabelService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
