package com.beerus.service.impl;

import com.beerus.common.base.BaseController;
import com.beerus.service.CurriculumFeignClient;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;

/**
 * @Author Beerus
 * @Description 课程回调类
 * @Date 2019-06-19
 **/
@Component
public class CurriculumFeignClientFallBack extends BaseController implements CurriculumFeignClient {


    @Override
    public ModelMap listCurriculumByNine() {
        return getErrorModelMap();
    }

    @Override
    public ModelMap viewCurriculum(Integer id) {
        return getErrorModelMap();
    }

    @Override
    public ModelMap listCurriculumByPager(Integer currPageNo) {
        return getErrorModelMap();
    }
}
