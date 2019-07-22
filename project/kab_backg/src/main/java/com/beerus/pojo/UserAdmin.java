package com.beerus.pojo;


import java.io.Serializable;

/**
 * @Author Beerus
 * @Description 管理员Bean
 * @Date 2019-07-15
 **/
public class UserAdmin implements Serializable {

    private Integer id;
    private String account;
    private String phone;
    private String password;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
