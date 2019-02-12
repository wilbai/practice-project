package com.wil.entity;

import java.io.Serializable;

/**
 * Created by wil on 2018/8/17.
 */
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private String phone;
    private String password;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
