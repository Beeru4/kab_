package com.beerus.service.impl;

import com.beerus.common.pojo.Survey;
import com.beerus.mapper.SurveyMapper;
import com.beerus.service.SurveyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author Beerus
 * @Description 概论业务层
 * @Date 2019-06-19
 **/
@Service("surveyService")
public class SurveyServiceImpl implements SurveyService {
    @Autowired
    private SurveyMapper surveyMapper;

    @Override
    public Survey getSurvey() {
        return surveyMapper.getSurvey();
    }
}
