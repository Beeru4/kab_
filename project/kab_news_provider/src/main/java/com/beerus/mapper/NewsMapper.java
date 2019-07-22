package com.beerus.mapper;

import com.beerus.common.pojo.News;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author Beerus
 * @Description 新闻映射层
 * @Date 2019-06-17
 **/
@Mapper
public interface NewsMapper {

    /**
     * 查询新闻
     *
     * @return
     */
    List<News> listNewsByNine();

    /**
     * 查询单条新闻
     * @param id
     * @return
     */
    News getNewsById(@Param(value = "id") Integer id);

    /**
     * 分页查新闻
     * @return
     */
    List<News> listNewsByPager(@Param(value = "currPageNo") Integer currPageNo,
                               @Param(value = "pageSize") Integer pageSize);

    /**
     * 查询总行数
     * @return
     */
    Integer count_rows();
}
