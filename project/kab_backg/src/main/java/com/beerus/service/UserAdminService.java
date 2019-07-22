package com.beerus.service;

import com.beerus.pojo.UserAdmin;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author Beerus
 * @Description 管理员业务层
 * @Date 2019-07-15
 **/
@Transactional
public interface UserAdminService {
    /**
     * 管理员登录
     *
     * @param account  账号
     * @param password 密码
     * @return
     */
    @Transactional(readOnly = true)
    UserAdmin login(String account, String password) throws Exception;
}
