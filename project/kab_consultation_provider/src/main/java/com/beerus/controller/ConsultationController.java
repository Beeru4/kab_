package com.beerus.controller;

import com.beerus.common.base.BaseController;
import com.beerus.common.utils.StatusCode;
import com.beerus.service.ConsultationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author Beerus
 * @Description 控制器
 * @Date 2019-06-19
 **/
@RestController
public class ConsultationController extends BaseController {
    @Autowired
    private ConsultationService consultationService;

    /**
     * 首页查询九条资讯
     *
     * @return
     */
    @RequestMapping(value = "/queryConsuByNine")
    public ModelMap queryConsuByNine() {
        return getModelMap(StatusCode.CODE_SUCCESS, consultationService.listConsultationByNine(), null);
    }

    /**
     * 首页单条资讯
     *
     * @return
     */
    @RequestMapping(value = "/viewConsuByNine/{id}")
    public ModelMap viewConsuByNine(@PathVariable("id") Integer id) {
        return getModelMap(StatusCode.CODE_SUCCESS, consultationService.viewConsultation(id), null);
    }

    /**
     * 分页查询
     *
     * @return
     */
    @RequestMapping(value = "/listConsultationByPager")
    public ModelMap listConsultationByPager(@RequestParam(value = "currPageNo", required = false,defaultValue = "1") Integer currPageNo) {
        return getModelMap(StatusCode.CODE_SUCCESS, consultationService.listConsultationByPager(currPageNo, 10), null);
    }
}
