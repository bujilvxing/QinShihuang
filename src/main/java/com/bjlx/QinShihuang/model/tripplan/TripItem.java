package com.bjlx.QinShihuang.model.tripplan;

import com.bjlx.QinShihuang.model.activity.Activity;
import com.bjlx.QinShihuang.model.poi.Hotel;
import com.bjlx.QinShihuang.model.poi.Restaurant;
import com.bjlx.QinShihuang.model.poi.Shopping;
import com.bjlx.QinShihuang.model.poi.Viewspot;
import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Transient;

/**
 * Created by pengyt on 2016/7/24.
 * 行程规划的项
 */
@Embedded
public class TripItem {

	@Transient
    public final static String fd_tripTime = "tripTime";
	@Transient
    public final static String fd_createTime = "createTime";
	@Transient
    public final static String fd_desc = "desc";
	@Transient
    public final static String fd_restaurant = "restaurant";
	@Transient
    public final static String fd_hotel = "hotel";
	@Transient
    public final static String fd_viewspot = "viewspot";
	@Transient
    public final static String fd_activity = "activity";
	@Transient
    public final static String fd_shopping = "shopping";

    /**
     * 行程项时间
     */
    private Long tripTime;

    /**
     * 创建时间
     */
    private Long createTime;

    /**
     * 描述
     */
    private String desc;

    /**
     * 餐馆
     */
    private Restaurant restaurant;

    /**
     * 客栈酒店
     */
    private Hotel hotel;

    /**
     * 景点
     */
    private Viewspot viewspot;

    /**
     * 活动
     */
    private Activity activity;

    /**
     * 购物
     */
    private Shopping shopping;

    public Long getTripTime() {
        return tripTime;
    }

    public void setTripTime(Long tripTime) {
        this.tripTime = tripTime;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public Viewspot getViewspot() {
        return viewspot;
    }

    public void setViewspot(Viewspot viewspot) {
        this.viewspot = viewspot;
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public Shopping getShopping() {
        return shopping;
    }

    public void setShopping(Shopping shopping) {
        this.shopping = shopping;
    }

    public TripItem() {

    }

    public TripItem(Long tripTime, String desc) {
        this.createTime = System.currentTimeMillis();
        this.tripTime = tripTime;
        this.desc = desc;
    }
}
