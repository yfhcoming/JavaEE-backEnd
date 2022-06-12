package com.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sys.entity.Label;

import java.util.List;


public interface LabelService extends IService<Label> {
    List findAllLabels();

    boolean isLabelIn(Integer labelId);
}
