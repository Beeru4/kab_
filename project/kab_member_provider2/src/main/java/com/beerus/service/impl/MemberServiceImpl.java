package com.beerus.service.impl;

import com.beerus.common.pojo.MemberUser;
import com.beerus.common.utils.Util;
import com.beerus.mapper.MemberMapper;
import com.beerus.service.MemberService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


/**
 * @Author Beerus
 * @Description 会员用户业务实现层
 * @Date 2019-06-17
 **/
@Service(value = "memberService")
public class MemberServiceImpl implements MemberService {

    @Resource
    private MemberMapper memberMapper;

    @Override
    public MemberUser login(String userName, String pwd) {
        MemberUser memberUser = memberMapper.getMemberByUserName(userName, pwd);
        //判断使用用户名是否登录成功
        if (Util.isEmpty(memberUser)) {
            //登入不成功 使用邮箱
            memberUser = memberMapper.getMemberByEmail(userName, pwd);
        }
        return memberUser;
    }

    @Override
    public MemberUser login(String telePhone) {
        return memberMapper.getMemberByPhone(telePhone);
    }

    @Override
    public Integer register(MemberUser memberUser) {
        return memberMapper.saveMember(memberUser);
    }

    @Override
    public Integer modifyMember(MemberUser memberUser) {
        return memberMapper.modifyMember(memberUser);
    }

    @Override
    public boolean checkNameExists(String userName) {
        return memberMapper.getMemberByName(userName) > 0;
    }

    @Override
    public boolean checkPhoneExists(String telePhone) {
        return memberMapper.count_RowsByPhone(telePhone) > 0;
    }

    @Override
    public boolean modifyUpdate(String telePhone, String pwd) {
        return memberMapper.modifyUpdate(telePhone, pwd) > 0;
    }

    @Override
    public boolean modifyPhone(String telePhone, Integer id) {
        return memberMapper.updatePhone(telePhone, id) > 0;
    }

    @Override
    public Integer modifyUserInfo(String userName, String password, Integer id) {
        return memberMapper.modifyUserInfo(userName,password,id);
    }
}
