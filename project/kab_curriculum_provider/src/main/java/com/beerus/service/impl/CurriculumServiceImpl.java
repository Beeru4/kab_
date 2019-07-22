package com.beerus.service.impl;

import com.beerus.common.pojo.CourseNotice;
import com.beerus.common.pojo.Curriculum;
import com.beerus.common.utils.Pager;
import com.beerus.mapper.CurriculumMapper;
import com.beerus.service.CurriculumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author Beerus
 * @Description 课程业务层
 * @Date 2019-06-19
 **/
@Service("curriculumService")
public class CurriculumServiceImpl implements CurriculumService {

  @Autowired
  private CurriculumMapper curriculumMapper;


  @Override
  public List<Curriculum> listCurriculumByNine() {
    return curriculumMapper.listCurriculumByNine();
  }

  @Override
  public Curriculum getCurriculumById(Integer id) {
    return curriculumMapper.getCurriculumById(id);
  }

  @Override
  public Pager<CourseNotice> listCurriculumByPager(Integer currPageNo, Integer pageSize) {
    Pager<CourseNotice> pager = new Pager<>();
    pager.setCurrPageNo(currPageNo);
    pager.setTotalRows(curriculumMapper.count_rows());
    pager.setPageSize(pageSize);
    pager.setTotalPage((pager.getTotalRows() + pageSize - 1) / pageSize);
    pager.setDatas(curriculumMapper.listCurriculumByPager((currPageNo - 1) * pageSize, pageSize));
    return pager;
  }
}
