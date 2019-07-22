package com.beerus.controller;

import com.beerus.pojo.UserAdmin;
import com.beerus.service.UserAdminService;
import com.beerus.utils.Util;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * @Author Beerus
 * @Description 会员登入控制层
 * @Date 2019-07-15
 **/
@Controller
public class LoginController {

    /**
     * 管理员业务实现类
     */
    @Resource
    private UserAdminService userAdminService;

    /**
     * 管理员根据用户名或手机号登入
     *
     * @param userName
     * @param password
     * @return
     */
    @PostMapping(value = "/login")
    public String login(@RequestParam(value = "userName") String userName,
                        @RequestParam(value = "password") String password,
                        Model model, HttpSession session) throws Exception {
        if (Util.isEmpty(userName) || Util.isEmpty(password)) {
            model.addAttribute("error", "账号或密码不能为空");
        } else {
            // 执行登录
            UserAdmin admin = userAdminService.login(userName, password);
            if (Util.isEmpty(admin)) {
                // 登录失败
                model.addAttribute("error", "账号或密码错误");
                model.addAttribute("userName", userName);
            } else {
                // 在当前session中保存管理员
                session.setAttribute("admin", admin);
                // 设为在线状态
                session.setAttribute("status",200);
                // 登录成功
                return "redirect:/index";
            }
        }
        return "login";
    }

    /**
     * 用户退出
     *
     * @param session
     * @return
     */
    @RequestMapping(value = "/loginOut")
    public String loginOut(HttpSession session,Model model) {
        session.removeAttribute("admin");
        return "redirect:/";
    }
}
