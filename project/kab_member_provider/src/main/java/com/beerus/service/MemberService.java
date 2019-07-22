package com.beerus.service;


import com.beerus.common.pojo.MemberUser;
import org.apache.ibatis.annotations.Param;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author Beerus
 * @Description 会员业务层
 * @Date 2019-06-17
 **/
@Transactional
public interface MemberService {
    /**
     * 会员登录
     *
     * @param telePhone 电话号码
     * @return =null 登入失败 !=null 登录成功
     */
    @Transactional(readOnly = true)
    MemberUser login(String telePhone);

    /**
     * 会员登录
     *
     * @param username 用户名
     * @param pwd      密码
     * @return =null 登入失败 !=null 登录成功
     */
    @Transactional(readOnly = true)
    MemberUser login(String username, String pwd);

    /**
     * 会员注册
     *
     * @param memberUser 要添加的会员
     * @return 1 > result 成功 <= result 失败
     */
    @Transactional(rollbackFor = {Exception.class})
    Integer register(MemberUser memberUser);

    /**
     * 修改会员
     *
     * @param memberUser 要修改的会员
     * @return 1 > result 成功 <= result 失败
     */
    @Transactional(rollbackFor = {Exception.class})
    Integer modifyMember(MemberUser memberUser);


    /**
     * 检查用户名是否存在
     *
     * @param userName
     * @return
     */
    @Transactional(readOnly = true)
    boolean checkNameExists(String userName);

    /**
     * 检查电话号码是否存在
     *
     * @param telePhone 电话号码
     * @return 1 > result 存在 1<result 成功
     */
    @Transactional(readOnly = true)
    boolean checkPhoneExists(String telePhone);

    /**
     * 根据手机号码修改密码
     *
     * @param telePhone 号码
     * @param pwd       修改的密码
     * @return 1 > 失败  1 < result 成功
     */
    @Transactional(rollbackFor = {Exception.class})
    boolean modifyUpdate(String telePhone, String pwd);

    /**
     * 根据会员ID修改手机号码
     *
     * @param telePhone 手机号码
     * @param id        会员ID
     * @return
     */
    @Transactional(rollbackFor = {Exception.class})
    boolean modifyPhone(String telePhone, Integer id);

    /**
     * 修改用户详细详细
     *
     * @param userName 用户名
     * @param password 密码
     * @param id       用户ID
     * @return
     */
    @Transactional(rollbackFor = {Exception.class})
    Integer modifyUserInfo(String userName,
                           String password,
                           Integer id);
}
