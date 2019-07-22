package com.beerus.mapper;

import com.beerus.common.pojo.CourseNotice;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author Beerus
 * @Description 公告映射层
 * @Date 2019-06-17
 **/
@Mapper
public interface CourseNoticeMapper {
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
    CourseNotice getCourseNoticeById(@Param(value = "id") Integer id);

    /**
     * 分页查询公告
     *
     * @return
     */
    List<CourseNotice> listCourseNoticeByPager(@Param(value = "currPageNo") Integer currPageNo,
                                               @Param(value = "pageSize") Integer pageSize);

    /**
     * 查询总行数
     *
     * @return
     */
    Integer count_rows();
}
