package com.beerus.service;

import com.beerus.pojo.News;
import com.beerus.utils.Pager;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author Beerus
 * @Description 新闻业务层
 * @Date 2019-07-19
 **/
@Transactional
public interface NewsService {
    /**
     * 分页查新闻
     *
     * @return
     */
    @Transactional(readOnly = true)
    Pager<News> listNewsByPager(News news,
                                Integer currPageNo,
                                Integer pageSize);

    /**
     * 根据条件查询新闻
     *
     * @param news
     * @return
     */
    @Transactional(readOnly = true)
    News getNews(News news);

    /**
     * 保存新闻
     *
     * @param news
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    boolean saveNews(News news);

    /**
     * 删除新闻
     *
     * @param newsId
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    boolean delNews(Integer newsId);

    /**
     * 更改新闻
     * @param news
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    boolean modifyNews(News news);
}
