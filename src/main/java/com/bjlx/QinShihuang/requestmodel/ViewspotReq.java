package com.bjlx.QinShihuang.requestmodel;

import com.bjlx.QinShihuang.model.misc.ImageItem;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * 景点参数
 * Created by pengyt on 2016/12/4.
 */
public class ViewspotReq {
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
    private Double price = 0.0;

    /**
     * 市场价
     */
    @Min(value = 0)
    private Double marketPrice = 0.0;

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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getMarketPrice() {
        return marketPrice;
    }

    public void setMarketPrice(Double marketPrice) {
        this.marketPrice = marketPrice;
    }
}
