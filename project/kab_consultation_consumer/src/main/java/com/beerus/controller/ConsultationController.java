package com.beerus.controller;

import com.alibaba.fastjson.JSON;
import com.beerus.common.base.BaseController;
import com.beerus.common.pojo.Consultation;
import com.beerus.common.utils.RedisConstant;
import com.beerus.common.utils.StatusCode;
import com.beerus.redis.RedisUtil;
import com.beerus.service.ConsultationFeignClient;
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
 * @Description 资讯控制层
 * @Date 2019-06-17
 **/
@Controller
@RefreshScope
public class ConsultationController extends BaseController {

    @Autowired
    private ConsultationFeignClient consultationFeignClient;
    @Resource
    private RedisUtil redisUtil;
    // 数据过期时间
    @Value("${redis.dataxpireTime}")
    private int dataxpireTime;

    /**
     * 首页查询资讯
     *
     * @return
     */
    @RequestMapping(value = "/queryConsuByNine")
    @ResponseBody
    public ModelMap listCourseNoticeNine() {
        String key = RedisConstant.DATA_KEY + RedisConstant.CONSUlTATION;
        String data = redisUtil.get(key, RedisConstant.DATA_DB);
        if (null == data) {
            // 没有数据 查询数据并保存到缓存
            ModelMap modelMap = consultationFeignClient.listCourseNoticeNine();
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
     * 查看资讯
     *
     * @return
     */
    @RequestMapping(value = "/viewConsuByNine/{id}")
    public String viewCourse(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("cou", new ObjectMapper().convertValue(consultationFeignClient.viewCourse(id).get("data"), Consultation.class));
        return "courseDetailed";
    }

    /**
     * 分页查询资讯
     *
     * @return
     */
    @RequestMapping(value = "/consultation")
    public String listCourseNoticeByPager(@RequestParam(value = "currPageNo", required = false, defaultValue = "1") Integer currPageNo, Model model) {
        model.addAttribute("pager", consultationFeignClient.listCourseNoticeByPager(currPageNo).get("data"));
        return "viewCourse";
    }
}
