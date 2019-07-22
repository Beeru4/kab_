package com.beerus.service;

import com.beerus.service.impl.SurveyFeignClientFallBack;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;


import org.springframework.cloud.openfeign.FeignClient;


/**
 * @Author Beerus
 * @Description Feign调用
 * @Date 2019-06-19
 **/
@FeignClient(value = "kab-survey-provider", fallback = SurveyFeignClientFallBack.class)
public interface SurveyFeignClient {
    /**
     * 查询概论
     *
     * @return
     */
    @RequestMapping("/survey")
    ModelMap getSurvey();

    /**
     * 查看概论
     *
     * @return
     */
    @RequestMapping(value = "/viewSurvey")
    ModelMap viewSurvey();

}
