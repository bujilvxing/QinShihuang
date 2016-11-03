package com.bjlx.QinShihuang.model.marketplace;

import org.mongodb.morphia.annotations.Embedded;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * Created by pengyt on 2016/7/29.
 * 商品定价
 */
@Embedded
public class Pricing {
	
	public final static String fd_price = "price";
	public final static String fd_timeRange = "timeRange";
	
    /**
     * 商品价格
     */
    @NotNull
    @Min(value = 0)
    private Integer price = 0;

    /**
     * 价格有效期限的区间。格式：[start, end]
     */
    @NotNull
    @Size(min = 2, max = 2)
    private List<Long> timeRange;

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public List<Long> getTimeRange() {
        return timeRange;
    }

    public void setTimeRange(List<Long> timeRange) {
        this.timeRange = timeRange;
    }

    public Pricing(Integer price, List<Long> timeRange) {
        this.price = price;
        this.timeRange = timeRange;
    }
}
