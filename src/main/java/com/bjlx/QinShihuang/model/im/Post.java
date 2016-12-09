package com.bjlx.QinShihuang.model.im;

import com.bjlx.QinShihuang.model.account.UserInfo;
import com.bjlx.QinShihuang.model.misc.ImageItem;
import org.bson.types.ObjectId;
import org.hibernate.validator.constraints.NotBlank;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Transient;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by pengyt on 2016/7/28.
 * 帖子信息
 */
@Entity
public class Post {

	@Transient
	public final static String fd_id = "id";
	@Transient
	public final static String fd_title = "title";
	@Transient
	public final static String fd_cover = "cover";
	@Transient
	public final static String fd_images = "images";
	@Transient
	public final static String fd_publishTime = "publishTime";
	@Transient
	public final static String fd_updateTime = "updateTime";
	@Transient
	public final static String fd_favorCnt = "favorCnt";
	@Transient
	public final static String fd_commentCnt = "commentCnt";
	@Transient
	public final static String fd_viewCnt = "viewCnt";
	@Transient
	public final static String fd_shareCnt = "shareCnt";
	@Transient
	public final static String fd_summary = "summary";
	@Transient
	public final static String fd_content = "content";
	@Transient
	public final static String fd_rank = "rank";
	@Transient
	public final static String fd_hotness = "hotness";
	@Transient
	public final static String fd_rating = "rating";
	@Transient
	public final static String fd_authorId = "author.userId";
	@Transient
	public final static String fd_author = "author";
	@Transient
    public final static String fd_voteCnt = "voteCnt";
    @Transient
    public final static String fd_chatgroupId = "chatgroupId";

	/**
	 * 主键
	 */
    @NotBlank
    @Id
    private ObjectId id;

    /**
     * 标题
     */
    private String title;

    /**
     * 封面图
     */
    private ImageItem cover;

    /**
     * 图集
     */
    private List<ImageItem> images;

    /**
     * 发表时间
     */
    @NotNull
    private Long publishTime;

    /**
     * 更新时间
     */
    @NotNull
    private Long updateTime;

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
     * 分享次数
     */
    @Min(value = 0)
    private Integer shareCnt = 0;

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
     * 帖子摘要
     */
    @NotBlank
    private String summary;

    /**
     * 帖子详情
     */
    private String content;
    
    /**
     * 排名
     */
    @Min(value = 1)
    private Integer rank;
    
    /**
     * 热门程度
     */
    @Min(value = 0)
    private Double hotness = 0.0;

    /**
     * 评分
     */
    @Min(value = 0)
    private Double rating = 0.0;

    /**
     * 作者的用户id
     */
    private UserInfo author;

    /**
     * 群组id
     */
    private Long chatgroupId;

    /**
     * 帖子状态
     */
    private Integer status;

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public Long getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Long publishTime) {
        this.publishTime = publishTime;
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

    public Integer getShareCnt() {
        return shareCnt;
    }

    public void setShareCnt(Integer shareCnt) {
        this.shareCnt = shareCnt;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public Double getHotness() {
        return hotness;
    }

    public void setHotness(Double hotness) {
        this.hotness = hotness;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public UserInfo getAuthor() {
        return author;
    }

    public void setAuthor(UserInfo author) {
        this.author = author;
    }

    public Integer getVoteCnt() {
        return voteCnt;
    }

    public void setVoteCnt(Integer voteCnt) {
        this.voteCnt = voteCnt;
    }

    public Long getChatgroupId() {
        return chatgroupId;
    }

    public void setChatgroupId(Long chatgroupId) {
        this.chatgroupId = chatgroupId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
