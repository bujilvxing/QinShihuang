package com.bjlx.QinShihuang.model.comment;

import com.bjlx.QinShihuang.model.misc.ImageItem;
import org.bson.types.ObjectId;
import org.hibernate.validator.constraints.NotBlank;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Transient;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
public class Comment {

	@Transient
	public final static String fd_id = "id";
	@Transient
	public final static String fd_rating = "rating";
	@Transient
	public final static String fd_userId = "userId";
	@Transient
	public final static String fd_avatar = "avatar";
	@Transient
	public final static String fd_nickName = "nickName";
	@Transient
	public final static String fd_contents = "contents";
	@Transient
	public final static String fd_publishTime = "publishTime";
	@Transient
	public final static String fd_updateTime = "updateTime";
	@Transient
	public final static String fd_commentType = "commentType";
	@Transient
	public final static String fd_itemId = "itemId";
	@Transient
	public final static String fd_images = "images";
	@Transient
	public final static String fd_status = "status";

	/**
	 * 主键
	 */
	@NotBlank
	@Id
	private ObjectId id = null;
	
	/**
	 * 评分
	 */
	@Min(value = 0)
	private Double rating = 0.0;
	
	/**
	 * 用户ID
	 */
	@NotNull
	@Min(value = 1)
	private Long userId = 0L;

	/**
	 * 用户头像
	 */
	private ImageItem avatar;

	/**
	 * 用户昵称
	 */
	private String nickName;

	/**
	 * 评价的详情
	 */
	@NotBlank
	private String contents;

	/**
	 * 评论发表时间
	 */
	@NotNull
	private Long publishTime;

	/**
	 * 评论修改时间
	 */
	private Long updateTime;

	/**
	 * 评论类型
	 */
	private Integer commentType;

	/**
	 * 评论的对象id
	 */
	private ObjectId itemId;

	/**
	 * 评论中附带的照片
	 */
	private List<ImageItem> images;

	/**
	 * 评论状态
	 */
	private Integer status;

	public Long getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Long updateTime) {
		this.updateTime = updateTime;
	}

	public Integer getCommentType() {
		return commentType;
	}

	public void setCommentType(Integer commentType) {
		this.commentType = commentType;
	}

	public ObjectId getItemId() {
		return itemId;
	}

	public void setItemId(ObjectId itemId) {
		this.itemId = itemId;
	}

	public List<ImageItem> getImages() {
		return images;
	}

	public void setImages(List<ImageItem> images) {
		this.images = images;
	}

	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}

	public Double getRating() {
		return rating;
	}

	public void setRating(Double rating) {
		this.rating = rating;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public ImageItem getAvatar() {
		return avatar;
	}

	public void setAvatar(ImageItem avatar) {
		this.avatar = avatar;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public Long getPublishTime() {
		return publishTime;
	}

	public void setPublishTime(Long publishTime) {
		this.publishTime = publishTime;
	}

	public Long getmTime() {
		return updateTime;
	}

	public void setmTime(Long updateTime) {
		this.updateTime = updateTime;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Comment() {

	}

	public Comment(Long userId, ImageItem avatar, String nickName, String contents, Integer commentType, ObjectId itemId) {
		this.id = new ObjectId();
		this.userId = userId;
		this.avatar = avatar;
		this.nickName = nickName;
		this.contents = contents;
		this.commentType = commentType;
		this.itemId = itemId;
		this.publishTime = System.currentTimeMillis();
		this.status = 1;
	}
}
