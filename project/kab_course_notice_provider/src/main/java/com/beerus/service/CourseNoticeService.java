package com.beerus.service;

import com.beerus.common.pojo.CourseNotice;
import com.beerus.common.utils.Pager;

import java.util.List;

/**
 * @Author Beerus
 * @Description
 * @Date 2019-06-19
 **/
public interface CourseNoticeService {
    /**
     * 查询课程公告
     *
     * @return
     */
    List<CourseNotice> listCourseNoticeByNine();

    /**
     * 根据ID查询单条公告
     *
     * @param id
     * @return
     */
    CourseNotice getCourseNoticeById(Integer id);

    /**
     * 分页查新闻
     *
     * @return
     */
    Pager<CourseNotice> listCourseNoticeByPager(Integer currPageNo,
                                        Integer pageSize);
}
