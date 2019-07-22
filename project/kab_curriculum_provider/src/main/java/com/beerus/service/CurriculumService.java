package com.beerus.service;

import com.beerus.common.pojo.CourseNotice;
import com.beerus.common.pojo.Curriculum;
import com.beerus.common.utils.Pager;
import org.apache.ibatis.annotations.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author Beerus
 * @Description 课程业务层
 * @Date 2019-06-19
 **/
@Transactional
public interface CurriculumService {
    /**
     * 查询课程公告
     *
     * @return
     */
    @Transactional(readOnly = true)
    List<Curriculum> listCurriculumByNine();

    /**
     * 根据ID查询单条公告
     *
     * @param id
     * @return
     */
    @Transactional(readOnly = true)
    Curriculum getCurriculumById(@Param(value = "id") Integer id);

    /**
     * 分页查询公告
     *
     * @return
     */
    @Transactional(readOnly = true)
    Pager<CourseNotice> listCurriculumByPager(Integer currPageNo,
                                              Integer pageSize);
}
