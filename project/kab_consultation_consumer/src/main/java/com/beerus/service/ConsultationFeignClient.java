package com.beerus.service;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


import com.beerus.service.impl.ConsultationFeignClientFallBack;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestParam;


/**
 * @Author Beerus
 * @Description 公告 Feign调用
 * @Date 2019-06-19
 **/
@FeignClient(value = "kab-consultation-provider", fallback = ConsultationFeignClientFallBack.class)
public interface ConsultationFeignClient {

    /**
     * 首页查询课程公告
     *
     * @return
     */
    @RequestMapping(value = "/queryConsuByNine")
    ModelMap listCourseNoticeNine();

    /**
     * 查看单条公告
     *
     * @return
     */
    @RequestMapping(value = "/viewConsuByNine/{id}")
    ModelMap viewCourse(@PathVariable("id") Integer id);

    /**
     * 分页查询公告
     *
     * @return
     */
    @RequestMapping(value = "/listConsultationByPager")
    ModelMap listCourseNoticeByPager(@RequestParam(value = "currPageNo", required = false, defaultValue = "1") Integer currPageNo);
}
