package com.beerus.kab_admin_provider.service;


import com.beerus.common.pojo.UserAdmin;

/**
 * @Author Beerus
 * @Description 用户业务层
 * @Date 2019-06-17
 **/
public interface UserAdminService {
    /**
     * 管理员用户登录
     *
     * @param userName 账号
     * @param pwd      密码
     * @return =null 登入失败 !=null 登录成功
     */
    UserAdmin login(String userName, String pwd);
}
