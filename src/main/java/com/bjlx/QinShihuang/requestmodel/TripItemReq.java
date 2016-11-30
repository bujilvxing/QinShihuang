package com.bjlx.QinShihuang.requestmodel;

import com.bjlx.QinShihuang.model.activity.Activity;
import com.bjlx.QinShihuang.model.poi.Shopping;
import com.bjlx.QinShihuang.model.poi.Viewspot;

/**
 * 行程规划项参数
 * Created by xiaozhi on 2016/11/30.
 */
public class TripItemReq {

    /**
     * 行程项时间
     */
    private Long tripTime;

    /**
     * 描述
     */
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
    private Viewspot viewspot;

    /**
     * 活动
     */
    private Activity activity;

    /**
     * 购物
     */
    private Shopping shopping;
}
