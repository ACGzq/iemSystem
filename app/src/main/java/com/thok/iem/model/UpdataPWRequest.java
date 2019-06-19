package com.thok.iem.model;

public class UpdataPWRequest extends BaseRequest {

    /**
     * newPassword : 123456
     * oldPassword : 654321
     */

    private String newPassword;
    private String oldPassword;

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }
}
