package com.bjlx.QinShihuang.model.geo;

import com.bjlx.QinShihuang.model.activity.Activity;
import com.bjlx.QinShihuang.model.misc.ImageItem;
import org.bson.types.ObjectId;
import org.hibernate.validator.constraints.NotBlank;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Transient;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.List;
import java.util.Set;

/**
 * 目的地(城市)
 * @author xiaozhi
 *
 */
@Entity
public class Locality {

	@Transient
	public final static String fd_id = "id";
	@Transient
	public final static String fd_zhName = "zhName";
	@Transient
	public final static String fd_enName = "enName";
	@Transient
	public final static String fd_cover = "cover";
	@Transient
	public final static String fd_images = "images";
	@Transient
	public final static String fd_lat = "lat";
	@Transient
	public final static String fd_lng = "lng";
	@Transient
	public final static String fd_rank = "rank";
	@Transient
	public final static String fd_remoteTraffic = "remoteTraffic";
	@Transient
	public final static String fd_localTraffic = "localTraffic";
	@Transient
	public final static String fd_shoppingIntro = "shoppingIntro";
	@Transient
	public final static String fd_diningIntro = "diningIntro";
	@Transient
	public final static String fd_cuisines = "cuisines";
	@Transient
	public final static String fd_activities = "activities";
	@Transient
	public final static String fd_tips = "tips";
	@Transient
	public final static String fd_geoHistory = "geoHistory";
	@Transient
	public final static String fd_specials = "specials";
	@Transient
	public final static String fd_alias = "alias";
	@Transient
	public final static String fd_visitCnt = "visitCnt";
	@Transient
	public final static String fd_commentCnt = "commentCnt";
	@Transient
	public final static String fd_favorCnt = "favorCnt";
	@Transient
	public final static String fd_hotness = "hotness";
	@Transient
	public final static String fd_rating = "rating";
	@Transient
	public final static String fd_superAdm = "superAdm";
	@Transient
	public final static String fd_tags = "tags";
	@Transient
	public final static String fd_desc = "desc";
	@Transient
	public final static String fd_travelMonth = "travelMonth";
	@Transient
	public final static String fd_timeCostDesc = "timeCostDesc";
	@Transient
	public final static String fd_timeCost = "timeCost";
	
	/**
	 * 主键
	 */
	@NotBlank
	@Id
	private ObjectId id;

	/**
	 * 中文名称
	 */
	private String zhName;

	/**
	 * 英文名称
	 */
	private String enName;
	
	/**
	 * 封面图
	 */
	private ImageItem cover;

	/**
	 * 图集
	 */
	private List<ImageItem> images;

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

	/**
	 * 排名
	 */
	@Min(value = 1)
	private Integer rank;

	/**
	 * 外部交通信息。每个entry都是一个tip，为HTML格式
	 */
	private List<DetailsEntry> remoteTraffic;

	/**
	 * 内部交通信息。每个entry都是一个tip，为HTML格式
	 */
	private List<DetailsEntry> localTraffic;

	/**
	 * 购物综述，HTML格式
	 */
	private String shoppingIntro;

	/**
	 * 美食综述，HTML格式
	 */
	private String diningIntro;
	
	/**
	 * 特色菜式
	 */
	private List<DetailsEntry> cuisines;

	/**
	 * 活动
	 */
	private List<Activity> activities;

	/**
	 * 小贴士
	 */
	private List<DetailsEntry> tips;

	/**
	 * 历史文化
	 */
	private List<DetailsEntry> geoHistory;

	/**
	 * 城市亮点
	 */
	private List<DetailsEntry> specials;

	/**
	 * 别名
	 */
	private Set<String> alias;

	/**
	 * 去过的人数
	 */
	@Min(value = 0)
	private Integer visitCnt = 0;
	
	/**
	 * 评论条数
	 */
	@Min(value = 0)
	private Integer commentCnt = 0;

