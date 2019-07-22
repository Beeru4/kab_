package com.beerus.controller;

import com.beerus.common.base.BaseController;
import com.beerus.common.utils.StatusCode;
import com.beerus.service.CourseNoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author Beerus
 * @Description 公告
 * @Date 2019-06-19
 **/
@RestController
public class CourseNoticeController extends BaseController {
    @Autowired
    private CourseNoticeService courseNoticeService;

    /**
     * 首页查询课程公告
     *
     * @return
     */
    @RequestMapping("/listCourseNoticeNine")
    public ModelMap listCourseNoticeNine() {
        return getModelMap(StatusCode.CODE_SUCCESS, courseNoticeService.listCourseNoticeByNine(), null);
    }

    /**
     * 查看单条公告
     *
     * @return
     */
    @RequestMapping(value = "/viewCourse/{id}")
    public ModelMap viewCourse(@PathVariable("id") Integer id) {
        return getModelMap(StatusCode.CODE_SUCCESS, courseNoticeService.getCourseNoticeById(id), null);
    }

    /**
     * 分页查询公告
     *
     * @return
     */
    @RequestMapping(value = "/listCourseNoticeByPager")
    public ModelMap listCourseNoticeByPager(@RequestParam(value = "currPageNo", required = false, defaultValue = "1") Integer currPageNo) {
        return getModelMap(StatusCode.CODE_SUCCESS, courseNoticeService.listCourseNoticeByPager(currPageNo, 10), null);
    }
}
