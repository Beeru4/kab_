package com.beerus.pojo;


import java.io.Serializable;
import java.util.Date;

/**
 * @Author Beerus
 * @Description 新闻Bean
 * @Date 2019-07-15
 **/
public class News implements Serializable {

    private Integer id;
    private String content;
    private Date releaseDate;
    private String title;
    private Date updateDate;
    private Integer browse;
    private String isRecommend;

    private Integer createId;

    /*一对一*/
    private  UserAdmin admin;

    public Integer getCreateId() {
        return createId;
    }

    public void setCreateId(Integer createId) {
        this.createId = createId;
    }

    public UserAdmin getAdmin() {
        return admin;
    }

    public void setAdmin(UserAdmin admin) {
        this.admin = admin;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Integer getBrowse() {
        return browse;
    }

    public void setBrowse(Integer browse) {
        this.browse = browse;
    }

    public String getIsRecommend() {
        return isRecommend;
    }

    public void setIsRecommend(String isRecommend) {
        this.isRecommend = isRecommend;
    }
}
