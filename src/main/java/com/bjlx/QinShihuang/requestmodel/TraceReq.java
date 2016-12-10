package com.bjlx.QinShihuang.requestmodel;

import com.bjlx.QinShihuang.model.activity.Activity;
import com.bjlx.QinShihuang.model.misc.Audio;
import com.bjlx.QinShihuang.model.misc.ImageItem;
import com.bjlx.QinShihuang.model.poi.Hotel;
import com.bjlx.QinShihuang.model.poi.Restaurant;
import com.bjlx.QinShihuang.model.poi.Shopping;
import com.bjlx.QinShihuang.model.poi.Viewspot;
import com.sun.prism.image.ViewPort;

import java.util.List;

/**
 * 足迹参数
 * Created by zhouyb on 2016/11/20.
 */
public class TraceReq {
    /**
     * 昵称
     */
    private String nickName;

    /**
     * 时间
     */
    private Long traceTime;
    /**
     * 封面
     */
    private ImageItem cover;

    /**
     * 图片
     */
    private List<ImageItem> images;
    /**
     * 音频
     */
    private Audio audio;
    /**
     * 状态
     */
    private int status;
    /**
     * 描述
     */
    private String desc;
    /**
     * 餐厅
     */
    private Restaurant restaurant;

    /**
     * 宾馆
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
    /**
     * originId
     */
    private String originId;
    /**
     * originUserId
     */
    private int originUserId;
    /**
     * originNickName
     */
    private String originNickName;
    /**
     * originAvatar
     */
    private ImageItem originAvatar;
    /**
     * lat
     */
    private double lat;
    /**
     * lng
     */
    private double lng;

    public Long getTraceTime() {
        return traceTime;
    }

    public void setTraceTime(Long traceTime) {
        this.traceTime = traceTime;
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

    public Audio getAudio() {
        return audio;
    }

    public void setAudio(Audio audio) {
        this.audio = audio;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
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

    public String getOriginId() {
        return originId;
    }

    public void setOriginId(String originId) {
        this.originId = originId;
    }

    public int getOriginUserId() {
        return originUserId;
    }

    public void setOriginUserId(int originUserId) {
        this.originUserId = originUserId;
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

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
}
