package com.bjlx.QinShihuang.model.account;

import com.bjlx.QinShihuang.model.activity.Activity;
import com.bjlx.QinShihuang.model.im.Post;
import com.bjlx.QinShihuang.model.marketplace.Commodity;
import com.bjlx.QinShihuang.model.misc.TravelNote;
import com.bjlx.QinShihuang.model.poi.Hotel;
import com.bjlx.QinShihuang.model.poi.Restaurant;
import com.bjlx.QinShihuang.model.poi.Viewspot;
import com.bjlx.QinShihuang.model.quora.Question;
import com.bjlx.QinShihuang.model.trace.Trace;
import com.bjlx.QinShihuang.model.tripplan.TripPlan;
import org.bson.types.ObjectId;
import org.hibernate.validator.constraints.NotBlank;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 用户收藏
 * @author xiaozhi
 *
 */
@Entity
public class Favorite {
	
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
	 * 用户收藏的帖子
	 */
	private List<Post> posts;
	
	/**
	 * 用户收藏的足迹
	 */
	private List<Trace> traces;
	
	/**
	 * 用户收藏的形成规划
	 */
	private List<TripPlan> tripPlans;
	
	/**
	 * 用户收藏的活动
	 */
	private List<Activity>  activities;
	
	/**
	 * 用户收藏的问答
	 */
	private List<Question>  quoras;
	
	/**
	 * 用户收藏的美食
	 */
	private List<Restaurant>  restaurants;
	
	/**
	 * 用户收藏的客栈
	 */
	private List<Hotel>  hotels;
	
	/**
	 * 用户收藏的游记
	 */
	private List<TravelNote>  travelNotes;
	
	/**
	 * 用户收藏的商品
	 */
	private List<Commodity>  commodities;
	
	/**
	 * 用户收藏的景点
	 */
	private List<Viewspot>  viewspots;


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

	public List<Trace> getTraces() {
		return traces;
	}

	public void setTraces(List<Trace> traces) {
		this.traces = traces;
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

	public List<Question> getQuoras() {
		return quoras;
	}

	public void setQuoras(List<Question> quoras) {
		this.quoras = quoras;
	}

	public List<Restaurant> getRestaurants() {
		return restaurants;
	}

	public void setRestaurants(List<Restaurant> restaurants) {
		this.restaurants = restaurants;
	}

	public List<Hotel> getHotels() {
		return hotels;
	}

	public void setHotels(List<Hotel> hotels) {
		this.hotels = hotels;
	}

	public List<TravelNote> getTravelNotes() {
		return travelNotes;
	}

	public void setTravelNotes(List<TravelNote> travelNotes) {
		this.travelNotes = travelNotes;
	}

	public List<Commodity> getCommodities() {
		return commodities;
	}

	public void setCommodities(List<Commodity> commodities) {
		this.commodities = commodities;
	}

	public List<Viewspot> getViewspots() {
		return viewspots;
	}

	public void setViewspots(List<Viewspot> viewspots) {
		this.viewspots = viewspots;
	}

	public List<Post> getPosts() {
		return posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}
	
}
