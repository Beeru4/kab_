package com.beerus.kab_admin_consumer.controller;

import com.beerus.common.base.BaseController;
import com.beerus.common.utils.SerializableUtil;
import com.beerus.common.utils.Util;
import com.beerus.kab_admin_consumer.service.AdminFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 * @Author Beerus
 * @Description 管理员控制层
 * @Date 2019-06-17
 **/
@RestController
public class UserAdminController extends BaseController {

    @Autowired
    private AdminFeignClient adminFeignClient;


    /**
     * 管理员登录
     *
     * @return
     */
    @RequestMapping(value = "/loginAdmin", method = RequestMethod.POST)
    public Object adminLogin(@RequestParam(value = "userName") String userName,
                             @RequestParam(value = "pwd") String pwd) {
        if (Util.isEmpty(userName) && Util.isEmpty(pwd)) {
            //账号密码为空
            return getModelMap(1001, null, "username or password is null");
        } else {
            //执行登录
            ModelMap modelMap = SerializableUtil.jsonToObject(adminFeignClient.adminLogin(userName, pwd), ModelMap.class);
            if ((Integer) modelMap.get("code") == 1000) {
                //登录成功
                return  "newsmgr";
            } else {
                //登录失败
                return  modelMap;
            }
        }
    }

    /**
     * 后台登录
     *
     * @return
     */
    @RequestMapping("/backgroundlogin")
    public String backgLogin() {
        return adminFeignClient.backgLogin();
    }

    @RequestMapping("/test")
    public String test() {
        return adminFeignClient.test();
    }
}
