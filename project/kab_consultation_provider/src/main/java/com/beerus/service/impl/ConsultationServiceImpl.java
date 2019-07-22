package com.beerus.service.impl;

import com.beerus.common.pojo.Consultation;
import com.beerus.common.utils.Pager;
import com.beerus.mapper.ConsultationMapper;
import com.beerus.service.ConsultationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author Beerus
 * @Description 资讯业务实现层
 * @Date 2019-06-17
 **/
@Service("consultationService")
public class ConsultationServiceImpl implements ConsultationService {

    @Autowired
    private ConsultationMapper consultationMapper;

    @Override
    public List<Consultation> listConsultationByNine() {
        return consultationMapper.listConsultationByNine();
    }

    @Override
    public Consultation viewConsultation(Integer id) {
        return consultationMapper.getConsultation(id);
    }

    @Override
    public Pager<Consultation> listConsultationByPager(Integer currPageNo, Integer pageSize) {
        Pager<Consultation> pager = new Pager<>();
        pager.setCurrPageNo(currPageNo);
        pager.setTotalRows(consultationMapper.count_rows());
        pager.setPageSize(pageSize);
        pager.setTotalPage((pager.getTotalRows() + pageSize - 1) / pageSize);
        pager.setDatas(consultationMapper.listConsultationByPager((currPageNo - 1) * pageSize, pageSize));
        return pager;
    }
}
