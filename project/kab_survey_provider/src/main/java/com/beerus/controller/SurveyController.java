package com.beerus.controller;

import com.beerus.common.base.BaseController;
import com.beerus.common.utils.StatusCode;
import com.beerus.service.SurveyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author Beerus
 * @Description 概论控制器
 * @Date 2019-06-19
 **/
@Controller
public class SurveyController  extends BaseController {
    @Autowired
    private SurveyService surveyService;

    /**
     * 查询概论
     * @return
     */
    @RequestMapping("/survey")
    @ResponseBody
    public ModelMap getSurvey(){
        return getModelMap(StatusCode.CODE_SUCCESS,surveyService.getSurvey(),null);
    }
    /**
     * 查看概论
     *
     * @return
     */
    @RequestMapping(value = "/viewSurvey")
    @ResponseBody
    public ModelMap viewSurvey(){
        return getModelMap(StatusCode.CODE_SUCCESS,surveyService.getSurvey(),null);
    }
}
