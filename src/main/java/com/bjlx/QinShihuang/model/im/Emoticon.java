package com.bjlx.QinShihuang.model.im;

import org.mongodb.morphia.annotations.Embedded;

@Embedded
public class Emoticon {
	
	public final static String fd_group = "group";
	public final static String fd_url = "url";
	/**
	 * 表情组
	 */
	private String group;
	
	/**
	 * 链接地址
	 */
	private String url;

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}
