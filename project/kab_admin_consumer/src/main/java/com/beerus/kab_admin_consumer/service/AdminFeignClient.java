package com.beerus.kab_admin_consumer.service;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


import com.beerus.kab_admin_consumer.service.impl.AdminFeignClientFallBack;
import org.springframework.cloud.openfeign.FeignClient;


/**
 * @Author Beerus
 * @Description Admin Feign调用
 * @Date 2019-06-19
 **/
@FeignClient(value = "kab-admin-provider", fallback = AdminFeignClientFallBack.class)
public interface AdminFeignClient {

    /**
     * 后台登录
     *
     * @return
     */
    @RequestMapping("/backgroundlogin")
    String backgLogin();

    /**
     * 管理员登录
     *
     * @return
     */
    @RequestMapping(value = "/loginAdmin", method = RequestMethod.POST)
    String adminLogin(@RequestParam(value = "userName") String userName,
                      @RequestParam(value = "pwd") String pwd);

    @RequestMapping("/test")
    String test();
}
