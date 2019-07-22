package com.beerus.service.impl;

import com.beerus.common.base.BaseController;
import com.beerus.service.CourseNoticeFeignClient;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;

/**
 * @Author Beerus
 * @Description  回调类
 * @Date 2019-06-19
 **/
@Component
public class CourseNoticeFeignClientFallBack extends BaseController implements CourseNoticeFeignClient {

    @Override
    public ModelMap listCourseNoticeNine() {
        return getErrorModelMap();
    }

    @Override
    public ModelMap viewCourse(Integer id) {
        return getErrorModelMap();
    }

    @Override
    public ModelMap listCourseNoticeByPager(Integer currPageNo) {
        return getErrorModelMap();
    }
}
