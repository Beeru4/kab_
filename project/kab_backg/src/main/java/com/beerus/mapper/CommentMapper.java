package com.beerus.mapper;

import org.apache.ibatis.annotations.Mapper;


/**
 * @Author Beerus
 * @Description 评论映射层
 * @Date 2019-06-17
 **/
@Mapper
public interface CommentMapper {

    /**
     * 根据ID删除评论
     * @param id
     * @return
     */
    Integer delComment(Integer id);
}
