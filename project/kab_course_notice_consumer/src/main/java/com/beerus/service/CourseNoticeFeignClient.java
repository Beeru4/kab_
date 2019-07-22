package com.beerus.service;

import com.beerus.service.impl.CourseNoticeFeignClientFallBack;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestParam;


/**
 * @Author Beerus
 * @Description 公告 Feign调用
 * @Date 2019-06-19
 **/
@FeignClient(value = "kab-course-provider", fallback = CourseNoticeFeignClientFallBack.class)
public interface CourseNoticeFeignClient {

    /**
     * 首页查询课程公告
     *
     * @return
     */
    @RequestMapping(value = "/listCourseNoticeNine")
    ModelMap listCourseNoticeNine();

    /**
     * 查看单条公告
     *
     * @return
     */
    @RequestMapping(value = "/viewCourse/{id}")
    ModelMap viewCourse(@PathVariable("id") Integer id);

    /**
     * 分页查询公告
     *
     * @return
     */
    @RequestMapping(value = "/listCourseNoticeByPager")
    ModelMap listCourseNoticeByPager(@RequestParam(value = "currPageNo", required = false, defaultValue = "1") Integer currPageNo);
}
