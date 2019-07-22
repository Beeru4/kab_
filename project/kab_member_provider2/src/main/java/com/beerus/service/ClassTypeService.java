package com.beerus.service;

import com.beerus.common.pojo.ClassType;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author Beerus
 * @Description 教师类型业务层
 * @Date 2019-07-01
 **/
@Transactional
public interface ClassTypeService {
  /**
   * 查询所有班级类型
   *
   * @return
   */
  @Transactional(readOnly = true)
  List<ClassType> listALlClassType();
}
