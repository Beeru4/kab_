package com.beerus.service.impl;

import com.beerus.common.base.BaseController;
import com.beerus.common.pojo.MemberUser;
import com.beerus.service.MemberFeignClient;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;

/**
 * @Author Beerus
 * @Description 异常回调类
 * @Date 2019-06-24
 **/
@Component
public class MemberFeignClientFallBack extends BaseController implements MemberFeignClient {


    @Override
    public ModelMap login(String userName, String pwd) {
        return null;
    }

    @Override
    public ModelMap register(MemberUser memberUser) {
        return getErrorModelMap();
    }

    @Override
    public ModelMap modifyMember(MemberUser memberUser) {
        return getErrorModelMap();
    }

    @Override
    public ModelMap checkUserName(String userName) {
        return getErrorModelMap();
    }

    @Override
    public ModelMap checkPhone(String telePhone) {
        return getErrorModelMap();
    }

    @Override
    public ModelMap sendMessage(Integer mark, String telePhone) {
        return getErrorModelMap();
    }

    @Override
    public ModelMap updatePwd(String telePhone, String pwd) {
        return getErrorModelMap();
    }


    @Override
    public ModelMap modifyMemberBefore() {
        return getErrorModelMap();
    }

    @Override
    public ModelMap updatePhone(String telePhone, Integer userId) {
        return getErrorModelMap();
    }

    @Override
    public ModelMap updateUserInfo(String userName, String password, Integer id) {
        return getErrorModelMap();
    }


}
