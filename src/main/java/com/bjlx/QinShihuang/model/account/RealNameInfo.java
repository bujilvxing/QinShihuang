package com.bjlx.QinShihuang.model.account;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.mongodb.morphia.annotations.Embedded;

import java.util.Date;
import java.util.List;

/**
 * 实名信息
 * @author xiaozhi
 *
 */
@Embedded
public class RealNameInfo {

	/**
	 * 姓
	 */
	@NotBlank
	@Length(min = 1, max = 64)
	private String surname;

	/**
	 * 名
	 */
	@NotBlank
	@Length(min = 1, max = 256)
	private String givenName;

	/**
	 * 性别
	 */
	private Integer gender;

	/**
	 * 出生日期
	 */
	private Date birthday;

	/**
	 * 身份信息
	 */
	private List<IdProof> identities;

	/**
	 * 联系电话
	 */
	private PhoneNumber tel;

	/**
	 * 联系邮箱
	 */
	@Email
	private String email;

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getGivenName() {
		return givenName;
	}

	public void setGivenName(String givenName) {
		this.givenName = givenName;
	}

	public Integer getGender() {
		return gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public List<IdProof> getIdentities() {
		return identities;
	}

	public void setIdentities(List<IdProof> identities) {
		this.identities = identities;
	}

	public PhoneNumber getTel() {
		return tel;
	}

	public void setTel(PhoneNumber tel) {
		this.tel = tel;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public RealNameInfo(String surname, String givenName, Integer gender, Date birthday, List<IdProof> identities,
			PhoneNumber tel, String email) {
		super();
		this.surname = surname;
		this.givenName = givenName;
		this.gender = gender;
		this.birthday = birthday;
		this.identities = identities;
		this.tel = tel;
		this.email = email;
	}
}
