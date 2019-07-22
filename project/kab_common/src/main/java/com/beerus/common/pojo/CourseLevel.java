package com.beerus.common.pojo;

import java.io.Serializable;

/**
 * @Author Beerus
 * @Description 公告级别
 * @Date 2019-06-19
 **/
public class CourseLevel implements Serializable {

    private Integer id;
    private String levelName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLevelName() {
        return levelName;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }
}
