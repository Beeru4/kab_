package com.beerus.kab_admin_provider.mapper;

import com.beerus.common.pojo.UserAdmin;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Author Beerus
 * @Description 管理员映射层
 * @Date 2019-06-17
 **/
@Mapper
public interface UserAdminMapper {
    /**
     * 根据管理员账号密码查找用户
     *
     * @param userName 用户名
     * @param pwd      用户密码
     * @return
     */
    UserAdmin getUserAdmin(@Param(value = "userName") String userName, @Param(value = "pwd") String pwd);
}
