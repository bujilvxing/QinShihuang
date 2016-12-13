package com.bjlx.QinShihuang.requestmodel;

import com.bjlx.QinShihuang.model.misc.ImageItem;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * 餐馆参数
 * Created by xiaozhi on 2016/11/30.
 */
public class RestaurantReq {

    /**
     * 主键
     */
    @NotBlank
    private String id = null;

    /**
     * 封面图
     */
    @NotNull
    private ImageItem cover;

    /**
     * POI中文名
     */
    @NotNull
    private String zhName;

    /**
     * POI英文名
     */
    private String enName;

    /**
     * POI链接
     */
    @NotBlank
    private String url;

    /**
     * POI价格
     */
    @Min(value = 0)
    private Integer price = 0;

    /**
     * POI价格
     */
    @Min(value = 0)
    private Integer marketPrice = 0;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ImageItem getCover() {
        return cover;
    }

    public void setCover(ImageItem cover) {
        this.cover = cover;
    }

    public String getZhName() {
        return zhName;
    }

    public void setZhName(String zhName) {
        this.zhName = zhName;
    }

    public String getEnName() {
        return enName;
    }

    public void setEnName(String enName) {
        this.enName = enName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getMarketPrice() {
        return marketPrice;
    }

    public void setMarketPrice(Integer marketPrice) {
        this.marketPrice = marketPrice;
    }
}
