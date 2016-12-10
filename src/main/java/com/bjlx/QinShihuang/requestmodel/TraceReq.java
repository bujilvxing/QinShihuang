package com.bjlx.QinShihuang.requestmodel;

import com.bjlx.QinShihuang.model.misc.Audio;
import com.bjlx.QinShihuang.model.misc.ImageItem;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 足迹参数
 * Created by zhouyb on 2016/11/20.
 */
public class TraceReq {

    /**
     * 足迹的时间。默认与创建时间是一致的。但是也有可能与创建时间不同，也许是事后创建的足迹
     */
    @NotNull
    private Long traceTime;

    /**
     * 封面图
     */
    private ImageItem cover;

    /**
     * 图片列表
     */
    private List<ImageItem> images;

    /**
     * 语音描述
     */
    private Audio audio;

    /**
     * 标题
     */
    @NotNull
    private String title;

    /**
     * 文字描述
     */
    private String desc;

    /**
     * 足迹所参加的活动
     */
    private ActivityBasicReq activity;

    /**
     * 足迹所在的景点
     */
    private ViewspotReq viewspot;

    /**
     * 足迹所在的餐馆
     */
    private RestaurantReq restaurant;

    /**
     * 足迹所在的购物
     */
    private ShoppingReq shopping;

    /**
     * 足迹所在的酒店
     */
    private HotelReq hotel;

    /**
     * 源足迹id
     */
    private String originId;

    /**
     * 足迹原创用户id
     */
    private Long originUserId;

    /**
     * 足迹原创用户昵称
     */
    private String originNickName;

    /**
     * 足迹原创用户头像
     */
    private ImageItem originAvatar;

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public ActivityBasicReq getActivity() {
        return activity;
    }

    public void setActivity(ActivityBasicReq activity) {
        this.activity = activity;
    }

    public ViewspotReq getViewspot() {
        return viewspot;
    }

    public void setViewspot(ViewspotReq viewspot) {
        this.viewspot = viewspot;
    }

    public RestaurantReq getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(RestaurantReq restaurant) {
        this.restaurant = restaurant;
    }

    public ShoppingReq getShopping() {
        return shopping;
    }

    public void setShopping(ShoppingReq shopping) {
        this.shopping = shopping;
    }

    public HotelReq getHotel() {
        return hotel;
    }

    public void setHotel(HotelReq hotel) {
        this.hotel = hotel;
    }

    public String getOriginId() {
        return originId;
    }

    public void setOriginId(String originId) {
        this.originId = originId;
    }

    public Long getOriginUserId() {
        return originUserId;
    }

    public void setOriginUserId(Long originUserId) {
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
}
