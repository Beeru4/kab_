package com.beerus.service.impl;


import com.beerus.mapper.NewsMapper;
import com.beerus.pojo.News;
import com.beerus.service.NewsService;
import com.beerus.utils.Pager;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author Beerus
 * @Description 新闻业务层实现类
 * @Date 2019-07-19
 **/
@Service
public class NewsServiceImpl implements NewsService {

    @Resource
    private NewsMapper newsMapper;


    @Override
    public Pager<News> listNewsByPager(News news, Integer currPageNo, Integer pageSize) {
        Pager<News> pager = new Pager<>();
        pager.setCurrPageNo(currPageNo);
        pager.setPageSize(pageSize);
        pager.setTotalRows(newsMapper.count_Rows(news));
        pager.setTotalPage((pager.getTotalRows() + pageSize - 1) / pageSize);
        pager.setDatas(newsMapper.listNewsByPager(news, (currPageNo - 1) * pageSize, pageSize));
        return pager;
    }

    @Override
    public News getNews(News news) {
        return newsMapper.getNews(news);
    }

    @Override
    public boolean saveNews(News news) {
        return newsMapper.saveNews(news) > 0;
    }

    @Override
    public boolean delNews(Integer newsId) {
        return newsMapper.delNews(newsId) > 0;
    }

    @Override
    public boolean modifyNews(News news) {
        return newsMapper.modifyNews(news) > 0;
    }
}
