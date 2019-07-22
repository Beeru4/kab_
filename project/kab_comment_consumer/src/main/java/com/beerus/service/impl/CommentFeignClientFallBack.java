package com.beerus.service.impl;

import com.beerus.common.base.BaseController;
import com.beerus.common.pojo.Comment;
import com.beerus.service.CommentFeignClient;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;

/**
 * @Author Beerus
 * @Description comment 回调类
 * @Date 2019-06-19
 **/
@Component
public class CommentFeignClientFallBack extends BaseController implements CommentFeignClient {

    @Override
    public ModelMap listCommentByPager(Integer currPageNo, Integer newsId) {
        return getErrorModelMap();
    }

    @Override
    public ModelMap addComment(Comment comment) {
        return getErrorModelMap();
    }
}
