package com.beerus.controller;

import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.beerus.common.base.BaseController;
import com.beerus.common.pojo.MemberUser;
import com.beerus.common.utils.SmsUtils;
import com.beerus.common.utils.StatusCode;
import com.beerus.common.utils.Util;
import com.beerus.service.ClassTypeService;
import com.beerus.service.MemberService;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @Author Beerus
 * @Description 会员控制器
 * @Date 2019-06-24
 **/
@RestController
public class MemberController extends BaseController {

    /**
     * 会员业务
     */
    @Resource
    private MemberService memberService;
    /**
     * 教室类型业务
     */
    @Resource
    private ClassTypeService classTypeService;

    /**
     * 会员登录
     *
     * @return
     */
    @RequestMapping(value = "/memberLogin")
    public ModelMap login(@RequestParam(value = "userName") String userName,
                          @RequestParam(value = "pwd") String pwd) {
        if (Util.isEmpty(userName) || Util.isEmpty(pwd)) {
            return getModelMap(StatusCode.CODE_EMPTY, null, "账号密码为空");
        }
        MemberUser login = memberService.login(userName, pwd);
        if (Util.isEmpty(login)) {
            //登录失败
            return getModelMap(StatusCode.CODE_ERROR, null, null);
        } else {
            //登录成功
            return getModelMap(StatusCode.CODE_SUCCESS, login, null);
        }
    }

    /**
     * 会员注册
     *
     * @param memberUser
     * @return
     */
    @RequestMapping(value = "/memberRegister")
    public ModelMap register(@RequestBody MemberUser memberUser) {
        if (Util.isEmpty(memberUser) || Util.isEmpty(memberUser.getPhone())) {
            return getModelMap(StatusCode.CODE_EMPTY, null, "填写不完整");
        }
        if (memberService.register(memberUser) > 0) {
            //注册成功
            return getModelMap(StatusCode.CODE_SUCCESS, memberService.login(memberUser.getPhone()).getId(), null);
        } else {
            //注册失败
            return getModelMap(StatusCode.CODE_ERROR, null, null);
        }
    }

    /**
     * 会员修改
     *
     * @param memberUser
     * @return
     */
    @RequestMapping(value = "/modifyMember")
    public ModelMap modifyMember(@RequestBody MemberUser memberUser) {
        if (Util.isEmpty(memberUser)) {
            return getModelMap(StatusCode.CODE_EMPTY, null, "填写不完整");
        }
        if (memberService.modifyMember(memberUser) > 0) {
            //注册成功
            return getModelMap(StatusCode.CODE_SUCCESS, null, null);
        } else {
            //注册失败
            return getModelMap(StatusCode.CODE_ERROR, null, null);
        }
    }

    /**
     * 会员修改前先查询
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/modifyMemberBefore")
    public ModelMap modifyMemberBefore() {
        return getModelMap(StatusCode.CODE_SUCCESS, classTypeService.listALlClassType(), null);
    }

    /**
     * 检查用户名是否存在
     *
     * @param userName 用户名
     * @return
     */
    @RequestMapping(value = "/checkUserName")
    public ModelMap checkUserName(@RequestParam(value = "userName") String userName) {
        if (Util.isEmpty(userName)) {
            return getModelMap(StatusCode.CODE_EMPTY, null, "填写不完整");
        } else {
            return getModelMap(StatusCode.CODE_SUCCESS, memberService.checkNameExists(userName), null);
        }
    }

    /**
     * 检查电话号码是否存在
     *
     * @param telePhone 电话号码
     * @return
     */
    @RequestMapping(value = "/checkPhone")
    public ModelMap checkPhone(@RequestParam(value = "telePhone") String telePhone) {
        if (Util.isEmpty(telePhone)) {
            return getModelMap(StatusCode.CODE_EMPTY, null, "填写不完整");
        } else {
            return getModelMap(StatusCode.CODE_SUCCESS, memberService.checkPhoneExists(telePhone), null);
        }
    }

    /**
     * 修改密码
     *
     * @param telePhone 手机号码
     * @param pwd       密码
     * @return
     */
    @RequestMapping(value = "/updatePwd")
    public ModelMap updatePwd(@RequestParam(value = "telePhone") String telePhone,
                              @RequestParam(value = "pwd") String pwd) {
        if (Util.isEmpty(telePhone) || Util.isEmpty(pwd)) {
            return getModelMap(StatusCode.CODE_EMPTY, null, "填写不完整");
        }
        //验证码正确修改密码
        return getModelMap(StatusCode.CODE_SUCCESS, memberService.modifyUpdate(telePhone, pwd), null);
    }

    /**
     * 发送验证码
     *
     * @param mark      标示变量
     * @param telePhone 电话号码
     * @return
     */
    @RequestMapping(value = "/sendCode")
    public ModelMap sendMessage(@RequestParam(value = "mark") Integer mark,
                                @RequestParam(value = "telePhone") String telePhone) {
        if (Util.isEmpty(mark)) {
            return getModelMap(StatusCode.CODE_SERVER_ERROR, null, "client error");
        } else {
            try {
                // 随机验证码
                int code = (int) ((Math.random() * 9 + 1) * 100000);
                SendSmsResponse sendSmsResponse = SmsUtils.sendSms(telePhone, mark, code);
                return getModelMap(StatusCode.CODE_SUCCESS, code, null);
            } catch (ClientException e) {
                return getModelMap(StatusCode.CODE_SERVER_ERROR, null, "server error");
            }
        }
    }

    /**
     * 修改手机号码
     *
     * @param telePhone 手机号码
     * @param userId    用户ID
     * @return
     */
    @RequestMapping(value = "/updatePhone")
    public ModelMap updatePhone(@RequestParam(value = "telePhone") String telePhone,
                                @RequestParam(value = "userId") Integer userId) {
        if (Util.isEmpty(telePhone) || Util.isEmpty(userId)) {
            return getModelMap(StatusCode.CODE_EMPTY, null, "填写不完整");
        }

        if (memberService.modifyPhone(telePhone, userId)) {
            // 修改成功
            return getModelMap(StatusCode.CODE_SUCCESS, memberService.login(telePhone), null);
        } else {
            // 修改失败
            return getModelMap(StatusCode.CODE_SUCCESS, null, null);
        }

    }

    /**
     * 修改用户信息
     *
     * @param userName 用户名
     * @param password 密码
     * @param id       用户ID
     * @return
     */
    @RequestMapping(value = "/modifyRegister")
    public ModelMap updateUserInfo(@RequestParam(value = "userName") String userName,
                                   @RequestParam(value = "password") String password,
                                   @RequestParam(value = "id") Integer id) {
        return getModelMap(StatusCode.CODE_SUCCESS, memberService.modifyUserInfo(userName, password, id), null);
    }
}