	/**
	 * 收藏次数
	 */
	@Min(value = 0)
	private Integer favorCnt;

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
	 * 父行政区
	 */
	private Locality superAdm;

	/**
	 * 标签
	 */
	private Set<String> tags;

	/**
	 * 简介
	 */
	private String desc;
	
	/**
	 * 最佳旅行时间
	 */
	private String travelMonth;

	/**
	 * 建议游玩时间
	 */
	private String timeCostDesc;

	/**
	 * 建议游玩时间（单位为小时）
	 */
	@Min(value = 0)
	private Integer timeCost = 0;

	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}

	public String getZhName() {
		return zhName;
	}

	public void setZhName(String zhName) {
		this.zhName = zhName;
	}

	public String getEnName() {
		return enName;
	}

	public void setEnName(String enName) {
		this.enName = enName;
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

	public Integer getRank() {
		return rank;
	}

	public void setRank(Integer rank) {
		this.rank = rank;
	}

	public List<DetailsEntry> getRemoteTraffic() {
		return remoteTraffic;
	}

	public void setRemoteTraffic(List<DetailsEntry> remoteTraffic) {
		this.remoteTraffic = remoteTraffic;
	}

	public List<DetailsEntry> getLocalTraffic() {
		return localTraffic;
	}

	public void setLocalTraffic(List<DetailsEntry> localTraffic) {
		this.localTraffic = localTraffic;
	}

	public String getShoppingIntro() {
		return shoppingIntro;
	}

	public void setShoppingIntro(String shoppingIntro) {
		this.shoppingIntro = shoppingIntro;
	}

	public String getDiningIntro() {
		return diningIntro;
	}

	public void setDiningIntro(String diningIntro) {
		this.diningIntro = diningIntro;
	}

	public List<DetailsEntry> getCuisines() {
		return cuisines;
	}

	public void setCuisines(List<DetailsEntry> cuisines) {
		this.cuisines = cuisines;
	}

	public List<Activity> getActivities() {
		return activities;
	}

	public void setActivities(List<Activity> activities) {
		this.activities = activities;
	}

	public List<DetailsEntry> getTips() {
		return tips;
	}

	public void setTips(List<DetailsEntry> tips) {
		this.tips = tips;
	}

	public List<DetailsEntry> getGeoHistory() {
		return geoHistory;
	}

	public void setGeoHistory(List<DetailsEntry> geoHistory) {
		this.geoHistory = geoHistory;
	}

	public List<DetailsEntry> getSpecials() {
		return specials;
	}

	public void setSpecials(List<DetailsEntry> specials) {
		this.specials = specials;
	}

	public Set<String> getAlias() {
		return alias;
	}

	public void setAlias(Set<String> alias) {
		this.alias = alias;
	}

	public Integer getVisitCnt() {
		return visitCnt;
	}

	public void setVisitCnt(Integer visitCnt) {
		this.visitCnt = visitCnt;
	}

	public Integer getCommentCnt() {
		return commentCnt;
	}

	public void setCommentCnt(Integer commentCnt) {
		this.commentCnt = commentCnt;
	}

	public Integer getFavorCnt() {
		return favorCnt;
	}

	public void setFavorCnt(Integer favorCnt) {
		this.favorCnt = favorCnt;
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

	public Locality getSuperAdm() {
		return superAdm;
	}

	public void setSuperAdm(Locality superAdm) {
		this.superAdm = superAdm;
	}

	public Set<String> getTags() {
		return tags;
	}

	public void setTags(Set<String> tags) {
		this.tags = tags;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getTravelMonth() {
		return travelMonth;
	}

	public void setTravelMonth(String travelMonth) {
		this.travelMonth = travelMonth;
	}

	public String getTimeCostDesc() {
		return timeCostDesc;
	}

	public void setTimeCostDesc(String timeCostDesc) {
		this.timeCostDesc = timeCostDesc;
	}

	public Integer getTimeCost() {
		return timeCost;
	}

	public void setTimeCost(Integer timeCost) {
		this.timeCost = timeCost;
	}
}
