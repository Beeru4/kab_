package com.beerus.mapper;

import com.beerus.common.pojo.Consultation;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author Beerus
 * @Description 资讯映射层
 * @Date 2019-06-17
 **/
@Mapper
public interface ConsultationMapper {

    /**
     * 首页查询资讯
     *
     * @return
     */
    List<Consultation> listConsultationByNine();

    /**
     * 首页单个资讯
     *
     * @return
     */
    Consultation getConsultation(@Param(value = "id") Integer id);

    /**
     * 分页查新闻
     *
     * @return
     */
    List<Consultation> listConsultationByPager(@Param(value = "currPageNo") Integer currPageNo,
                                               @Param(value = "pageSize") Integer pageSize);

    /**
     * 查询总行数
     *
     * @return
     */
    Integer count_rows();
}
