package com.beerus.kab_public.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * @Author Beerus
 * @Description 公共控制器
 * @Date 2019-06-19
 **/
@Controller
public class PublicController {
    /**
     * 首页
     *
     * @return
     */
    @RequestMapping("/index")
    public String index(HttpSession session) {
        Object member = session.getAttribute("member");
        return "index";
    }

    /**
     * 关于我们
     *
     * @return
     */
    @RequestMapping("/about")
    public String about() {
        return "about";
    }

    /**
     * 联系我们
     *
     * @return
     */
    @RequestMapping("/contact")
    public String contact() {
        return "contact";
    }

    /**
     * 版权
     *
     * @return
     */
    @RequestMapping("/copy")
    public String copy() {
        return "copy";
    }

    /**
     * 招聘
     *
     * @return
     */
    @RequestMapping("/recruit")
    public String recruit() {
        return "recruit";
    }

    /**
     * 无权限
     *
     * @return
     */
    @RequestMapping("/power")
    public String power() {
        return "power";
    }


}
