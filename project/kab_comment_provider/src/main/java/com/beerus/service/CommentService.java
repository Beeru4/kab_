package com.beerus.service;

import com.beerus.common.pojo.Comment;
import com.beerus.common.utils.Pager;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author Beerus
 * @Description 评论业务层
 * @Date 2019-06-24
 **/
@Transactional
public interface CommentService {
    /**
     * 根据ID分页查询评论
     *
     * @param currPageNo
     * @param pageSize
     * @param newsId
     * @return
     */
    @Transactional(readOnly = false)
    Pager<Comment> listCommentByPager(Integer currPageNo,
                                      Integer pageSize,
                                      Integer newsId);

    /**
     * 添加评论
     *
     * @param comment 要添加的评论
     * @return 1 > result 成功 result < 1失败
     */
    @Transactional(rollbackFor = {Exception.class})
    boolean saveComment(Comment comment);


}
