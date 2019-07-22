package com.beerus.kab_admin_provider.controller;

import com.beerus.common.base.BaseController;
import com.beerus.common.pojo.UserAdmin;
import com.beerus.common.redis.RedisUtil;
import com.beerus.common.utils.Util;
import com.beerus.kab_admin_provider.service.UserAdminService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @Author Beerus
 * @Description 用户控制层
 * @Date 2019-06-17
 **/
@RestController
public class UserAdminController extends BaseController {
    @Resource(name = "userAdminService")
    private UserAdminService userAdminService;

    @Resource
    private RedisUtil redisUtil;


    /**
     * 管理员登录
     *
     * @return
     */
    @RequestMapping(value = "/loginAdmin", method = RequestMethod.POST)
    public Object adminLogin(@RequestParam(value = "userName") String userName,
                             @RequestParam(value = "pwd") String pwd) {
        //执行登录
        UserAdmin admin = userAdminService.login(userName, pwd);
        if (Util.isEmpty(admin)) {
            //账号密码错误
            return getModelMap(1002,null,"username or password is error");
        } else {
            //账号密码正确
            return getModelMap(1000,null,"login ok");
        }
    }

    @RequestMapping("/test")
    public String test(HttpServletRequest request) {
        return "sessionId:" + request.getSession().getId() + " port:" + request.getServerPort() + "  provider1" + request.getServerPort();
    }
}
