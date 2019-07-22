package com.beerus.service.impl;

import com.beerus.common.pojo.Comment;
import com.beerus.common.utils.Pager;
import com.beerus.mapper.CommentMapper;
import com.beerus.service.CommentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author Beerus
 * @Description 评论业务层
 * @Date 2019-06-24
 **/
@Service("commentService")
public class CommentServiceImpl implements CommentService {

    @Resource
    private CommentMapper commentMapper;

    @Override
    public Pager<Comment> listCommentByPager(Integer currPageNo, Integer pageSize, Integer newsId) {
        Pager<Comment> pager = new Pager<>();
        pager.setCurrPageNo(currPageNo);
        pager.setTotalRows(commentMapper.count_rows(newsId));
        pager.setPageSize(pageSize);
        pager.setTotalPage((pager.getTotalRows() + pageSize - 1) / pageSize);
        pager.setDatas(commentMapper.listCommentByPager((currPageNo - 1) * pageSize, pageSize, newsId));
        return pager;
    }

    @Override
    public boolean saveComment(Comment comment) {
        return commentMapper.saveComment(comment) > 0;
    }


}
