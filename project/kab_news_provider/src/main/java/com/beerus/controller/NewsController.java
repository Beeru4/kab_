package com.beerus.controller;

import com.beerus.common.base.BaseController;
import com.beerus.common.utils.StatusCode;
import com.beerus.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author Beerus
 * @Description
 * @Date 2019-06-19
 **/
@RestController
public class NewsController extends BaseController {

    @Autowired
    private NewsService newsService;

    /**
     * 查询首页新闻
     *
     * @return
     */
    @RequestMapping("/listNewsByNine")
    public ModelMap listNewsByNine() {

        return getModelMap(StatusCode.CODE_SUCCESS, newsService.listNewsByNine(), null);
    }

    /**
     * 首页单条新闻
     *
     * @return
     */
    @RequestMapping(value = "/viewNews/{id}")
    public ModelMap viewNews(@PathVariable("id") Integer id) {
        return getModelMap(StatusCode.CODE_SUCCESS, newsService.getNewsById(id), null);
    }

    /**
     * 分页查询新闻
     *
     * @return
     */
    @RequestMapping(value = "/news")
    public ModelMap listNewsPager(@RequestParam(value = "currPageNo", required = false,defaultValue = "1") Integer currPageNo) {
        return getModelMap(StatusCode.CODE_SUCCESS, newsService.listNewsByPager(currPageNo, 10), "success");
    }
}
