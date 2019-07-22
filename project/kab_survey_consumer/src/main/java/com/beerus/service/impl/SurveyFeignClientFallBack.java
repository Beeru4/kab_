package com.beerus.service.impl;

import com.beerus.common.base.BaseController;
import com.beerus.service.SurveyFeignClient;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;

/**
 * @Author Beerus
 * @Description  回调类
 * @Date 2019-06-19
 **/
@Component
public class SurveyFeignClientFallBack  extends BaseController implements SurveyFeignClient {

  @Override
  public ModelMap getSurvey() {
    return getErrorModelMap();
  }

  @Override
  public ModelMap viewSurvey() {
    return getErrorModelMap();
  }
}
