package com.bjlx.QinShihuang.model.trace;

import com.bjlx.QinShihuang.model.activity.Activity;
import com.bjlx.QinShihuang.model.misc.Audio;
import com.bjlx.QinShihuang.model.misc.ImageItem;
import com.bjlx.QinShihuang.model.poi.Hotel;
import com.bjlx.QinShihuang.model.poi.Restaurant;
import com.bjlx.QinShihuang.model.poi.Shopping;
import com.bjlx.QinShihuang.model.poi.Viewspot;
import org.bson.types.ObjectId;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Transient;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 足迹
 * @author xiaozhi
 *
 */
@Entity
public class Trace {

	@Transient
	public final static String fd_id = "id";
	@Transient
	public final static String fd_userId = "userId";
	@Transient
	public final static String fd_nickName = "nickName";
	@Transient
	public final static String fd_avatar = "avatar";
	@Transient
	public final static String fd_createTime= "createTime";
	@Transient
	public final static String fd_traceTime = "traceTime";
	@Transient
	public final static String fd_updateTime = "updateTime";
	@Transient
	public final static String fd_cover = "cover";
	@Transient
	public final static String fd_images = "images";
	@Transient
	public final static String fd_audio = "audio";
	@Transient
	public final static String fd_title = "title";
	@Transient
	public final static String fd_desc = "desc";
	@Transient
	public final static String fd_status = "status";
	@Transient
	public final static String fd_activity = "activity";
	@Transient
	public final static String fd_viewspot = "viewspot";
	@Transient
	public final static String fd_restaurant = "restaurant";
	@Transient
	public final static String fd_shopping = "shopping";
	@Transient
	public final static String fd_hotel = "hotel";
	@Transient
	public final static String fd_favorCnt = "favorCnt";
	@Transient
	public final static String fd_commentCnt = "commentCnt";
	@Transient
	public final static String fd_viewCnt = "viewCnt";
	@Transient
	public final static String fd_shareCnt = "shareCnt";
	@Transient
	public final static String fd_originId = "originId";
	@Transient
	public final static String fd_originUserId = "originUserId";
	@Transient
	public final static String fd_originNickName = "originNickName";
	@Transient
	public final static String fd_originAvatar = "originAvatar";
	@Transient
	public final static String fd_lat = "lat";
	@Transient
	public final static String fd_lng = "lng";
	@Transient
	public final static String fd_voteCnt = "voteCnt";

	/**
	 * 主键
	 */
	@NotBlank
	@Id
	private ObjectId id;
	
	/**
	 * 所属用户的用户id
	 */
	@NotNull
	@Min(value = 1)
	private Long userId;
	
	/**
	 * 所属用户的用户昵称
	 */
	@NotBlank
	@Length(min = 1, max = 64)
	private String nickName;
	
	/**
	 * 所属用户的用户头像
	 */
	private ImageItem avatar;
	
	/**
	 * 创建时间
	 */
	private Long createTime;

	/**
	 * 足迹的时间。默认与创建时间是一致的。但是也有可能与创建时间不同，也许是事后创建的足迹
	 */
	private Long traceTime;

	/**
	 * 更新时间
	 */
	private Long updateTime;

	/**
	 * 封面图
	 */
	private ImageItem cover;

	/**
	 * 图片列表
	 */
	private List<ImageItem> images;
	
	/**
	 * 语音描述
	 */
	private Audio audio;

	/**
	 * 标题
	 */
	private String title;

	/**
	 * 文字描述
	 */
	private String desc;

	/**
	 * 状态，1表示私密可不见，2表示好友可见，3表示所有人可见
	 */
	private Integer status = 1;

	/**
	 * 足迹所参加的活动
	 */
	private Activity activity;

	/**
	 * 足迹所在的景点
	 */
	private Viewspot viewspot;

	/**
	 * 足迹所在的餐馆
	 */
	private Restaurant restaurant;

	/**
	 * 足迹所在的购物
	 */
	private Shopping shopping;

	/**
	 * 足迹所在的酒店
	 */
	private Hotel hotel;

	/**
	 * 收藏次数
	 */
	@Min(value = 0)
	private Integer favorCnt = 0;

	/**
	 * 点赞数
	 */
	@Min(value = 0)
	private Integer voteCnt = 0;

	/**
	 * 评论次数
	 */
	@Min(value = 0)
	private Integer commentCnt = 0;

