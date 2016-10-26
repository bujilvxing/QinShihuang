package com.bjlx.QinShihuang.model.marketplace;

import com.bjlx.QinShihuang.model.geo.Locality;
import com.bjlx.QinShihuang.model.misc.ImageItem;
import org.bson.types.ObjectId;
import org.hibernate.validator.constraints.NotBlank;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

import java.util.List;

/**
 * Created by pengyt on 2016/7/24.
 * 商品信息
 */
@Entity
public class Commodity {

    /**
     * 主键
     */
    @NotBlank
    @Id
    private ObjectId id;

    /**
     * 商品分类
     */
    private List<String> category;

    /**
     * 标题
     */
    private String title;

    /**
     * 商品所在地(特色商品)
     */
    private Locality locality;

    /**
     * 描述
     */
    private String desc;

    /**
     * 封面图
     */
    private ImageItem cover;

    /**
     * 图片列表
     */
    private List<ImageItem> images;

    /**
     * 售价
     */
    private Double price;

    /**
     * 市场价
     */
    private Double marketPrice;

    /**
     * 状态。审核中,已发布
     */
    private Integer status;

    /**
     * 套餐
     */
    private List<CommodityPlan> plans;

    /**
     * 销量
     */
    private Integer salesVolume;

    /**
     * 创建时间
     */
    private Long createTime;

    /**
     * 更新时间
     */
    private Long updateTime;

    /**
     * 排名
     */
    private Double rating = 0.0;

    /**
     * 商品所属版本
     */
    private Long version;

    /**
     * 商品类型
     */
    private String commodityType;

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public List<String> getCategory() {
        return category;
    }

    public void setCategory(List<String> category) {
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Locality getLocality() {
        return locality;
    }

    public void setLocality(Locality locality) {
        this.locality = locality;
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

    public List<ImageItem> getImages() {
        return images;
    }

    public void setImages(List<ImageItem> images) {
        this.images = images;
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<CommodityPlan> getPlans() {
        return plans;
    }

    public void setPlans(List<CommodityPlan> plans) {
        this.plans = plans;
    }

    public Integer getSalesVolume() {
        return salesVolume;
    }

    public void setSalesVolume(Integer salesVolume) {
        this.salesVolume = salesVolume;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public String getCommodityType() {
        return commodityType;
    }

    public void setCommodityType(String commodityType) {
        this.commodityType = commodityType;
    }
}
