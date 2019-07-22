package com.beerus.mapper;

import com.beerus.pojo.UserAdmin;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Author Beerus
 * @Description 管理员映射层
 * @Date 2019-07-15
 **/
@Mapper
public interface UserAdminMapper {
    /**
     * 根据用户名或者手机号和密码得到管理员用户
     *
     * @param account  账号
     * @param password 密码
     * @return
     */
    UserAdmin getUserAdmin(@Param(value = "account") String account,
                           @Param(value = "password") String password) throws Exception;
}
