package com.oaup.admin.backend.support.model.bo;

import lombok.Data;

@Data
public class OAuthUser extends BaseEntity {

    /**
     *
     */
    private static final long serialVersionUID = 2836972841233228L;

    private String userId;

    private  String userName;

    private String oAuthType;

    private String oAuthId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getoAuthType() {
        return oAuthType;
    }

    public void setoAuthType(String oAuthType) {
        this.oAuthType = oAuthType;
    }

    public String getoAuthId() {
        return oAuthId;
    }

    public void setoAuthId(String oAuthId) {
        this.oAuthId = oAuthId;
    }
}
