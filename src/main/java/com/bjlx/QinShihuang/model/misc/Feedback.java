package com.bjlx.QinShihuang.model.misc;

import org.bson.types.ObjectId;
import org.hibernate.validator.constraints.NotBlank;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

import javax.validation.constraints.Min;
import java.util.Date;

@Entity
public class Feedback {

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
	Long userId;

	/**
	 * 反馈内容
	 */
	String body;

	/**
	 * 反馈时间
	 */
	Date time;

	/**
	 * 从哪个App反馈过来的, 例如：不羁旅行
	 */
	String origin;

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

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public Feedback(Long userId, String body, Date time, String origin) {
		super();
		this.id = new ObjectId();
		this.userId = userId;
		this.body = body;
		this.time = time;
		this.origin = origin;
	}
}
