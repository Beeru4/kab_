package com.beerus.service;

import org.springframework.transaction.annotation.Transactional;

/**
 * @Author Beerus
 * @Description 评论业务层
 * @Date 2019-06-24
 **/
@Transactional
public interface CommentService {

    /**
     * 根据ID删除评论
     * @param id
     * @return
     */
    @Transactional(rollbackFor = {Exception.class})
    boolean delComment(Integer id);

}
