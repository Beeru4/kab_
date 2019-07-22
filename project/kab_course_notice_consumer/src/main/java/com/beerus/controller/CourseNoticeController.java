package com.beerus.controller;

import com.alibaba.fastjson.JSON;
import com.beerus.common.base.BaseController;
import com.beerus.common.pojo.CourseNotice;
import com.beerus.common.pojo.Curriculum;
import com.beerus.common.pojo.News;
import com.beerus.common.utils.RedisConstant;
import com.beerus.common.utils.SerializableUtil;
import com.beerus.common.utils.StatusCode;
import com.beerus.redis.RedisUtil;
import com.beerus.service.CourseNoticeFeignClient;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


/**
 * @Author Beerus
 * @Description 公告控制层
 * @Date 2019-06-17
 **/
@Controller
@RefreshScope
public class CourseNoticeController extends BaseController {

    @Autowired
    private CourseNoticeFeignClient courseNoticeFeignClient;
    @Resource
    private RedisUtil redisUtil;
    // 数据过期时间
    @Value("${redis.dataxpireTime}")
    private int dataxpireTime;

    /**
     * 首页查询课程公告
     *
     * @return
     */
    @RequestMapping(value = "/listCourseNoticeNine")
    @ResponseBody
    public ModelMap listCourseNoticeNine() {

        String key = RedisConstant.DATA_KEY + RedisConstant.COURSE;
        String data = redisUtil.get(key, RedisConstant.DATA_DB);
        if (null == data) {
            // 没有数据 查询数据并保存到缓存
            ModelMap modelMap = courseNoticeFeignClient.listCourseNoticeNine();
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
     * 查看单条公告
     *
     * @return
     */
    @RequestMapping(value = "/viewCourse/{id}")
    public String viewCourse(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("cou", new ObjectMapper().convertValue(courseNoticeFeignClient.viewCourse(id).get("data"),CourseNotice.class));
        return "courseDetailed";
    }

    /**
     * 分页查询公告
     *
     * @return
     */
    @RequestMapping(value = "/course")
    public String listCourseNoticeByPager(@RequestParam(value = "currPageNo", required = false, defaultValue = "1") Integer currPageNo, Model model) {
        model.addAttribute("pager", SerializableUtil.parsePager(courseNoticeFeignClient.listCourseNoticeByPager(currPageNo), CourseNotice.class));
        return "viewCourse";
    }
}
