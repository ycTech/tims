package com.tims.facade.domain;

import java.io.Serializable;

/**
 * @author liuzm
 * @create 2018-02-27 18:33
 **/
public class Test implements Serializable {
    private String id;
    private String name;
    private String phone;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

}
