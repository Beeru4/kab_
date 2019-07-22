package com.beerus.mapper;

import com.beerus.pojo.MemberUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author Beerus
 * @Description 会员映射层
 * @Date 2019-07-15
 **/
@Mapper
public interface MemberUserMapper {

    /**
     * 根据条件查找用户
     * @param member
     * @param currPageNo
     * @param pageSize
     * @return
     */
    List<MemberUser> listMember(@Param(value = "member") MemberUser member,
                                @Param(value = "currPageNo") Integer currPageNo,
                                @Param(value = "pageSize") Integer pageSize);

    /**
     * 根据条件查询总行数
     * @param member
     * @return
     */
    Integer count_Rows(@Param(value = "member") MemberUser member);

    /**
     * 更改ID用户状态
     * @param id 用户ID
     * @param status 用户状态
     * @return
     */
    Integer modifyStatus(@Param(value = "id")Integer id,@Param(value = "status")Integer status);
}
