package com.beerus.common.pojo;

import java.io.Serializable;

/**
 * @Author Beerus
 * @Description 教室类型
 * @Date 2019-06-19
 **/
public class ClassType implements Serializable {

    private Integer id;
    private String classTypeName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getClassTypeName() {
        return classTypeName;
    }

    public void setClassTypeName(String classTypeName) {
        this.classTypeName = classTypeName;
    }
}
