package com.bjlx.QinShihuang.model.account;

import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Indexed;

import javax.validation.constraints.NotNull;

/**
 * 第三方平台用户信息
 */
@Embedded
public class OAuthInfo {
	
	/**
     * 第三方账号体系的名称。比如：weixin表示这是微信账号
     */
    @NotNull
    private String provider;

    /**
     * 用户在第三方账号体系中的id
     */
	@Indexed
    @NotNull
    private String oauthId;

    /**
     * 用户在第三方账号的昵称
     */
    @NotNull
    private String nickName;

    /**
     * 用户在第三方账号的头像
     */
    private String avatar;

    /**
     * 用户在第三方账号的token
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

	public OAuthInfo(String provider, String oauthId, String nickName, String avatar, String token) {
		this.provider = provider;
		this.oauthId = oauthId;
		this.nickName = nickName;
		this.avatar = avatar;
		this.token = token;
	}
}
