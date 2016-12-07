package com.bjlx.QinShihuang.requestmodel;

import com.bjlx.QinShihuang.model.activity.Joiner;
import com.bjlx.QinShihuang.model.misc.Address;
import com.bjlx.QinShihuang.model.misc.ImageItem;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 活动参数
 * Created by pengyt on 2016/12/4.
 */
public class ActivityReq {

    /**
     * 活动名称(标题)
     */
    @NotNull
    private String title;

    /**
     * 最大允许人数
     */
    @Min(value = 1)
    private Integer maxNum;

    /**
     * 开始时间
     */
    @Min(value = 1)
    private Long startTime;

    /**
     * 结束时间
     */
    @Min(value = 1)
    private Long endTime;

    /**
     * 活动地点
     */
    @NotNull
    private Address address;

    /**
     * 封面图
     */
    @NotNull
    private ImageItem cover;

    /**
     * 海报
     */
    @NotNull
    private List<ImageItem> posters;

    /**
     * 活动主题
     */
    @NotNull
    private String theme;

    /**
     * 活动分类
     */
    @NotNull
    private String category;

    /**
     * 活动标签
     */
    private List<String> tags;

    /**
     * 活动是否为隐私活动，1表示不可见，2表示可见
     */
    @Min(value = 1)
    @Max(value = 2)
    private Integer visiable = 1;

    /**
     * 活动详情
     */
    @NotNull
    private String desc;

    /**
     * 报名人信息
     */
    private List<Joiner> applicantInfos;

    /**
     * 门票
     */
    private List<String> ticketIds;

    /**
     * 是否免费
     */
    @NotNull
    private Boolean isFree = true;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getMaxNum() {
        return maxNum;
    }

    public void setMaxNum(Integer maxNum) {
        this.maxNum = maxNum;
    }

    public Long getStartTime() {
        return startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    public Long getEndTime() {
        return endTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public ImageItem getCover() {
        return cover;
    }

    public void setCover(ImageItem cover) {
        this.cover = cover;
    }

    public List<ImageItem> getPosters() {
        return posters;
    }

    public void setPosters(List<ImageItem> posters) {
        this.posters = posters;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public Integer getVisiable() {
        return visiable;
    }

    public void setVisiable(Integer visiable) {
        this.visiable = visiable;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public List<Joiner> getApplicantInfos() {
        return applicantInfos;
    }

    public void setApplicantInfos(List<Joiner> applicantInfos) {
        this.applicantInfos = applicantInfos;
    }

    public List<String> getTicketIds() {
        return ticketIds;
    }

    public void setTicketIds(List<String> ticketIds) {
        this.ticketIds = ticketIds;
    }

    public Boolean isFree() {
        return isFree;
    }

    public void setIsFree(Boolean isFree) {
        this.isFree = isFree;
    }
}
