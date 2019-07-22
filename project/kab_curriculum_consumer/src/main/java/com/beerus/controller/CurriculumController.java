package com.beerus.controller;

import com.alibaba.fastjson.JSON;
import com.beerus.common.base.BaseController;
import com.beerus.common.pojo.Curriculum;
import com.beerus.common.pojo.News;
import com.beerus.common.utils.RedisConstant;
import com.beerus.common.utils.SerializableUtil;
import com.beerus.common.utils.StatusCode;
import com.beerus.redis.RedisUtil;
import com.beerus.service.CurriculumFeignClient;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


/**
 * @Author Beerus
 * @Description 课程控制层
 * @Date 2019-06-17
 **/
@Controller
public class CurriculumController extends BaseController {

    @Autowired
    private CurriculumFeignClient curriculumFeignClient;
    @Resource
    private RedisUtil redisUtil;
    // 数据过期时间
    @Value("${redis.dataxpireTime}")
    private int dataxpireTime;

    /**
     * 首页查询九条课程
     *
     * @return
     */
    @RequestMapping(value = "/listCurriculumByNine")
    @ResponseBody
    public ModelMap listCurriculumByNine() {
        String key = RedisConstant.DATA_KEY + RedisConstant.CURRICULUM;
        String data = redisUtil.get(key, RedisConstant.DATA_DB);
        if (null == data) {
            // 没有数据 查询数据并保存到缓存
            ModelMap modelMap = curriculumFeignClient.listCurriculumByNine();
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
     * 查询单条课程
     *
     * @return
     */
    @RequestMapping(value = "/viewCurriculum/{id}")
    public String viewConsuByNine(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("curr", new ObjectMapper().convertValue(curriculumFeignClient.viewCurriculum(id).get("data"), Curriculum.class));
        return "curriculumDetailed";
    }

    /**
     * 分页查询课程
     *
     * @return
     */
    @RequestMapping(value = "/curriculum")
    public String listConsultationByPager(@RequestParam(value = "currPageNo", required = false, defaultValue = "1") Integer currPageNo, Model model) {
        model.addAttribute("pager", SerializableUtil.parsePager( curriculumFeignClient.listCurriculumByPager(currPageNo),Curriculum.class));
        return "viewCurriculum";
    }
}
