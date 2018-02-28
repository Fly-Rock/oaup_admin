package com.oaup.admin.backend.support.model.po;

import lombok.Data;

import java.util.Date;

@Data
public class UserInfo {
    private Integer userId;

    private String userName;

    private Date createDate;

    private String passWord;

}