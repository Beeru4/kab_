package com.beerus.mapper;

import com.beerus.common.pojo.CourseNotice;
import com.beerus.common.pojo.Curriculum;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author Beerus
 * @Description 课程映射层
 * @Date 2019-06-17
 **/
@Mapper
public interface CurriculumMapper {
    /**
     * 查询课程公告
     *
     * @return
     */
    List<Curriculum> listCurriculumByNine();

    /**
     * 根据ID查询单条公告
     *
     * @param id
     * @return
     */
    Curriculum getCurriculumById(@Param(value = "id") Integer id);

    /**
     * 分页查询公告
     *
     * @return
     */
    List<CourseNotice> listCurriculumByPager(@Param(value = "currPageNo") Integer currPageNo,
                                               @Param(value = "pageSize") Integer pageSize);

    /**
     * 查询总行数
     *
     * @return
     */
    Integer count_rows();
}