	/**
	 * 浏览次数
	 */
	@Min(value = 0)
	private Integer viewCnt = 0;

	/**
	 * 转发次数
	 */
	@Min(value = 0)
	private Integer shareCnt = 0;

	/**
     * 源足迹id
     */
    private ObjectId originId;
	
	/**
	 * 足迹原创用户id
	 */
	private Long originUserId;

	/**
	 * 足迹原创用户昵称
	 */
	private String originNickName;

	/**
	 * 足迹原创用户头像
	 */
	private ImageItem originAvatar;

	/**
	 * 经度
	 */
	@Min(value = -90)
	@Max(value = 90)
	private Double lat;

	/**
	 * 纬度
	 */
	@Min(value = -180)
	@Max(value = 180)
	private Double lng;

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

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public ImageItem getAvatar() {
		return avatar;
	}

	public void setAvatar(ImageItem avatar) {
		this.avatar = avatar;
	}

	public Long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	public Long getTraceTime() {
		return traceTime;
	}

	public void setTraceTime(Long traceTime) {
		this.traceTime = traceTime;
	}

	public Long getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Long updateTime) {
		this.updateTime = updateTime;
	}

	public ImageItem getCover() {
		return cover;
	}

	public void setCover(ImageItem cover) {
		this.cover = cover;
	}

	public List<ImageItem> getImages() {
		return images;
	}

	public void setImages(List<ImageItem> images) {
		this.images = images;
	}

	public Audio getAudio() {
		return audio;
	}

	public void setAudio(Audio audio) {
		this.audio = audio;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Activity getActivity() {
		return activity;
	}

	public void setActivity(Activity activity) {
		this.activity = activity;
	}

	public Viewspot getViewspot() {
		return viewspot;
	}

	public void setViewspot(Viewspot viewspot) {
		this.viewspot = viewspot;
	}

	public Restaurant getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}

	public Shopping getShopping() {
		return shopping;
	}

	public void setShopping(Shopping shopping) {
		this.shopping = shopping;
	}

	public Hotel getHotel() {
		return hotel;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}

	public ObjectId getOriginId() {
		return originId;
	}

	public void setOriginId(ObjectId originId) {
		this.originId = originId;
	}

	public String getOriginNickName() {
		return originNickName;
	}

	public void setOriginNickName(String originNickName) {
		this.originNickName = originNickName;
	}

	public ImageItem getOriginAvatar() {
		return originAvatar;
	}

	public void setOriginAvatar(ImageItem originAvatar) {
		this.originAvatar = originAvatar;
	}

	public Double getLat() {
		return lat;
	}

	public void setLat(Double lat) {
		this.lat = lat;
	}

	public Double getLng() {
		return lng;
	}

	public void setLng(Double lng) {
		this.lng = lng;
	}

	public Long getOriginUserId() {
		return originUserId;
	}

	public void setOriginUserId(Long originUserId) {
		this.originUserId = originUserId;
	}

	public Integer getFavorCnt() {
		return favorCnt;
	}

	public void setFavorCnt(Integer favorCnt) {
		this.favorCnt = favorCnt;
	}

	public Integer getCommentCnt() {
		return commentCnt;
	}

	public void setCommentCnt(Integer commentCnt) {
		this.commentCnt = commentCnt;
	}

	public Integer getViewCnt() {
		return viewCnt;
	}

	public void setViewCnt(Integer viewCnt) {
		this.viewCnt = viewCnt;
	}

	public Integer getShareCnt() {
		return shareCnt;
	}

	public void setShareCnt(Integer shareCnt) {
		this.shareCnt = shareCnt;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getVoteCnt() {
		return voteCnt;
	}

	public void setVoteCnt(Integer voteCnt) {
		this.voteCnt = voteCnt;
	}

	public Trace() {
	}

	public Trace(Long userId, String nickName, ImageItem avatar) {
		super();
		this.id = new ObjectId();
		this.userId = userId;
		this.nickName = nickName;
		this.avatar = avatar;
	}

	public Trace(Long userId, String nickName, ImageItem avatar, ObjectId originId, Long originUserId,
			String originNickName, ImageItem originAvatar) {
		super();
		this.id = new ObjectId();
		this.userId = userId;
		this.nickName = nickName;
		this.avatar = avatar;
		this.originId = originId;
		this.originUserId = originUserId;
		this.originNickName = originNickName;
		this.originAvatar = originAvatar;
	}
}
