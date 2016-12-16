package com.bjlx.QinShihuang.model.misc;

import org.hibernate.validator.constraints.Email;
import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Transient;

import java.util.List;

@Embedded
public class Contact {

	@Transient
	public final static String fd_phoneList = "phoneList";
	@Transient
	public final static String fd_cellphoneList = "cellphoneList";
	@Transient
	public final static String fd_qq = "qq";
	@Transient
	public final static String fd_weixin = "weixin";
	@Transient
	public final static String fd_sina = "sina";
	@Transient
	public final static String fd_fax = "fax";
	@Transient
	public final static String fd_email = "email";
	@Transient
	public final static String fd_website = "website";

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

	public Contact() {

	}

	public Contact(List<String> phoneList, List<String> cellphoneList, String qq, String weixin, String sina, String fax, String email, String website) {
		this.phoneList = phoneList;
		this.cellphoneList = cellphoneList;
		this.qq = qq;
		this.weixin = weixin;
		this.sina = sina;
		this.fax = fax;
		this.email = email;
		this.website = website;
	}
}
