package com.beerus.kab_admin_provider.service.impl;

import com.beerus.common.pojo.UserAdmin;
import com.beerus.kab_admin_provider.mapper.UserAdminMapper;
import com.beerus.kab_admin_provider.service.UserAdminService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author Beerus
 * @Description 管理用户业务实现层
 * @Date 2019-06-17
 **/
@Service(value = "userAdminService")
public class UserAdminServiceImpl implements UserAdminService {

    @Resource(name = "userAdminMapper")
    private UserAdminMapper userAdminMapper;

    @Override
    public UserAdmin login(String userName, String pwd) {
        return userAdminMapper.getUserAdmin(userName, pwd);
    }
}
