package com.bjlx.QinShihuang.requestmodel;

import com.bjlx.QinShihuang.model.misc.ImageItem;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * 活动参数
 * Created by pengyt on 2016/12/4.
 */
public class ActivityReq {

    /**
     * 主键
     */
    @NotBlank
    private String id;

    /**
     * 活动名称(标题)
     */
    @NotNull
    private String title;

    /**
     * 封面图
     */
    @NotNull
    private ImageItem cover;

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
     * 活动是否为隐私活动，1表示不可见，2表示可见
     */
    @Min(value = 1)
    @Max(value = 2)
    private Integer visiable = 1;

    /**
     * 是否免费
     */
    @NotNull
    private Boolean isFree = false;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ImageItem getCover() {
        return cover;
    }

    public void setCover(ImageItem cover) {
        this.cover = cover;
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

    public Integer getVisiable() {
        return visiable;
    }

    public void setVisiable(Integer visiable) {
        this.visiable = visiable;
    }

    public Boolean isFree() {
        return isFree;
    }

    public void setIsFree(Boolean isFree) {
        this.isFree = isFree;
    }
}
