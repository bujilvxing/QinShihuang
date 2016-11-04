package com.bjlx.QinShihuang.requestmodel;

/**
 * 登录参数
 * @author xiaozhi
 *
 */
public class LoginReq {

	/**
	 * 账户
	 */
	private String account;
	
	/**
	 * 密码
	 */
	private String password;
	
	/**
	 * 个推的clientId
	 */
	private String clientId;

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

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
}
