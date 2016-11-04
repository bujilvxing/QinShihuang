package com.bjlx.QinShihuang.model.misc;

import org.bson.types.ObjectId;
import org.hibernate.validator.constraints.NotBlank;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Transient;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

@Entity
public class TravelNote {

	@Transient
	public final static String fd_id = "id";
	@Transient
	public final static String fd_cover = "cover";
	@Transient
	public final static String fd_images = "images";
	@Transient
	public final static String fd_rating = "rating";
	@Transient
	public final static String fd_hotness = "hotness";
	@Transient
	public final static String fd_title = "title";
	@Transient
	public final static String fd_publishTime = "publishTime";
	@Transient
	public final static String fd_favorCnt = "favorCnt";
	@Transient
	public final static String fd_commentCnt = "commentCnt";
	@Transient
	public final static String fd_viewCnt = "viewCnt";
	@Transient
	public final static String fd_shareCnt = "shareCnt";
	@Transient
	public final static String fd_travelTime = "travelTime";
	@Transient
	public final static String fd_summary = "summary";
	@Transient
	public final static String fd_contents = "contents";
	@Transient
	public final static String fd_source = "source";
	@Transient
	public final static String fd_essence = "essence";


	/**
	 * 主键
	 */
	@NotBlank
	@Id
	private ObjectId id = null;
	
	/**
	 * 封面图
	 */
	private ImageItem cover;

	/**
	 * 图集
	 */
	private List<ImageItem> images;
	
	/**
	 * 评分, 值在0到1之间
	 */
	@Max(value = 1)
	@Min(value = 0)
	private Double rating = 0.0;
	
	@Min(value = 0)
	private Double hotness = 0.0;

	/**
	 * 游记标题
	 */
	@NotBlank
	private String title;

	/**
	 * 发表时间
	 */
	@NotNull
	private Long publishTime;

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
	 * 分享次数
	 */
	@Min(value = 0)
	private Integer shareCnt = 0;

	/**
	 * 出游的时间
	 */
	@NotNull
	private Long travelTime;

	/**
	 * 游记摘要
	 */
	@NotBlank
	private String summary;

	/**
	 * 游记正文
	 */
	private List<Map<String, String>> contents;

	/**
	 * 游记来源
	 */
	private String source;

	/**
	 * 是否为精华游记
	 */
	private Boolean essence = false;

	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
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

	public Double getRating() {
		return rating;
	}

	public void setRating(Double rating) {
		this.rating = rating;
	}

	public Double getHotness() {
		return hotness;
	}

	public void setHotness(Double hotness) {
		this.hotness = hotness;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Long getPublishTime() {
		return publishTime;
	}

	public void setPublishTime(Long publishTime) {
		this.publishTime = publishTime;
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

	public Long getTravelTime() {
		return travelTime;
	}

	public void setTravelTime(Long travelTime) {
		this.travelTime = travelTime;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public List<Map<String, String>> getContents() {
		return contents;
	}

	public void setContents(List<Map<String, String>> contents) {
		this.contents = contents;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public Boolean isEssence() {
		return essence;
	}

	public void setEssence(Boolean essence) {
		this.essence = essence;
	}

	public TravelNote(ObjectId id, ImageItem cover, List<ImageItem> images, Double hotness, String title,
					  Long publishTime, Integer commentCnt, Long travelTime, String summary, List<Map<String, String>> contents,
					  String source, Boolean essence) {
		super();
		this.id = id;
		this.cover = cover;
		this.images = images;
		this.hotness = hotness;
		this.title = title;
		this.publishTime = publishTime;
		this.commentCnt = commentCnt;
		this.travelTime = travelTime;
		this.summary = summary;
		this.contents = contents;
		this.source = source;
		this.essence = essence;
	}
	
}
