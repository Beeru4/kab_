package com.beerus.mapper;

import com.beerus.common.pojo.Survey;
import org.apache.ibatis.annotations.Mapper;


/**
 * @Author Beerus
 * @Description 概论映射层
 * @Date 2019-06-17
 **/
@Mapper
public interface SurveyMapper {
    /**
     * 查询概论
     *
     * @return
     */
    Survey getSurvey();
}
