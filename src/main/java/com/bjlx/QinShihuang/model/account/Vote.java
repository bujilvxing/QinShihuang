package com.bjlx.QinShihuang.model.account;

import org.bson.types.ObjectId;
import org.hibernate.validator.constraints.NotBlank;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

/**
 * 点赞
 * @author xiaozhi
 *
 */
@Entity
public class Vote {

	/**
	 * 主键
	 */
	@Id
	@NotBlank
	private ObjectId id;
	
	/**
	 * 用户id
	 */
	private Long userId;
	
	/**
	 * 点赞类型
	 */
	private Integer voteType;
	
	/**
	 * 点赞对象的id
	 */
	private ObjectId targetId;
	
	/**
	 * 点赞时间
	 */
	private Long voteTime;

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

	public Integer getVoteType() {
		return voteType;
	}

	public void setVoteType(Integer voteType) {
		this.voteType = voteType;
	}

	public ObjectId getTargetId() {
		return targetId;
	}

	public void setTargetId(ObjectId targetId) {
		this.targetId = targetId;
	}

	public Long getVoteTime() {
		return voteTime;
	}

	public void setVoteTime(Long voteTime) {
		this.voteTime = voteTime;
	}
	
	
}
