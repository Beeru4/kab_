package com.beerus.mapper;

import com.beerus.common.pojo.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author Beerus
 * @Description 评论映射层
 * @Date 2019-06-17
 **/
@Mapper
public interface CommentMapper {

    /**
     * 根据ID分页插叙数据
     *
     * @param currPageNo 当前页码
     * @param pageSize   页大小
     * @param newsId     新闻ID
     * @return 插叙后的数据
     */
    List<Comment> listCommentByPager(@Param(value = "currPageNo") Integer currPageNo,
                                     @Param(value = "pageSize") Integer pageSize,
                                     @Param(value = "newsId") Integer newsId);

    /**
     * 查询总行数
     *
     * @return
     */
    Integer count_rows(@Param(value = "newsId") Integer newsId);

    /**
     * 添加评论
     *
     * @param comment 要添加的评论
     * @return 1 > result 成功 result < 1失败
     */
    Integer saveComment(Comment comment);
}
