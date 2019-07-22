package com.beerus.service;

import com.beerus.common.pojo.Consultation;
import com.beerus.common.utils.Pager;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author Beerus
 * @Description 资讯业务层
 * @Date 2019-06-17
 **/
public interface ConsultationService {

    /**
     * 首页查询新闻
     * @return
     */
    List<Consultation> listConsultationByNine();

    /**
     * 查询单个资讯
     *
     * @return
     */
    Consultation viewConsultation(Integer id);

    /**
     * 分页查新闻
     *
     * @return
     */
    Pager<Consultation> listConsultationByPager(Integer currPageNo,
                                        Integer pageSize);
}
