package com.bjlx.QinShihuang.model.misc;

import org.bson.types.ObjectId;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;
import org.mongodb.morphia.annotations.Embedded;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.Date;

@Embedded
public class Audio {

	/**
	 * 主键
	 */
	@NotBlank
	private ObjectId id;
	
	/**
	 * 时长，单位为秒，不能超过60秒
	 */
	@Min(value = 0)
	@Max(value = 60)
	private Integer length;
	
	/**
	 * 创建时间
	 */
	private Date createTime;
	
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

	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}

	public Integer getLength() {
		return length;
	}

	public void setLength(Integer length) {
		this.length = length;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
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
