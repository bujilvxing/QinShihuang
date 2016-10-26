package com.bjlx.QinShihuang.model.marketplace;

import org.hibernate.validator.constraints.Length;
import org.mongodb.morphia.annotations.Embedded;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * Created by pengyt on 2016/7/28.
 * 商品套餐
 */
@Embedded
public class CommodityPlan {

    /**
     * 某套餐的唯一识别号码
     */
    @NotNull
    @Length(min = 1, max = 64)
    private String planId;

    /**
     * 套餐的标题。注意，描述可以为空（该套餐为default套餐时）
     */
    @Length(min = 1, max = 64)
    private String title;

    /**
     * 套餐的描述
     */
    @Length(min = 1, max = 65535)
    private String desc;

    /**
     * 详细的定价信息
     */
    @NotNull
    @Size(min = 1)
    private List<Pricing> pricing;

    /**
     * 市场价
     */
    @Min(value = 0)
    private Integer marketPrice = 0;

    /**
     * 售价
     */
    @Min(value = 0)
    private Integer price = 0;

    /**
     * 库存信息
     */
    private List<StockInfo> stockInfo;

    /**
     * 是否有时间要求
     */
    private Boolean timeRequired;

    public String getPlanId() {
        return planId;
    }

    public void setPlanId(String planId) {
        this.planId = planId;
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

    public List<Pricing> getPricing() {
        return pricing;
    }

    public void setPricing(List<Pricing> pricing) {
        this.pricing = pricing;
    }

    public Integer getMarketPrice() {
        return marketPrice;
    }

    public void setMarketPrice(Integer marketPrice) {
        this.marketPrice = marketPrice;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public List<StockInfo> getStockInfo() {
        return stockInfo;
    }

    public void setStockInfo(List<StockInfo> stockInfo) {
        this.stockInfo = stockInfo;
    }

    public Boolean isTimeRequired() {
        return timeRequired;
    }

    public void setTimeRequired(Boolean timeRequired) {
        this.timeRequired = timeRequired;
    }
}
