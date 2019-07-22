package com.beerus.service;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


import com.beerus.service.impl.CurriculumFeignClientFallBack;
import org.springframework.cloud.openfeign.FeignClient;


/**
 * @Author Beerus
 * @Description 课程 Feign调用
 * @Date 2019-06-19
 **/
@FeignClient(value = "kab-curriculum-provider", fallback = CurriculumFeignClientFallBack.class)
public interface CurriculumFeignClient {
    /**
     * 首页查询课程公告
     *
     * @return
     */
    @RequestMapping("/listCurriculumByNine")
    ModelMap listCurriculumByNine();

    /**
     * 查看单条公告
     *
     * @return
     */
    @RequestMapping(value = "/viewCurriculum/{id}")
    ModelMap viewCurriculum(@PathVariable("id") Integer id);

    /**
     * 分页查询课程
     *
     * @return
     */
    @RequestMapping(value = "/listCurriculumByPager")
    ModelMap listCurriculumByPager(@RequestParam(value = "currPageNo", required = false, defaultValue = "1") Integer currPageNo);
}
