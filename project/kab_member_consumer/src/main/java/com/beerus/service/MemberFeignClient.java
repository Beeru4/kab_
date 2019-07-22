package com.beerus.service;

import com.beerus.common.pojo.MemberUser;
import com.beerus.service.impl.MemberFeignClientFallBack;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import sun.misc.Cache;

/**
 * @Author Beerus
 * @Description
 * @Date 2019-06-24
 **/
@FeignClient(value = "kab-member-provider", fallback = MemberFeignClientFallBack.class)
public interface MemberFeignClient {
    /**
     * 会员登入
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/memberLogin")
    ModelMap login(@RequestParam(value = "userName") String userName,
                   @RequestParam(value = "pwd") String pwd);

    /**
     * 会员注册
     *
     * @param memberUser
     * @return
     */
    @RequestMapping(value = "/memberRegister")
    ModelMap register(@ModelAttribute MemberUser memberUser);

    /**
     * 会员修改
     *
     * @param memberUser
     * @return
     */
    @RequestMapping(value = "/modifyMember")
    ModelMap modifyMember(@ModelAttribute MemberUser memberUser);

    /**
     * 检查用户名是否存在
     *
     * @param userName
     * @return
     */
    @RequestMapping(value = "/checkUserName")
    ModelMap checkUserName(@RequestParam(value = "userName") String userName);

    /**
     * 检查电话号码是否存在
     *
     * @param telePhone 电话号码
     * @return
     */
    @RequestMapping(value = "/checkPhone")
    ModelMap checkPhone(@RequestParam(value = "telePhone") String telePhone);

    /**
     * 发送验证码
     *
     * @param mark      标示变量
     * @param telePhone 电话号码
     * @return
     */
    @RequestMapping(value = "/sendCode")
    ModelMap sendMessage(@RequestParam(value = "mark") Integer mark,
                         @RequestParam(value = "telePhone") String telePhone);

    /**
     * 修改密码
     *
     * @param telePhone 手机号码
     * @param pwd       密码
     * @return
     */
    @RequestMapping(value = "/updatePwd")
    ModelMap updatePwd(@RequestParam(value = "telePhone") String telePhone,
                       @RequestParam(value = "pwd") String pwd);

    /**
     * 会员修改前先查询
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/modifyMemberBefore")
    ModelMap modifyMemberBefore();

    /**
     * 修改手机号码
     *
     * @param telePhone 手机号码
     * @param userId    用户ID
     * @return
     */
    @RequestMapping(value = "/updatePhone")
    ModelMap updatePhone(@RequestParam(value = "telePhone") String telePhone,
                         @RequestParam(value = "userId") Integer userId);

    /**
     * 修改用户信息
     *
     * @param userName 用户名
     * @param password 密码
     * @param id       用户ID
     * @return
     */
    @RequestMapping(value = "/modifyRegister")
    ModelMap updateUserInfo(@RequestParam(value = "userName") String userName,
                            @RequestParam(value = "password") String password,
                            @RequestParam(value = "id") Integer id);
}
