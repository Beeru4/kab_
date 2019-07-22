package com.beerus.controller;

import com.alibaba.fastjson.JSON;
import com.beerus.common.pojo.News;
import com.beerus.common.utils.RedisConstant;
import com.beerus.common.utils.SerializableUtil;
import com.beerus.common.utils.StatusCode;
import com.beerus.redis.RedisUtil;
import com.beerus.service.NewsFeignClient;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * @Author Beerus
 * @Description 新闻控制器
 * @Date 2019-06-20
 **/
@Controller
@RefreshScope
public class NewsController {

    @Autowired
    private NewsFeignClient newsFeignClient;
    @Resource
    private RedisUtil redisUtil;
    // 数据过期时间
    @Value("${redis.dataxpireTime}")
    private int dataxpireTime;

    /**
     * 查询首页新闻
     *
     * @return
     */
    @RequestMapping("/listNewsByNine")
    @ResponseBody
    public ModelMap listNewsByNine() {
        String key = RedisConstant.DATA_KEY + RedisConstant.NEWS;
        String data = redisUtil.get(key, RedisConstant.DATA_DB);
        if (null == data) {
            // 没有数据 查询数据并保存到缓存
            ModelMap modelMap = newsFeignClient.listNewsByNine();
            int code = (int) modelMap.get("code");
            if (StatusCode.CODE_SUCCESS == code) {
                redisUtil.set(key, JSON.toJSONString(modelMap), RedisConstant.DATA_DB);
                redisUtil.expire(key, dataxpireTime, RedisConstant.DATA_DB);
            }
            return modelMap;
        } else {
            // 直接读取缓存
            return JSON.parseObject(data, ModelMap.class);
        }
    }

    /**
     * 首页单条新闻
     *
     * @return
     */
    @RequestMapping(value = "/viewNews/{id}")
    public String viewNews(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("news", new ObjectMapper().convertValue(newsFeignClient.viewNews(id).get("data"), News.class));
        return "newsDetailed";
    }

    /**
     * 分页查询新闻
     *
     * @return
     */
    @RequestMapping(value = "/news")
    public String listNewsPager(@RequestParam(value = "currPageNo", required = false, defaultValue = "1") Integer currPageNo, Model model) {
        model.addAttribute("pager",SerializableUtil.parsePager(newsFeignClient.listNewsPager(currPageNo),News.class));
        return "viewNews";
    }
}
