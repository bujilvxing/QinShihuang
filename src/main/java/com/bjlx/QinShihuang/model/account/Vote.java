package com.bjlx.QinShihuang.model.account;

import org.bson.types.ObjectId;
import org.hibernate.validator.constraints.NotBlank;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Transient;

/**
 * 点赞
 * @author xiaozhi
 *
 */
@Entity
public class Vote {

	@Transient
	public final static String fd_id = "id";
	@Transient
	public final static String fd_userId = "userId";
	@Transient
	public final static String fd_voteType = "voteType";
	@Transient
	public final static String fd_itemId = "itemId";
	@Transient
	public final static String fd_voteTime = "voteTime";
	
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
	private ObjectId itemId;
	
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

	public ObjectId getItemId() {
		return itemId;
	}

	public void setItemId(ObjectId itemId) {
		this.itemId = itemId;
	}

	public Long getVoteTime() {
		return voteTime;
	}

	public void setVoteTime(Long voteTime) {
		this.voteTime = voteTime;
	}
	
	
}
