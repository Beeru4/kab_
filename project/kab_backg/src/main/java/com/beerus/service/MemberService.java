package com.beerus.service;

import com.beerus.pojo.MemberUser;
import com.beerus.utils.Pager;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author Beerus
 * @Description 会员业务层
 * @Date 2019-07-19
 **/
@Transactional
public interface MemberService {

    /**
     * 根据条件查找用户
     *
     * @param member
     * @param currPageNo
     * @param pageSize
     * @return
     */
    @Transactional(readOnly = true)
    Pager<MemberUser> listMember(MemberUser member,
                                 Integer currPageNo,
                                 Integer pageSize);

    /**
     * 更改ID用户状态
     * @param id 用户ID
     * @param status 用户状态
     * @return
     */
    @Transactional(rollbackFor =  Exception.class)
    boolean modifyStatus(Integer id,Integer status);
}
