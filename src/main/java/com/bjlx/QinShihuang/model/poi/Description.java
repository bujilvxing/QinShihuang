package com.bjlx.QinShihuang.model.poi;

import org.hibernate.validator.constraints.NotBlank;

public class Description {

	/**
	 * 简略描述
	 */
	@NotBlank
	private String desc;

	/**
	 * 描述详情
	 */
	private String details;

	/**
	 * 贴士
	 */
	private String tips;

	/**
	 * 交通信息
	 */
	private String traffic;

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public String getTips() {
		return tips;
	}

	public void setTips(String tips) {
		this.tips = tips;
	}

	public String getTraffic() {
		return traffic;
	}

	public void setTraffic(String traffic) {
		this.traffic = traffic;
	}
}
