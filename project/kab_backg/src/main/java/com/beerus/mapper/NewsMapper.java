package com.beerus.mapper;

import com.beerus.pojo.News;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author Beerus
 * @Description 新闻映射层
 * @Date 2019-07-15
 **/
@Mapper
public interface NewsMapper {
    /**
     * 分页查新闻
     *
     * @return
     */
    List<News> listNewsByPager(@Param(value = "news") News news,
                               @Param(value = "currPageNo") Integer currPageNo,
                               @Param(value = "pageSize") Integer pageSize);

    /**
     * 根据条件查询新闻
     *
     * @param news
     * @return
     */
    Integer count_Rows(@Param(value = "news") News news);

    /**
     * 根据条件查询新闻
     *
     * @param news
     * @return
     */
    News getNews(News news);

    /**
     * 保存新闻
     *
     * @param news
     * @return
     */
    Integer saveNews(News news);

    /**
     * 删除新闻
     *
     * @param newsId
     * @return
     */
    Integer delNews(Integer newsId);

    /**
     * 更改新闻
     * @param news
     * @return
     */
    Integer modifyNews(News news);

}
