package com.bjlx.QinShihuang.model.misc;

import java.util.List;

import org.hibernate.validator.constraints.Email;
import org.mongodb.morphia.annotations.Embedded;

@Embedded
public class Contact {

	/**
	 * 电话号码列表: 010-83671111
	 */
	private List<String> phoneList;

	/**
	 * 手机号列表: 13811111111
	 */
	private List<String> cellphoneList;

	/**
	 * qq号
	 */
	private String qq;

	/**
	 * 微信号
	 */
	private String weixin;

	/**
	 * 新浪微博
	 */
	private String sina;

	/**
	 * 传真
	 */
	private String fax;

	/**
	 * 电子邮箱
	 */
	@Email
	private String email;

	/**
	 * 网址
	 */
	private String website;

	public List<String> getPhoneList() {
		return phoneList;
	}

	public void setPhoneList(List<String> phoneList) {
		this.phoneList = phoneList;
	}

	public List<String> getCellphoneList() {
		return cellphoneList;
	}

	public void setCellphoneList(List<String> cellphoneList) {
		this.cellphoneList = cellphoneList;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getWeixin() {
		return weixin;
	}

	public void setWeixin(String weixin) {
		this.weixin = weixin;
	}

	public String getSina() {
		return sina;
	}

	public void setSina(String sina) {
		this.sina = sina;
	}
	
}
