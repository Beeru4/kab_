package com.beerus.controller;

import com.alibaba.fastjson.JSON;
import com.beerus.common.base.BaseController;
import com.beerus.common.utils.RedisConstant;
import com.beerus.common.utils.StatusCode;
import com.beerus.redis.RedisUtil;
import com.beerus.service.SurveyFeignClient;
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
 * @Description 控制层
 * @Date 2019-06-17
 **/
@Controller
@RefreshScope
public class SurveyController extends BaseController {

    @Autowired
    private SurveyFeignClient surveyFeignClient;
    @Resource
    private RedisUtil redisUtil;
    // 数据过期时间
    @Value("${redis.dataxpireTime}")
    private int dataxpireTime;

    /**
     * 查询概论
     *
     * @return
     */
    @RequestMapping("/survey")
    @ResponseBody
    public ModelMap getSurvey() {
        String key = RedisConstant.DATA_KEY + RedisConstant.SURVEY;
        String data = redisUtil.get(key, RedisConstant.DATA_DB);
        if (null == data) {
            // 没有数据 查询数据并保存到缓存
            ModelMap modelMap = surveyFeignClient.getSurvey();
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
     * 查看概论
     *
     * @return
     */
    @RequestMapping(value = "/viewSurvey")
    public String viewSurvey(Model model) {
        model.addAttribute("survey", getSurvey().get("data"));
        return "viewSurvey";
    }

}
