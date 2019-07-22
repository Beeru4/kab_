package com.beerus.service.impl;

import com.beerus.mapper.MemberUserMapper;
import com.beerus.pojo.MemberUser;
import com.beerus.service.MemberService;
import com.beerus.utils.Pager;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author Beerus
 * @Description 会员业务层实现类
 * @Date 2019-07-19
 **/
@Service
public class MemberServiceImpl implements MemberService {

    @Resource
    private MemberUserMapper memberUserMapper;

    @Override
    public Pager<MemberUser> listMember(MemberUser member, Integer currPageNo, Integer pageSize) {
        Pager<MemberUser> pager = new Pager<>();
        pager.setCurrPageNo(currPageNo);
        pager.setPageSize(pageSize);
        pager.setTotalRows(memberUserMapper.count_Rows(member));
        pager.setTotalPage((pager.getTotalRows() + pageSize - 1) / pageSize);
        pager.setDatas(memberUserMapper.listMember(member, (currPageNo - 1) * pageSize, pageSize));
        return  pager;
    }

    @Override
    public boolean modifyStatus(Integer id, Integer status) {
        return memberUserMapper.modifyStatus(id,status) > 0;
    }
}
