package com.beerus.controller;

import com.beerus.common.base.BaseController;
import com.beerus.common.utils.StatusCode;
import com.beerus.service.CurriculumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author Beerus
 * @Description 课程控制层
 * @Date 2019-06-19
 **/
@RestController
public class CurriculumController extends BaseController {
    @Autowired
    private CurriculumService curriculumService;

    /**
     * 首页查询课程公告
     *
     * @return
     */
    @RequestMapping("/listCurriculumByNine")
    public ModelMap listCurriculumByNine() {
        return getModelMap(StatusCode.CODE_SUCCESS, curriculumService.listCurriculumByNine(), null);
    }

    /**
     * 查看单条公告
     *
     * @return
     */
    @RequestMapping(value = "/viewCurriculum/{id}")
    public ModelMap viewCurriculum(@PathVariable("id") Integer id) {
        return getModelMap(StatusCode.CODE_SUCCESS, curriculumService.getCurriculumById(id), null);
    }

    /**
     * 分页查询课程
     *
     * @return
     */
    @RequestMapping(value = "/listCurriculumByPager")
    public ModelMap listCurriculumByPager(@RequestParam(value = "currPageNo", required = false, defaultValue = "1") Integer currPageNo) {
        return getModelMap(StatusCode.CODE_SUCCESS, curriculumService.listCurriculumByPager(currPageNo, 10), null);
    }
}
