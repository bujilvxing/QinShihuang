package com.bjlx.QinShihuang.model.marketplace;

import org.hibernate.validator.constraints.NotBlank;
import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Transient;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * Created by pengyt on 2016/7/29.
 * 库存状态
 */
@Embedded
public class StockInfo {

	@Transient
	public final static String fd_status = "status";
	@Transient
	public final static String fd_quantity = "quantity";
	@Transient
	public final static String fd_timeRange = "timeRange";
	
    /**
     * 库存状态。可选值：empty, nonempty, plenty。如果为nonempty，后面的quantity字段将生效
     */
    @NotBlank
    @Pattern(regexp = "(empty|nonempty|plenty)")
    private String status;

    /**
     * 库存量
     */
    @Min(value = 0)
    private Integer quantity = 0;

    /**
     * 该库存状态说明所对应的时间区间。格式：[start, end]
     */
    @NotNull
    @Size(min = 2, max = 2)
    private List<Long> timeRange;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public List<Long> getTimeRange() {
        return timeRange;
    }

    public void setTimeRange(List<Long> timeRange) {
        this.timeRange = timeRange;
    }

    public StockInfo(String status, Integer quantity, List<Long> timeRange) {
        this.status = status;
        this.quantity = quantity;
        this.timeRange = timeRange;
    }

    public StockInfo(){

    }
}
