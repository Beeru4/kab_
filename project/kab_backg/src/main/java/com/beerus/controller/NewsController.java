package com.beerus.controller;

import com.alibaba.fastjson.JSON;
import com.beerus.pojo.News;
import com.beerus.pojo.UserAdmin;
import com.beerus.service.CommentService;
import com.beerus.service.NewsService;
import com.beerus.utils.RedisUtil;
import com.beerus.utils.Util;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * @Author Beerus
 * @Description 新闻控制层
 * @Date 2019-07-15
 **/
@Controller
public class NewsController {
    @Resource
    private NewsService newsService;
    @Resource
    private CommentService commentService;
    @Resource
    private RedisUtil redisUtil;
    // 查询的首页数据
    public final String DATA_KEY = "data:";
    // 查询的首页数据
    public final int DATA_DB = 2;
    // 新闻
    public final String NEWS = "News";
    /**
     * 页大小
     */
    private final int PAGESIZE = 10;

    /**
     * 分页查询新闻
     *
     * @param news       条件
     * @param currPageNo 当前页码
     * @return
     */
    @RequestMapping(value = "/newsManager")
    public String newsManager(@ModelAttribute News news,
                              @RequestParam(value = "currPageNo", required = false, defaultValue = "1") Integer currPageNo, Model model) {
        // 携带查询数据
        if (!Util.isEmpty(news.getTitle())) {
            model.addAttribute("title", news.getTitle());
        }
        model.addAttribute("pager", newsService.listNewsByPager(news, currPageNo, PAGESIZE));
        return "news";
    }

    /**
     * 根据id删除新闻
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/delNews/{id}")
    @ResponseBody
    public Object delNews(@PathVariable Integer id) {
        // 判断是否删除成功
        if (newsService.delNews(id)) {
            changeNewsCache();
            // 删除评论
            commentService.delComment(id);
            // 删除成功
            return "{\"code\":200}";
        }
        return "{\"code\":-1}";
    }

    /**
     * 根据id查询新闻
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/getNews/{id}")
    @ResponseBody
    public Object getNews(@PathVariable Integer id) {
        News toNews = new News();
        toNews.setId(id);
        // 得到查询后的新闻
        News news = newsService.getNews(toNews);
        // 判断是否有该新闻
        if (!Util.isEmpty(news)) {
            // 删除成功
            return JSON.toJSON(news);
        }
        return "{\"code\":-1}";
    }

    /**
     * 更改新闻
     *
     * @param news
     * @return
     */
    @RequestMapping(value = "/modifyNews")
    public String modifyNews(@ModelAttribute News news) {
        if (newsService.modifyNews(news)) {
            // 修改成功
            changeNewsCache();
        }
        return "redirect:/newsManager";
    }

    /**
     * 增加新闻
     *
     * @param news
     * @return
     */
    @RequestMapping(value = "/saveNews")
    public String saveNews(@ModelAttribute News news, HttpSession session) {
        // 设置创建人
        news.setCreateId(((UserAdmin) session.getAttribute("admin")).getId());
        if (newsService.saveNews(news)) {
            // 添加成功
            changeNewsCache();
        }
        return "redirect:/newsManager";
    }

    /**
     * 更新缓存
     */
    private void changeNewsCache() {
        redisUtil.del(DATA_DB, DATA_KEY + NEWS);
    }
}
