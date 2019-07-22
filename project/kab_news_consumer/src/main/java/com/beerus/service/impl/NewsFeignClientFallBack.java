package com.beerus.service.impl;

import com.beerus.common.base.BaseController;
import com.beerus.service.NewsFeignClient;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;

/**
 * @Author Beerus
 * @Description 回调类
 * @Date 2019-06-19
 **/
@Component //把类交给Spring管理 否则无效
public class NewsFeignClientFallBack extends BaseController implements NewsFeignClient{

    @Override
    public ModelMap listNewsByNine() {
        return getErrorModelMap();
    }

    @Override
    public ModelMap viewNews(Integer id) {
        return getErrorModelMap();
    }

    @Override
    public ModelMap listNewsPager(Integer currPageNo) {
        return getErrorModelMap();
    }
}
