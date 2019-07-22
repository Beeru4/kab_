package com.beerus.controller;

import com.beerus.pojo.UserAdmin;
import com.beerus.utils.Util;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;


/**
 * @Author Beerus
 * @Description 公共控制层 拿来跳转页面
 * @Date 2019-07-15
 **/
@Controller
public class PublicController {
    /**
     * 跳转到登入页面
     *
     * @return
     */
    @RequestMapping(value = "/")
    public String loginBefore(HttpSession session) {
        // 当前用户已经登入过 不用在登入了
        if (!Util.isEmpty(session.getAttribute("admin"))) {
            // 跳到锁定页面
            if ((int) session.getAttribute("status") == -1) {
                return "redirect:lock_screen";
            }
            return "index";
        }
        return "login";
    }

    /**
     * 跳转到首页
     *
     * @return
     */
    @RequestMapping(value = "/index")
    public String index() {
        return "index";
    }

    /**
     * 锁定页面
     *
     * @return
     */
    @RequestMapping(value = "/lock_screen")
    public String lock(HttpSession session) {
        // 设置为锁定状态
        if ((int) session.getAttribute("status") == 200) {
            session.setAttribute("status", -1);
        }
        return "lock_screen";
    }


    /**
     * 解锁
     *
     * @param session
     * @param password 解锁密码
     * @return
     */
    @RequestMapping(value = "/unLock", produces = "text/html;charset=utf-8")
    @ResponseBody
    public Object unLock(HttpSession session, @RequestParam(value = "password") String password, Model model) {
        // 解锁密码一致
        if (((UserAdmin) session.getAttribute("admin")).getPassword().equals(password)) {
            // 更改锁定状态
            session.setAttribute("status", 200);
            return " {\"code\":200}";
        } else {
            // 密码不一致
            return " {\"code\":-1}";
        }
    }

}
