package com.beerus.service.impl;

import com.beerus.common.pojo.News;
import com.beerus.common.utils.Pager;
import com.beerus.mapper.NewsMapper;
import com.beerus.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author Beerus
 * @Description 新闻映业务层实现
 * @Date 2019-06-19
 **/
@Service("newsService")
public class NewsServiceImpl implements NewsService {
    @Autowired
    private NewsMapper newsMapper;

    @Override
    public List<News> listNewsByNine() {
        return newsMapper.listNewsByNine();
    }

    @Override
    public News getNewsById(Integer id) {
        return newsMapper.getNewsById(id);
    }

    @Override
    public Pager<News> listNewsByPager(Integer currPageNo, Integer pageSize) {
        Pager<News> pager = new Pager<>();
        pager.setCurrPageNo(currPageNo);
        pager.setTotalRows(newsMapper.count_rows());
        pager.setPageSize(pageSize);
        pager.setTotalPage((pager.getTotalRows() + pageSize - 1) / pageSize);
        pager.setDatas(newsMapper.listNewsByPager((currPageNo - 1) * pageSize, pageSize));
        return pager;
    }
}
