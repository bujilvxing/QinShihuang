package com.bjlx.QinShihuang.requestmodel;

/**
 * 第三方登录用户信息
 * Created by pengyt on 2016/10/23.
 */
public class OAuthUserInfo {

    /**
     * 第三方平台名称
     */
    private String provider;

    /**
     * 第三方平台的用户id
     */
    private String oauthId;

    /**
     * 第三方平台的用户昵称
     */
    private String nickName;

    /**
     * 第三方平台的用户头像
     */
    private String avatar;

    /**
     * 第三方平台的用户令牌
     */
    private String token;

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public String getOauthId() {
        return oauthId;
    }

    public void setOauthId(String oauthId) {
        this.oauthId = oauthId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
