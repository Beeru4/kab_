package com.beerus.service;

import com.beerus.common.pojo.News;
import com.beerus.common.utils.Pager;

import java.util.List;

/**
 * @Author Beerus
 * @Description 新闻映射层
 * @Date 2019-06-19
 **/
public interface NewsService {
    /**
     * 查询新闻
     *
     * @return
     */
    List<News> listNewsByNine();

    /**
     * 查询单条新闻
     *
     * @param id
     * @return
     */
    News getNewsById(Integer id);

    /**
     * 分页查新闻
     *
     * @return
     */
    Pager<News> listNewsByPager(Integer currPageNo,
                                Integer pageSize);
}
