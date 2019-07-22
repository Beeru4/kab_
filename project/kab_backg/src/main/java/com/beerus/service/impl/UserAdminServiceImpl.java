package com.beerus.service.impl;

import com.beerus.mapper.UserAdminMapper;
import com.beerus.pojo.UserAdmin;
import com.beerus.service.UserAdminService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author Beerus
 * @Description 管理员业务层实现
 * @Date 2019-07-15
 **/
@Service(value = "userAdminService")
public class UserAdminServiceImpl implements UserAdminService {

    /**
     * 管理员映射
     */
    @Resource
    private UserAdminMapper userAdminMapper;

    @Override
    public UserAdmin login(String account, String password) throws Exception {
        return userAdminMapper.getUserAdmin(account,password);
    }
}
