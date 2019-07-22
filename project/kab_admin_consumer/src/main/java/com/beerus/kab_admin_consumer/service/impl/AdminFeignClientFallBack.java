package com.beerus.kab_admin_consumer.service.impl;

import com.beerus.kab_admin_consumer.service.AdminFeignClient;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author Beerus
 * @Description Admin 回调类
 * @Date 2019-06-19
 **/
public class AdminFeignClientFallBack implements AdminFeignClient {
    @Override
    public String backgLogin() {
        return null;
    }

    @Override
    public String adminLogin(String userName, String pwd) {
        return null;
    }

    @Override
    public String test() {
        return null;
    }
}
