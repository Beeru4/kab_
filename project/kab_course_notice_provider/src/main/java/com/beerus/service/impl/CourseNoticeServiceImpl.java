package com.beerus.service.impl;

import com.beerus.common.pojo.CourseNotice;
import com.beerus.common.utils.Pager;
import com.beerus.mapper.CourseNoticeMapper;
import com.beerus.service.CourseNoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author Beerus
 * @Description
 * @Date 2019-06-19
 **/
@Service("courseNoticeService")
public class CourseNoticeServiceImpl implements CourseNoticeService {

    @Autowired
    private CourseNoticeMapper courseNoticeMapper;

    @Override
    public List<CourseNotice> listCourseNoticeByNine() {
        return courseNoticeMapper.listCourseNoticeByNine();
    }

    @Override
    public CourseNotice getCourseNoticeById(Integer id) {
        return courseNoticeMapper.getCourseNoticeById(id);
    }

    @Override
    public Pager<CourseNotice> listCourseNoticeByPager(Integer currPageNo, Integer pageSize) {
        Pager<CourseNotice> pager = new Pager<>();
        pager.setCurrPageNo(currPageNo);
        pager.setTotalRows(courseNoticeMapper.count_rows());
        pager.setPageSize(pageSize);
        pager.setTotalPage((pager.getTotalRows() + pageSize - 1) / pageSize);
        pager.setDatas(courseNoticeMapper.listCourseNoticeByPager((currPageNo - 1) * pageSize, pageSize));
        return pager;
    }


}
