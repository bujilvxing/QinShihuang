package com.bjlx.QinShihuang.requestmodel;

/**
 * 绑定邮箱参数
 * Created by xiaozhi on 2016/11/8.
 */
public class BindEmailReq {
    /**
     * 邮箱号
     */
    private String email;

    /**
     * 令牌
     */
    private String token;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
