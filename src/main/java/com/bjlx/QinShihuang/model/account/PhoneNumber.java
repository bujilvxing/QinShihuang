package com.bjlx.QinShihuang.model.account;

import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Transient;

/**
 * 电话号码数据
 * @author xiaozhi
 *
 */
@Embedded
public class PhoneNumber {

	@Transient
	public final static String fd_dialCode = "dialCode";
	@Transient
	public final static String fd_number = "number";

	/**
	 * 国家区域号码
	 */
	private Integer dialCode = 86;
	
	/**
	 * 手机号
	 */
	private String number;

	public PhoneNumber() {
		
	}
	public PhoneNumber(Integer dialCode, String number) {
		super();
		this.dialCode = dialCode;
		this.number = number;
	}

	public Integer getDialCode() {
		return dialCode;
	}

	public void setDialCode(Integer dialCode) {
		this.dialCode = dialCode;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}
}
