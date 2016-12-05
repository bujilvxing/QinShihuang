package com.bjlx.QinShihuang.requestmodel;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * 行程规划项参数
 * Created by xiaozhi on 2016/11/30.
 */
public class TripItemReq {

    /**
     * 行程项时间
     */
    @Min(value = 0)
    private Long tripTime;

    /**
     * 描述
     */
    @NotNull
    private String desc;

    /**
     * 餐馆
     */
    private RestaurantReq restaurant;

    /**
     * 客栈酒店
     */
    private HotelReq hotel;

    /**
     * 景点
     */
    private ViewspotReq viewspot;

    /**
     * 活动
     */
    private ActivityBasicReq activity;

    /**
     * 购物
     */
    private ShoppingReq shopping;

    public Long getTripTime() {
        return tripTime;
    }

    public void setTripTime(Long tripTime) {
        this.tripTime = tripTime;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public RestaurantReq getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(RestaurantReq restaurant) {
        this.restaurant = restaurant;
    }

    public HotelReq getHotel() {
        return hotel;
    }

    public void setHotel(HotelReq hotel) {
        this.hotel = hotel;
    }

    public ViewspotReq getViewspot() {
        return viewspot;
    }

    public void setViewspot(ViewspotReq viewspot) {
        this.viewspot = viewspot;
    }

    public ActivityBasicReq getActivity() {
        return activity;
    }

    public void setActivity(ActivityBasicReq activity) {
        this.activity = activity;
    }

    public ShoppingReq getShopping() {
        return shopping;
    }

    public void setShopping(ShoppingReq shopping) {
        this.shopping = shopping;
    }
}
