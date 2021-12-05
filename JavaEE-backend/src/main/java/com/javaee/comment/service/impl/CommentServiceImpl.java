package com.javaee.comment.service.impl;

import com.javaee.comment.entity.Comment;
import com.javaee.comment.mapper.CommentMapper;
import com.javaee.comment.service.CommentService;
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
