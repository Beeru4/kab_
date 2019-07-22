package com.beerus.common.pojo;

import java.io.Serializable;

/**
 * @Author Beerus
 * @Description 用户类型
 * @Date 2019-06-19
 **/
public class UserType implements Serializable {

    private Integer id;
    private String userTypeName;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserTypeName() {
        return userTypeName;
    }

    public void setUserTypeName(String userTypeName) {
        this.userTypeName = userTypeName;
    }
}
