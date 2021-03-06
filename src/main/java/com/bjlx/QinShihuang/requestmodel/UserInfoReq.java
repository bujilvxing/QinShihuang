package com.bjlx.QinShihuang.requestmodel;

/**
 * 用户注册信息
 * Created by pengyt on 2016/10/23.
 */
public class UserInfoReq {

    /**
     * 合法的token
     */
    private String token;

    /**
     * 账户
     */
    private String account;

    /**
     * 密码
     */
    private String password;

    /**
     * 邀请码的长度
     */
    private Integer promotionCodeSize;
    
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

	public Integer getPromotionCodeSize() {
		return promotionCodeSize;
	}

	public void setPromotionCodeSize(Integer promotionCodeSize) {
		this.promotionCodeSize = promotionCodeSize;
	}
    
}
