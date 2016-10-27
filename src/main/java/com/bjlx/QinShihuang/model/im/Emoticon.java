package com.bjlx.QinShihuang.model.im;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Embedded;

@Embedded
public class Emoticon {

	/**
	 * id
	 */
	private ObjectId id;
	
	/**
	 * 表情组
	 */
	private String group;
	
	/**
	 * 链接地址
	 */
	private String url;

	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
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
