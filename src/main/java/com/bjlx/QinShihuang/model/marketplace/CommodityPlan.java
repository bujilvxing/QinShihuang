package com.bjlx.QinShihuang.model.marketplace;

import org.hibernate.validator.constraints.Length;
import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Transient;

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

	@Transient
	public final static String fd_planId = "planId";
	@Transient
	public final static String fd_title = "title";
	@Transient
	public final static String fd_desc = "desc";
	@Transient
	public final static String fd_pricings = "pricings";
	@Transient
	public final static String fd_marketPrice = "marketPrice";
	@Transient
	public final static String fd_price = "price";
	@Transient
	public final static String fd_stockInfos = "stockInfos";
	@Transient
	public final static String fd_timeRequired = "timeRequired";
	
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
    private List<Pricing> pricings;

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
    private List<StockInfo> stockInfos;

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

    public List<Pricing> getPricings() {
        return pricings;
    }

    public void setPricings(List<Pricing> pricings) {
        this.pricings = pricings;
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

    public List<StockInfo> getStockInfos() {
        return stockInfos;
    }

    public void setStockInfos(List<StockInfo> stockInfos) {
        this.stockInfos = stockInfos;
    }

    public Boolean getTimeRequired() {
        return timeRequired;
    }

    public void setTimeRequired(Boolean timeRequired) {
        this.timeRequired = timeRequired;
    }

    public CommodityPlan() {

    }

    public CommodityPlan(Boolean timeRequired, List<StockInfo> stockInfos, Integer price, Integer marketPrice, List<Pricing> pricings, String desc, String title, String planId) {
        this.timeRequired = timeRequired;
        this.stockInfos = stockInfos;
        this.price = price;
        this.marketPrice = marketPrice;
        this.pricings = pricings;
        this.desc = desc;
        this.title = title;
        this.planId = planId;
    }
}
