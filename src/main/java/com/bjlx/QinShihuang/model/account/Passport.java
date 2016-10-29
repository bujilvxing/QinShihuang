package com.bjlx.QinShihuang.model.account;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 护照
 * @author xiaozhi
 *
 */
public class Passport extends IdProof {

	public final static String fd_nation = "nation";
	public final static String fd_number = "number";
	/**
	 * 国籍
	 */
	@Pattern(regexp = "[A-Z]{2}")
	String nation;

	/**
	 * 护照号码
	 */
	@NotBlank
	String number;

	public String getNation() {
		return nation;
	}

	public void setNation(String nation) {
		this.nation = nation;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}
}
