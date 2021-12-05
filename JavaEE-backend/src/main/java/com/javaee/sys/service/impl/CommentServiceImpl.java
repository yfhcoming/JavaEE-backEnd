package com.javaee.sys.service.impl;

import com.javaee.sys.entity.Comment;
import com.javaee.sys.mapper.CommentMapper;
import com.javaee.sys.service.CommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author T12
 * @since 2021-12-05
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {

}
