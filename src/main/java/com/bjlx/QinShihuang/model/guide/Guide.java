package com.bjlx.QinShihuang.model.guide;

import com.bjlx.QinShihuang.model.activity.Activity;
import com.bjlx.QinShihuang.model.misc.ImageItem;
import com.bjlx.QinShihuang.model.poi.Hotel;
import com.bjlx.QinShihuang.model.poi.Restaurant;
import com.bjlx.QinShihuang.model.poi.Shopping;
import com.bjlx.QinShihuang.model.poi.Viewspot;
import com.bjlx.QinShihuang.model.tripplan.TripPlan;
import org.bson.types.ObjectId;
import org.hibernate.validator.constraints.NotBlank;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Transient;

import javax.validation.constraints.Min;
import java.util.List;

/**
 * 攻略
 * Created by pengyt on 2015/10/21.
 */
@Entity
public class Guide {

	@Transient
	public final static String fd_id = "id";
	@Transient
	public final static String fd_cover = "cover";
	@Transient
	public final static String fd_images = "images";
	@Transient
	public final static String fd_updateTime = "updateTime";
	@Transient
	public final static String fd_title = "title";
	@Transient
	public final static String fd_desc = "desc";
	@Transient
	public final static String fd_bestTripTime = "bestTripTime";
	@Transient
	public final static String fd_tips = "tips";
	@Transient
	public final static String fd_hotels = "hotels";
	@Transient
	public final static String fd_shoppings = "shoppings";
	@Transient
	public final static String fd_restaurants = "restaurants";
	@Transient
	public final static String fd_viewspots = "viewspots";
	@Transient
	public final static String fd_tripPlans = "tripPlans";
	@Transient
	public final static String fd_activities = "activities";
	@Transient
	public final static String fd_summary = "summary";
	@Transient
	public final static String fd_detailUrl = "detailUrl";
	@Transient
	public final static String fd_viewCnt = "viewCnt";
	@Transient
	public final static String fd_shareCnt = "shareCnt";
	
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
	 * 更新时间
	 */
	private Long updateTime;

	/**
	 * 攻略标题
	 */
	@NotBlank
	private String title;

	/**
	 * 城市简介
	 */
	private String desc;

	/**
	 * 最佳旅行时间
	 */
	private String bestTripTime;

	/**
	 * 提示
	 */
	private String tips;

	/**
	 * 攻略中去的poi
	 */
	private List<Hotel> hotels;
	
	/**
	 * 攻略中的购物
	 */
	private List<Shopping> shoppings;

	/**
	 * 攻略中的美食
	 */
	private List<Restaurant> restaurants;

	/**
	 * 攻略中的景点
	 */
	private List<Viewspot> viewspots;

	/**
	 * 攻略中的行程规划
	 */
	private List<TripPlan> tripPlans;

	/**
	 * 攻略中的活动
	 */
	private List<Activity> activities;

	/**
	 * 攻略摘要
	 */
	private String summary;

	/**
	 * 攻略详情
	 */
	private String detailUrl;

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

	private Long createTime;
    
	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getBestTripTime() {
		return bestTripTime;
	}

	public void setBestTripTime(String bestTripTime) {
		this.bestTripTime = bestTripTime;
	}

	public String getTips() {
		return tips;
	}

	public void setTips(String tips) {
		this.tips = tips;
	}

	public List<Viewspot> getViewspots() {
		return viewspots;
	}

	public void setViewspots(List<Viewspot> viewspots) {
		this.viewspots = viewspots;
	}

	public List<TripPlan> getTripPlans() {
		return tripPlans;
	}

	public void setTripPlans(List<TripPlan> tripPlans) {
		this.tripPlans = tripPlans;
	}

	public List<Activity> getActivities() {
		return activities;
	}

	public void setActivities(List<Activity> activities) {
		this.activities = activities;
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

	public Long getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Long updateTime) {
		this.updateTime = updateTime;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<Hotel> getHotels() {
		return hotels;
	}

	public void setHotels(List<Hotel> hotels) {
		this.hotels = hotels;
	}

	public List<Shopping> getShoppings() {
		return shoppings;
	}

	public void setShoppings(List<Shopping> shoppings) {
		this.shoppings = shoppings;
	}

	public List<Restaurant> getRestaurants() {
		return restaurants;
	}

	public void setRestaurants(List<Restaurant> restaurants) {
		this.restaurants = restaurants;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getDetailUrl() {
		return detailUrl;
	}

	public void setDetailUrl(String detailUrl) {
		this.detailUrl = detailUrl;
	}

	public Long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	public Guide() {

	}

	public Guide(ImageItem cover, List<ImageItem> images, Long updateTime, String title, String desc, String bestTripTime, String tips, List<Hotel> hotels, List<Shopping> shoppings, List<Restaurant> restaurants, List<Viewspot> viewspots, List<TripPlan> tripPlans, List<Activity> activities, String summary, String detailUrl) {
		this.id = new ObjectId();
		this.cover = cover;
		this.images = images;
		this.updateTime = updateTime;
		this.title = title;
		this.desc = desc;
		this.bestTripTime = bestTripTime;
		this.tips = tips;
		this.hotels = hotels;
		this.shoppings = shoppings;
		this.restaurants = restaurants;
		this.viewspots = viewspots;
		this.tripPlans = tripPlans;
		this.activities = activities;
		this.summary = summary;
		this.detailUrl = detailUrl;
		this.createTime = System.currentTimeMillis();
	}
}

