package com.bjlx.QinShihuang.model.account;

import com.bjlx.QinShihuang.model.misc.ImageItem;
import org.bson.types.ObjectId;
import org.hibernate.validator.constraints.NotBlank;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Transient;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * 用户收藏
 * @author xiaozhi
 *
 */
@Entity
public class Favorite {

	@Transient
	public final static String fd_id = "id";
	@Transient
	public final static String fd_userId = "userId";
	@Transient
	public final static String fd_favoriteType = "favoriteType";
	@Transient
	public final static String fd_itemId = "itemId";
	@Transient
	public final static String fd_authorId = "authorId";
	@Transient
	public final static String fd_authorNickName = "authorNickName";
	@Transient
	public final static String fd_authorAvatar = "authorAvatar";
	@Transient
	public final static String fd_cover = "cover";
	@Transient
	public final static String fd_title = "title";
	@Transient
	public final static String fd_favoriteTime = "favoriteTime";
	/**
	 * 主键
	 */
	@NotBlank
	@Id
	private ObjectId id = null;
	
	/**
	 * 用户ID
	 */
	@NotNull
	@Min(value = 1)
	private Long userId;

	/**
	 * 收藏的类型。1表示帖子，2表示足迹，3表示形成规划，4表示活动，5表示问答，6表示美食，7表示客栈，8表示游记，9表示景点，10表示购物，11表示商品
	 */
	private Integer favoriteType;

	/**
	 * 收藏的对象id
	 */
	private ObjectId itemId;

	/**
	 * 作者id
	 */
	private Long authorId;

	/**
	 * 作者昵称
	 */
	private String authorNickName;

	/**
	 * 作者头像
	 */
	private ImageItem authorAvatar;

	/**
	 * 封面
	 */
	private ImageItem cover;

	/**
	 * 标题
	 */
	private String title;

	/**
	 * 收藏时间
	 */
	private Long favoriteTime;

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

	public Integer getFavoriteType() {
		return favoriteType;
	}

	public void setFavoriteType(Integer favoriteType) {
		this.favoriteType = favoriteType;
	}

	public ObjectId getItemId() {
		return itemId;
	}

	public void setItemId(ObjectId itemId) {
		this.itemId = itemId;
	}

	public Long getAuthorId() {
		return authorId;
	}

	public void setAuthorId(Long authorId) {
		this.authorId = authorId;
	}

	public String getAuthorNickName() {
		return authorNickName;
	}

	public void setAuthorNickName(String authorNickName) {
		this.authorNickName = authorNickName;
	}

	public ImageItem getAuthorAvatar() {
		return authorAvatar;
	}

	public void setAuthorAvatar(ImageItem authorAvatar) {
		this.authorAvatar = authorAvatar;
	}

	public ImageItem getCover() {
		return cover;
	}

	public void setCover(ImageItem cover) {
		this.cover = cover;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Long getFavoriteTime() {
		return favoriteTime;
	}

	public void setFavoriteTime(Long favoriteTime) {
		this.favoriteTime = favoriteTime;
	}

	public Favorite() {

	}

	public Favorite(Long userId, Integer favoriteType, ObjectId itemId, String title) {
		this.id = new ObjectId();
		this.userId = userId;
		this.favoriteType = favoriteType;
		this.itemId = itemId;
		this.title = title;
		this.favoriteTime = System.currentTimeMillis();
	}
}
