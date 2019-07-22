package com.beerus.controller;

import com.alibaba.fastjson.JSON;
import com.beerus.common.base.BaseController;
import com.beerus.common.pojo.MemberUser;
import com.beerus.common.utils.RedisConstant;
import com.beerus.common.utils.StatusCode;
import com.beerus.common.utils.Util;
import com.beerus.redis.RedisUtil;
import com.beerus.service.MemberFeignClient;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @Author Beerus
 * @Description 会员控制器
 * @Date 2019-06-24
 **/
@Controller
@RefreshScope
public class MemberController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(MemberController.class);

    // 用户过期时间
    @Value("${redis.userexpireTime}")
    private int expireTime;
    // 验证码过期时间
    @Value("${codetime}")
    private int codeTime;
    @Autowired
    private MemberFeignClient memberFeignClient;
    @Resource
    private RedisUtil redisUtil;

    /**
     * 会员登入 使用redis保存用户
     *
     * @param userName 用户名
     * @param pwd      密码
     * @param session  会话
     * @param model    模型
     * @param request
     * @return
     */
    @RequestMapping(value = "/memberLogin")
    public String onLogin(@RequestParam(value = "username") String userName,
                          @RequestParam(value = "pwd") String pwd, HttpSession session, Model model, HttpServletRequest request) {
        ModelMap login = memberFeignClient.login(getArrayOne(userName), pwd);
        //登录成功
        if (StatusCode.CODE_SUCCESS == (int) login.get("code")) {
            // 判断是否冻结
            if(new ObjectMapper().convertValue(login.get("data"),MemberUser.class).getIsLock() == 0){
                model.addAttribute("userName",getArrayOne(userName));
                model.addAttribute("pwd",getArrayOne(pwd));
                //账号已被锁定
                model.addAttribute(StatusCode.ERROR, "该账号已被冻结,请联系管理员解除冻结.");
                return "login";
            }
            // 保存到redis
            tokenStorage(session, JSON.toJSONString(login.get("data")));
            session.setAttribute("member", new ObjectMapper().convertValue(login.get("data"), MemberUser.class));
            model.addAttribute("member", login.get("data"));
            return personal(model);
        } else {
            model.addAttribute(StatusCode.ERROR, "该账号不存在");
            //登录失败
            return "login";
        }


    }

    /**
     * 登录成功(该映射要路径要拦截 如果用户没登录是不可以访问改路径)
     *
     * @return
     */
    @RequestMapping("/memberLoginAfter")
    public String loginSuccess() {
        return "redirect:index";
    }

    /**
     * 会员注册
     *
     * @param memberUser 要注册的会员
     * @param model
     * @return
     */
    @RequestMapping(value = "/memberRegister")
    public String register(@ModelAttribute MemberUser memberUser,
                           @RequestParam(value = "code") String code,
                           Model model, HttpSession session) {
        if (redisUtil.get(RedisConstant.CODE_KEY + getKey(session), RedisConstant.CODE_DB).equals(getArrayOne(code))) {
            //注册成功 可以查询用户然后保存到缓存页面
            memberUser.setUserName(getUUid());
            ModelMap register = memberFeignClient.register(getMemberUser(memberUser));
            if ((Integer) register.get("code") == StatusCode.CODE_SUCCESS) {
                model.addAttribute("member", memberUser);
                session.setAttribute("id", register.get("data"));
                return "modifyuserinfo";
            } else {
                model.addAttribute(StatusCode.ERROR, "注册失败");
                //注册失败
                return "register";
            }
        } else {
            model.addAttribute(StatusCode.ERROR, "验证码错误");
            //注册失败
            return "register";
        }

    }

    /**
     * 会员修改
     *
     * @param memberUser
     * @return
     */
    @RequestMapping(value = "/modifyMember")
    public String modifyMember(@ModelAttribute MemberUser memberUser, HttpSession session, Model model) {
        String key = RedisConstant.USER_KEY + getKey(session);
        MemberUser user = JSON.parseObject(redisUtil.get(key, RedisConstant.USER_DB), MemberUser.class);

        // 要更新的用户设置id和密码
        MemberUser updateUser = getMemberUser(memberUser);
        updateUser.setUserName(user.getUserName());
        updateUser.setPassword(user.getPassword());
        updateUser.setId(user.getId());
        updateUser.setPassword(user.getPassword());
        updateUser.setBirthday(memberUser.getBirthday());
        updateUser.setClassId(memberUser.getClassId());
        // 更新用户
        ModelMap modelMap = memberFeignClient.modifyMember(updateUser);
        // 更新成功
        if (StatusCode.CODE_SUCCESS == (int) modelMap.get("code")) {
            Object data = memberFeignClient.login(updateUser.getUserName(), updateUser.getPassword()).get("data");
            // 更新redis里的用户
            redisUtil.set(key, JSON.toJSONString(data), RedisConstant.USER_DB);
            redisUtil.expire(key, expireTime, RedisConstant.USER_DB);
            // 重置用户对象
            model.addAttribute("classList", memberFeignClient.modifyMemberBefore().get("data"));//查询教室类型
            session.setAttribute("member", new ObjectMapper().convertValue(data, MemberUser.class));
            model.addAttribute("success", "修改成功");
            return "personal";
        } else {
            // 更新失败
            model.addAttribute("error", "修改失败");
            return "personal";
        }
    }


    /**
     * 检查用户名是否存在
     *
     * @param userName
     * @return
     */
    @RequestMapping(value = "/checkUserName")
    @ResponseBody
    public ModelMap checkUserName(@RequestParam(value = "userName") String userName) {
        return memberFeignClient.checkUserName(getArrayOne(userName));
    }

    /**
     * 检查电话号码是否存在
     *
     * @param telePhone 电话号码
     * @return
     */
    @RequestMapping(value = "/checkPhone")
    @ResponseBody
    public ModelMap checkPhone(@RequestParam(value = "telePhone") String telePhone) {
        return memberFeignClient.checkPhone(getArrayOne(telePhone));
    }

    /**
     * 发送验证码
     *
     * @param mark      标示变量
     * @param telePhone 电话号码
     * @return
     */
    @RequestMapping(value = "/sendCode")
    @ResponseBody
    public ModelMap sendMessage(@RequestParam(value = "mark", required = false, defaultValue = "1") Integer mark,
                                @RequestParam(value = "telePhone") String telePhone, HttpSession session) {
        ModelMap modelMap = memberFeignClient.sendMessage(mark, getArrayOne(telePhone));
        // 保存验证码
        if ((Integer) modelMap.get("code") == StatusCode.CODE_SUCCESS) {
            String key = RedisConstant.CODE_KEY + getKey(session);
            redisUtil.set(key, (Integer) modelMap.get("data") + "", RedisConstant.CODE_DB);
            redisUtil.expire(key, codeTime, RedisConstant.CODE_DB);
        }
        return modelMap;
    }

    /**
     * 修改密码
     *
     * @param telePhone 手机号码
     * @param pwd       密码
     * @param code      验证码
     * @return
     */
    @RequestMapping(value = "/updatePwd")
    public String updatePwd(@RequestParam(value = "telePhone") String telePhone,
                            @RequestParam(value = "pwd") String pwd,
                            @RequestParam(value = "code") String code,
                            HttpSession session, Model model) {
        // 从Redis读取验证码判读是否一致
        if (redisUtil.get(RedisConstant.CODE_KEY + getKey(session), RedisConstant.CODE_DB).equals(getArrayOne(code))) {
            //验证码一致
            ModelMap modelMap = memberFeignClient.updatePwd(getArrayOne(telePhone), getArrayOne(pwd));
            if (StatusCode.CODE_SUCCESS == (int) modelMap.get("code")) {
                model.addAttribute("success", "修改成功");
            } else {
                model.addAttribute("error", "修改失败");
            }
        } else {
            model.addAttribute("telePhone", getArrayOne(telePhone));
            model.addAttribute("pwd", getArrayOne(pwd));
            model.addAttribute("error", "验证码不正确");
        }

        // 判断是从那个位置修改的密码
        if(Util.isEmpty(session.getAttribute("member"))){
            return "updatepwd";
        }else{
            return "modifyupdate";
        }
    }

    /**
     * 修改手机号码
     *
     * @param telePhone 手机号码
     * @param code      验证码
     * @return
     */
    @RequestMapping(value = "/updatePhone")
    public String updatePhone(@RequestParam(value = "telePhone") String telePhone,
                              @RequestParam(value = "code") String code, HttpSession session, Model model) {
        // 从Redis读取验证码判读是否一致
        if (redisUtil.get(RedisConstant.CODE_KEY + getKey(session), RedisConstant.CODE_DB).equals(getArrayOne(code))) {
            // 验证码一致
            ModelMap modelMap = memberFeignClient.updatePhone(getArrayOne(telePhone), ((MemberUser) session.getAttribute("member")).getId());
            if (StatusCode.CODE_SUCCESS == (int) modelMap.get("code")) {
                model.addAttribute("success", "修改成功");
                // 更改缓存当中的号码
                redisUtil.set(RedisConstant.USER_KEY + getKey(session),
                        JSON.toJSONString(new ObjectMapper().convertValue(modelMap.get("data"), MemberUser.class)),
                        RedisConstant.USER_DB);
                session.setAttribute("member",new ObjectMapper().convertValue(modelMap.get("data"), MemberUser.class));
                // 删除验证码
                redisUtil.del(RedisConstant.CODE_DB, RedisConstant.CODE_KEY + getKey(session));
            } else {
                model.addAttribute("msg", "修改失败");
            }
        } else {
            model.addAttribute("telePhone", getArrayOne(telePhone));
            model.addAttribute("msg", "验证码错误");
        }
        return "updtele";
    }

    /**
     * 修改用户信息
     *
     * @param userName 用户名
     * @param password 密码
     * @return
     */
    @RequestMapping(value = "/modifyRegister")
    public String updateUserInfo(@RequestParam(value = "userName") String userName,
                                 @RequestParam(value = "password") String password, HttpSession session, Model model) {
        ModelMap modelMap = memberFeignClient.updateUserInfo(getArrayOne(userName),
                getArrayOne(password),
                (Integer) session.getAttribute("id"));
        if ((Integer) modelMap.get("code") == StatusCode.CODE_SUCCESS) {
            Object data = memberFeignClient.login(getArrayOne(userName), password).get("data");
            String key = RedisConstant.USER_KEY + getKey(session);
            redisUtil.set(key,
                    JSON.toJSONString(data), RedisConstant.USER_DB);
            redisUtil.expire(key, expireTime, RedisConstant.USER_DB);
            session.setAttribute("member", data);
            model.addAttribute("classList", memberFeignClient.modifyMemberBefore().get("data"));//查询教室类型
            // 修改成功
            return "personal";
        } else {
            //修改失败
            return "personal";
        }
    }

    /**
     * 用户跳转到登入页面
     *
     * @return
     */
    @RequestMapping(value = "/login")
    public String login() {
        return "login";
    }

    /**
     * 用户跳转到注册页面
     *
     * @return
     */
    @RequestMapping(value = "/register")
    public String register() {
        return "register";
    }

    /**
     * 用户跳转到首页找回密码
     *
     * @return
     */
    @RequestMapping(value = "/pwd")
    public String indexPwd() {
        return "updatepwd";
    }

    /**
     * 修改密码
     *
     * @return
     */
    @RequestMapping(value = "/modifyupdate")
    public String modifyUpd() {
        return "modifyupdate";
    }

    /**
     * 用户跳转到个人中心
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/personal")
    public String personal(Model model) {
        //从Redis当中读取用户信息
        model.addAttribute("classList", memberFeignClient.modifyMemberBefore().get("data"));//查询教室类型
        return "personal";
    }

    /**
     * 跳转到修改手机页面
     *
     * @return
     */
    @RequestMapping(value = "/modifytele")
    public String modifyTeleBefore() {
        return "updtele";
    }


    /**
     * 生成 token
     *
     * @param session 利用sessionId保存用户
     * @param user    用户
     * @return
     */
    public String tokenStorage(HttpSession session, String user) {
        //生成token
        String token = getKey(session);
        //存储redis里
        redisUtil.set(RedisConstant.USER_KEY + token, user, RedisConstant.USER_DB);
        //设置过期时间
        redisUtil.expire(RedisConstant.USER_KEY + token, expireTime, RedisConstant.USER_DB);
        return token;
    }

    /**
     * 退出
     *
     * @return
     */
    @RequestMapping(value = "/loginout")
    public String loginout(HttpSession session) {
        // 会话移除用户
        session.removeAttribute("member");
        // redis移除
        redisUtil.del(RedisConstant.USER_DB, RedisConstant.USER_KEY + getKey(session));
        return "ok";
    }


    /**
     * 返回第一个值
     *
     * @param stg
     * @return
     */
    public String getArrayOne(String stg) {
        if (null == stg || "".equals(stg)) {
            return stg;
        }
        return stg.split(",")[0];
    }

    /**
     * 得到服务器路径
     *
     * @param request
     * @return
     */
    public String getURL(HttpServletRequest request) {
        return request.getRequestURL().toString();
    }

    /**
     * 返回第一个值
     *
     * @param memberUser
     * @return
     */
    public MemberUser getMemberUser(MemberUser memberUser) {
        MemberUser user = new MemberUser();
        user.setGender(memberUser.getGender());
        user.setPhone(getArrayOne(memberUser.getPhone()));
        user.setUserName(getArrayOne(memberUser.getUserName()));
        user.setMajor(getArrayOne(memberUser.getMajor()));
        user.setName(getArrayOne(memberUser.getName()));
        user.setAuditStatus(getArrayOne(memberUser.getAuditStatus()));
        user.setPost(getArrayOne(memberUser.getPost()));
        user.setSchool(getArrayOne(memberUser.getSchool()));
        user.setAsSubject(getArrayOne(memberUser.getAsSubject()));
        user.setEducation(getArrayOne(memberUser.getEducation()));
        user.setEmail(getArrayOne(memberUser.getEmail()));
        user.setIdCar(getArrayOne(memberUser.getIdCar()));
        user.setAddress(getArrayOne(memberUser.getAddress()));
        return user;
    }
}
