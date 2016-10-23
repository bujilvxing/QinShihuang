package com.bjlx.QinShihuang.requestmodel;

/**
 * 重置密码
 * Created by pengyt on 2016/10/23.
 */
public class ResetPwd {

    /**
     * 用户账户
     */
    private String account;

    /**
     * 新密码
     */
    private String newPassword;

    /**
     * 令牌
     */
    private String token;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
