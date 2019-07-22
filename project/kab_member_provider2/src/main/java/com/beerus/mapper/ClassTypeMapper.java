package com.beerus.mapper;

import com.beerus.common.pojo.ClassType;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author Beerus
 * @Description 班级类型映射层
 * @Date 2019-07-01
 **/
@Mapper
public interface ClassTypeMapper {
  /**
   * 查询所有班级类型
   *
   * @return
   */
  List<ClassType> listALlClassType();
}
