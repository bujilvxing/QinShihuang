package com.bjlx.QinShihuang.model.misc;

import org.hibernate.validator.constraints.URL;
import org.mongodb.morphia.annotations.Embedded;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Embedded
public class Audio {
	
	public final static String fd_length = "length";
	public final static String fd_createTime = "createTime";
	public final static String fd_fileName = "fileName";
	public final static String fd_url = "url";
	public final static String fd_key = "key";
	
	/**
	 * 时长，单位为秒，不能超过60秒
	 */
	@Min(value = 0)
	@Max(value = 60)
	private Integer length;
	
	/**
	 * 创建时间
	 */
	private Long createTime;
	
	/**
	 * 文件名
	 */
	private String fileName;
	
	/**
	 * 链接地址
	 */
	@URL
	private String url;
	
	/**
	 * 获取音频的密钥key
	 */
	private String key;

	public Integer getLength() {
		return length;
	}

	public void setLength(Integer length) {
		this.length = length;
	}

	public Long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}
}
