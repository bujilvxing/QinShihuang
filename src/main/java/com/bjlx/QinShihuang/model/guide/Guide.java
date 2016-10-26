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

import java.util.List;

import javax.validation.constraints.Min;

/**
 * 攻略
 * Created by pengyt on 2015/10/21.
 */
@Entity
public class Guide {

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
	private List<Viewspot> Viewspots;

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
		return Viewspots;
	}

	public void setViewspots(List<Viewspot> viewspots) {
		Viewspots = viewspots;
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
}

