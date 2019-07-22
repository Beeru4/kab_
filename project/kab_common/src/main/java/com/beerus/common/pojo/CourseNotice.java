package com.beerus.common.pojo;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author Beerus
 * @Description 公告
 * @Date 2019-06-19
 **/
public class CourseNotice implements Serializable {

    private Integer id;
    private String title;
    @JSONField(format = "yyyy-MM-dd")
    private Date releaseDate;
    @JSONField(format = "yyyy-MM-dd")
    private Date updateDate;
    private String content;
    private Integer browse;
    private Integer courseLevelId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getBrowse() {
        return browse;
    }

    public void setBrowse(Integer browse) {
        this.browse = browse;
    }

    public Integer getCourseLevelId() {
        return courseLevelId;
    }

    public void setCourseLevelId(Integer courseLevelId) {
        this.courseLevelId = courseLevelId;
    }
}
