package com.bjlx.QinShihuang.model.misc;

import org.bson.types.ObjectId;
import org.hibernate.validator.constraints.NotBlank;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Transient;

import javax.validation.constraints.Min;

@Entity
public class Feedback {

	@Transient
	public final static String fd_id = "id";
	@Transient
	public final static String fd_userId = "userId";
	@Transient
	public final static String fd_content = "content";
	@Transient
	public final static String fd_time = "time";
	@Transient
	public final static String fd_origin = "origin";
	
	/**
	 * 主键
	 */
	@NotBlank
	@Id
	private ObjectId id = null;
	
	/**
	 * 用户id
	 */
	@NotBlank
	@Min(value = 1)
	private Long userId;

	/**
	 * 反馈内容
	 */
	private String content;

	/**
	 * 反馈时间
	 */
	private Long time;

	/**
	 * 从哪个App反馈过来的, 例如：不羁旅行
	 */
	private String origin;

	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Long getTime() {
		return time;
	}

	public void setTime(Long time) {
		this.time = time;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public Feedback(Long userId, String content, Long time, String origin) {
		super();
		this.id = new ObjectId();
		this.userId = userId;
		this.content = content;
		this.time = time;
		this.origin = origin;
	}
}
