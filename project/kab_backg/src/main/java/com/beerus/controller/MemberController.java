package com.beerus.controller;

import com.beerus.pojo.MemberUser;
import com.beerus.service.MemberService;
import com.beerus.utils.Util;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @Author Beerus
 * @Description 会员控层
 * @Date 2019-07-15
 **/
@Controller
public class MemberController {

    /**
     * 页大小
     */
    private   final int PAGESIZE = 10;

    @Resource
    private MemberService memberService;

    /**
     * 分页查询用户
     *
     * @param memberUser 用户条件
     * @param currPageNo 当前页码
     * @return
     */
    @RequestMapping(value = "/memberManger")
    public String memberManger(@ModelAttribute MemberUser memberUser,
                               @RequestParam(value = "currPageNo", required = false, defaultValue = "1") Integer currPageNo, Model model) {
       if(!Util.isEmpty(memberUser.getName())){
           model.addAttribute("name",memberUser.getName());
       }
        model.addAttribute("pager",memberService.listMember(memberUser,currPageNo,PAGESIZE));
        return "member";
    }

    /**
     * 修改用户冻结状态
     * @param id
     * @param status
     * @return
     */
    @RequestMapping(value = "/lockMember/{id}")
    @ResponseBody
    public  Object lockMember(@PathVariable Integer id, @RequestParam(value = "status") Integer status){
        if(memberService.modifyStatus(id,status)){
            // 修改成功
            return "{\"code\":200}";
        }
        return "{\"code\":-1}";
    }
}
