package com.bjlx.QinShihuang.requestmodel;

/**
 * Created by pengyt on 2016/10/24.
 */
public class BindTelReq {

    /**
     * 手机号
     */
    private String tel;

    /**
     * 令牌
     */
    private String token;

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
