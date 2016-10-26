package com.bjlx.QinShihuang.model.misc;

import org.mongodb.morphia.annotations.Embedded;

@Embedded
public class RichText {

	/**
	 * 标题
	 */
	private String title;
	
	/**
	 * 摘要
	 */
	private String summary;
	
	/**
	 * HTML格式的信息
	 */
	private String body;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}
}
