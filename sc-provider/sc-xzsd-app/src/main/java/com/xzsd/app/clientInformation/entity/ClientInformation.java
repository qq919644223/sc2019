package com.xzsd.app.clientInformation.entity;

public class ClientInformation {
    /**
     * 门店邀请码
     */
    private String inviteCode;
    /**
     * 用户编号
     */
    private String userId;

    public String getInviteCode() {
        return inviteCode;
    }

    public void setInviteCode(String inviteCode) {
        this.inviteCode = inviteCode;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
