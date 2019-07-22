package com.beerus.mapper;

import com.beerus.common.pojo.MemberUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Author Beerus
 * @Description 会员映射层
 * @Date 2019-06-24
 **/
@Mapper
public interface MemberMapper {
    /**
     * 根据用户名查询用户
     *
     * @param userName 账号
     * @param pwd      密码
     * @return =null 登入失败 !=null 登录成功
     */
    MemberUser getMemberByUserName(@Param(value = "userName") String userName,
                                   @Param(value = "pwd") String pwd);
    /**
     * 根据电话号码查询用户
     *
     * @param phone 电话号码
     * @return =null 登入失败 !=null 登录成功
     */
    MemberUser getMemberByPhone(@Param(value = "phone") String phone);

    /**
     * 根据邮箱查询用户
     *
     * @param email 邮箱
     * @param pwd   密码
     * @return =null 登入失败 !=null 登录成功
     */
    MemberUser getMemberByEmail(@Param(value = "email") String email,
                                @Param(value = "pwd") String pwd);

    /**
     * 根据用户名检查用户名是否存在
     *
     * @param userName 用户名
     * @return
     */
    Integer getMemberByName(@Param(value = "userName") String userName);

    /**
     * 添加会员
     *
     * @param memberUser 要添加的会员
     * @return 1 > result 成功 <= result 失败
     */
    Integer saveMember(MemberUser memberUser);

    /**
     * 修改会员
     *
     * @param memberUser 要修改的会员
     * @return 1 > result 成功 <= result 失败
     */
    Integer modifyMember(MemberUser memberUser);

    /**
     * 根据手机号码修改密码
     *
     * @param telePhone 号码
     * @param pwd       修改的密码
     * @return 1 > 失败  1 < result 成功
     */
    Integer modifyUpdate(@Param(value = "telePhone") String telePhone,
                         @Param(value = "pwd") String pwd);

    /**
     * 检查电话号码是否存在
     *
     * @param telePhone 电话号码
     * @return 1 > result 存在 1<result 成功
     */
    Integer count_RowsByPhone(@Param(value = "telePhone") String telePhone);

    /**
     * 修改会员手机号码
     *
     * @param telePhone 手机号码
     * @param id        当前用户ID
     * @return 1 > result 失败 1<result 成功
     */
    Integer updatePhone(@Param(value = "telePhone") String telePhone, @Param(value = "id") Integer id);

    /**
     * 修改用户详细详细
     * @param userName 用户名
     * @param password 密码
     * @param id 用户ID
     * @return
     */
    Integer modifyUserInfo(@Param(value = "userName")String userName,
                           @Param(value = "password") String password,
                           @Param(value = "id") Integer id);
}
