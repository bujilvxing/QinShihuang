package com.bjlx.QinShihuang.model.im;

import org.mongodb.morphia.annotations.Embedded;

@Embedded
public class Emoticon {

	private String id;
	
	private String group;
	
	private String url;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

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
