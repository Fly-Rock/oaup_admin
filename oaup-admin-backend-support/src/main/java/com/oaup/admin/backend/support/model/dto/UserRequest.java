package com.oaup.admin.backend.support.model.dto;

import java.io.Serializable;

public class UserRequest implements Serializable {

	private static final long serialVersionUID = -8291621728817208945L;
	
    private Integer userid;

    private String username;


    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
}