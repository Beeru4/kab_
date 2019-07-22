package com.beerus.utils;

import java.util.List;

/**
 * @Author Beerus
 * @Description 分页工具类
 * @Date 2019-06-24
 **/
public class Pager<T> {

  /**当前页码*/
  private  Integer currPageNo;
  /**页大小*/
  private  Integer pageSize;
  /**总页码*/
  private  Integer totalPage;
  /**查询的数据*/
  private List<T> datas;
  /**总行数*/
  private  Integer totalRows;

  public Integer getCurrPageNo() {
    return currPageNo;
  }

  public void setCurrPageNo(Integer currPageNo) {
    this.currPageNo = currPageNo;
  }

  public Integer getPageSize() {
    return pageSize;
  }

  public void setPageSize(Integer pageSize) {
    this.pageSize = pageSize;
  }

  public Integer getTotalPage() {
    return totalPage;
  }

  public void setTotalPage(Integer totalPage) {
    this.totalPage = totalPage;
  }

  public List<T> getDatas() {
    return datas;
  }

  public void setDatas(List<T> datas) {
    this.datas = datas;
  }

  public Integer getTotalRows() {
    return totalRows;
  }

  public void setTotalRows(Integer totalRows) {
    this.totalRows = totalRows;
  }
}
