package com.bjlx.QinShihuang.model.tripplan;

import org.bson.types.ObjectId;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

import com.bjlx.QinShihuang.model.misc.ImageItem;

import java.util.List;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * Created by pengyt on 2016/7/24.
 * 行程规划
 */
@Entity
public class TripPlan {

	public final static String fd_id = "id";
	public final static String fd_userId = "userId";
	public final static String fd_nickName = "nickName";
	public final static String fd_createTime = "createTime";
	public final static String fd_updateTime = "updateTime";
	public final static String fd_title = "title";
	public final static String fd_desc = "desc";
	public final static String fd_cover = "cover";
	public final static String fd_tripItems = "tripItems";
	public final static String fd_favorCnt = "favorCnt";
	public final static String fd_commentCnt = "commentCnt";
	public final static String fd_viewCnt = "viewCnt";
	public final static String fd_shareCnt = "shareCnt";
	public final static String fd_originId = "originId";
	public final static String fd_originUserId = "originUserId";
	public final static String fd_originNickName = "originNickName";
	public final static String fd_originAvatar = "originAvatar";
	public final static String fd_hotness = "hotness";

	/**
     * 主键
     */
	@Id
	@NotBlank
    private ObjectId id;
    
    /**
	 * 行程规划所属用户的用户id
	 */
	@NotNull
	@Min(value = 1)
	private Long userId;
	
	/**
	 * 行程规划所属用户的用户昵称
	 */
	@NotBlank
	@Length(min = 1, max = 64)
	private String nickName;
	
	/**
	 * 行程规划所属用户的用户头像
	 */
	private ImageItem avatar;

    /**
     * 创建时间
     */
    private Long createTime;

	/**
	 * 更新时间
	 */
	private Long updateTime;

	/**
	 * 标题
	 */
	private String title;

	/**
	 * 描述
	 */
	private String desc;

	/**
	 * 封面
	 */
	private ImageItem cover;

    /**
     * 行程项列表
     */
    private List<TripItem> tripItems;

	/**
	 * 收藏次数
	 */
	@Min(value = 0)
	private Integer favorCnt = 0;

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
     * 源行程规划id
     */
    private ObjectId originId;
    
    /**
	 * 形程规划原创用户id
	 */
	private Long originUserId;

	/**
	 * 形程规划原创用户昵称
	 */
	private String originNickName;

	/**
	 * 形程规划原创用户头像
	 */
	private ImageItem originAvatar;

    /**
     * 热度
     */
    private Double hotness;

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public List<TripItem> getTripItems() {
        return tripItems;
    }

    public void setTripItems(List<TripItem> tripItems) {
        this.tripItems = tripItems;
    }

    public Integer getShareCnt() {
        return shareCnt;
    }

    public void setShareCnt(Integer shareCnt) {
        this.shareCnt = shareCnt;
    }

    public Double getHotness() {
        return hotness;
    }

    public void setHotness(Double hotness) {
        this.hotness = hotness;
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

	public Long getOriginUserId() {
		return originUserId;
	}

	public void setOriginUserId(Long originUserId) {
		this.originUserId = originUserId;
	}

	public Long getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Long updateTime) {
		this.updateTime = updateTime;
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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public ImageItem getCover() {
		return cover;
	}

	public void setCover(ImageItem cover) {
		this.cover = cover;
	}

	public TripPlan(Long userId, String nickName, ImageItem avatar) {
		super();
		this.id = new ObjectId();
		this.userId = userId;
		this.nickName = nickName;
		this.avatar = avatar;
	}

	public TripPlan(Long userId, String nickName, ImageItem avatar, ObjectId originId, Long originUserId,
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
