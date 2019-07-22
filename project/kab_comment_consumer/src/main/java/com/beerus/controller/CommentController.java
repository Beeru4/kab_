package com.beerus.controller;

import com.beerus.common.pojo.Comment;
import com.beerus.common.pojo.MemberUser;
import com.beerus.service.CommentFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @Author Beerus
 * @Description
 * @Date 2019-06-20
 **/
@RestController
public class CommentController {

    @Autowired
    private CommentFeignClient commentFeignClient;

    /**
     * 根据ID分页查询评论
     *
     * @param currPageNo
     * @param newsId
     * @return
     */
    @RequestMapping(value = "/comment/{newsId}/{currPageNo}")
    public ModelMap listCommentByPager(@PathVariable Integer currPageNo,
                                       @PathVariable Integer newsId, HttpServletRequest request) {
        return commentFeignClient.listCommentByPager(currPageNo, newsId);
    }

    /**
     * 添加评论
     *
     * @param comment 评论
     * @return
     */
    @RequestMapping(value = "/addComment")
    public ModelMap addComment(@ModelAttribute Comment comment, HttpSession session) {
        if(session.getAttribute("member") == null){
            ModelMap modelMap = new ModelMap();
            modelMap.put("code",1002);
            return modelMap;
        }
        comment.setCommentUserId(((MemberUser)session.getAttribute("member")).getId());
        return commentFeignClient.addComment(comment);
    }
}
