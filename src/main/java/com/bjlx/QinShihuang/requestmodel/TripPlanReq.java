package com.bjlx.QinShihuang.requestmodel;

import com.bjlx.QinShihuang.model.misc.ImageItem;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 行程规划参数
 * Created by xiaozhi on 2016/11/30.
 */
public class TripPlanReq {

    @NotNull
    private String title;

    /**
     * 描述
     */
    @NotNull
    private String desc;

    /**
     * 封面
     */
    @NotNull
    private ImageItem cover;

    /**
     * 行程项列表
     */
    private List<TripItemReq> tripItems;

    /**
     * 源行程规划id
     */
    private String originId;

    /**
     * 形程规划原创用户id
     */
    private Long originUserId;

    /**
     * 形程规划原创用户昵称
     */
    private String originNickName;

    /**
     * 形程规划原创用户头像
     */
    private ImageItem originAvatar;

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

    public ImageItem getCover() {
        return cover;
    }

    public void setCover(ImageItem cover) {
        this.cover = cover;
    }

    public List<TripItemReq> getTripItems() {
        return tripItems;
    }

    public void setTripItems(List<TripItemReq> tripItems) {
        this.tripItems = tripItems;
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
}
