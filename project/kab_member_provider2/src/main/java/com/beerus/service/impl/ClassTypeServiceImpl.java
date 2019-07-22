package com.beerus.service.impl;

import com.beerus.common.pojo.ClassType;
import com.beerus.mapper.ClassTypeMapper;
import com.beerus.service.ClassTypeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author Beerus
 * @Description 教室类型业务层实现
 * @Date 2019-07-01
 **/
@Service
public class ClassTypeServiceImpl implements ClassTypeService {
  @Resource
  private ClassTypeMapper classTypeMapper;

  @Override
  public List<ClassType> listALlClassType() {
    return classTypeMapper.listALlClassType();
  }
}
