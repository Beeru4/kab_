package com.beerus.controller;

import com.beerus.common.base.BaseController;
import com.beerus.common.pojo.Comment;
import com.beerus.common.utils.StatusCode;
import com.beerus.common.utils.Util;
import com.beerus.service.CommentService;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @Author Beerus
 * @Description 评论控制器
 * @Date 2019-06-24
 **/
@RestController
public class CommentController extends BaseController {

    @Resource
    private CommentService commentService;

    /**
     * 根据ID分页查询评论
     *
     * @param currPageNo
     * @param newsId
     * @return
     */
    @RequestMapping(value = "/comment")
    public ModelMap listCommentByPager(@RequestParam(value = "currPageNo", required = false,defaultValue = "1") Integer currPageNo,
                                       @RequestParam(value = "newsId", required = true) Integer newsId) {
        if (Util.isEmpty(newsId)) {
            return getModelMap(StatusCode.CODE_EMPTY, null, null);
        } else {
            return getModelMap(StatusCode.CODE_SUCCESS, commentService.listCommentByPager(currPageNo, 5, newsId), "success");
        }
    }

    /**
     * 添加评论
     *
     * @param comment 评论
     * @return
     */
    @RequestMapping(value = "/addComment")
    public ModelMap addComment(@RequestBody Comment comment) {
        if (Util.isEmpty(comment) || Util.isEmpty(comment.getContent())  || Util.isEmpty(comment.getNewsId())) {
            return getModelMap(StatusCode.CODE_EMPTY, null, "param is Null");
        } else {
            if (commentService.saveComment(comment)) {
                //添加成功
                return getModelMap(StatusCode.CODE_SUCCESS, null, null);
            } else {
                //添加失败
                return getModelMap(StatusCode.CODE_ERROR, null, null);
            }
        }
    }


}
