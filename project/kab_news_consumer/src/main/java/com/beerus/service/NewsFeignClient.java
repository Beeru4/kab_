package com.beerus.service;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


import com.beerus.service.impl.NewsFeignClientFallBack;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestParam;


/**
 * @Author Beerus
 * @Description Admin Feign调用
 * @Date 2019-06-19
 **/
@FeignClient(value = "kab-news-provider", fallback = NewsFeignClientFallBack.class)
public interface NewsFeignClient {

    /**
     * 首页查询九条新闻
     *
     * @return
     */
    @RequestMapping("/listNewsByNine")
    ModelMap listNewsByNine();

    /**
     * 首页单条新闻
     *
     * @return
     */
    @RequestMapping(value = "/viewNews/{id}")
    ModelMap viewNews(@PathVariable("id") Integer id);

    /**
     * 分页查询新闻
     *
     * @return
     */
    @RequestMapping(value = "/news")
    ModelMap listNewsPager(@RequestParam(value = "currPageNo", required = false, defaultValue = "1") Integer currPageNo);
}
