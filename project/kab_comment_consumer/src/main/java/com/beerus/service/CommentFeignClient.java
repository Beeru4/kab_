package com.beerus.service;


import com.beerus.common.pojo.Comment;
import com.beerus.service.impl.CommentFeignClientFallBack;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


/**
 * @Author Beerus
 * @Description 评论 Feign调用
 * @Date 2019-06-19
 **/
@FeignClient(value = "kab-comment-provider", fallback = CommentFeignClientFallBack.class)
public interface CommentFeignClient {

  /**
     * 根据ID分页查询评论
     *
     * @param currPageNo
     * @param newsId
     * @return
     */
    @RequestMapping(value = "/comment")
    ModelMap listCommentByPager(@RequestParam(value = "currPageNo", required = false, defaultValue = "1") Integer currPageNo,
                                @RequestParam(value = "newsId") Integer newsId);

    /**
     * 添加评论
     *
     * @param comment 评论
     * @return
     */
    @RequestMapping(value = "/addComment")
    ModelMap addComment(@ModelAttribute Comment comment);
}
