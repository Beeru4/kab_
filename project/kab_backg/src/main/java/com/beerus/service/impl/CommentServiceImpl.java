package com.beerus.service.impl;

import com.beerus.mapper.CommentMapper;
import com.beerus.service.CommentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author Beerus
 * @Description 评论业务层
 * @Date 2019-06-24
 **/
@Service
public class CommentServiceImpl implements CommentService {

    @Resource
    private CommentMapper commentMapper;

    @Override
    public boolean delComment(Integer id) {
        return commentMapper.delComment(id) > 0;
    }
}
