package com.bjlx.QinShihuang.model.poi;

import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Transient;

/**
 * Created by pengyt on 2016/7/26.
 * 宾馆房间可利用的时间以及价格
 */
@Embedded
public class AvailableDay {

	@Transient
	public final static String fd_bookTime = "bookTime";
	@Transient
	public final static String fd_available = "available";
	@Transient
	public final static String fd_price = "price";
	
	/**
	 * 预订时间
	 */
    private Long bookTime;

    /**
     * 是否可利用
     */
    private Boolean available;

    /**
     * 价格
     */
    private Double price;

    public Long getBookTime() {
        return bookTime;
    }

    public void setBookTime(Long bookTime) {
        this.bookTime = bookTime;
    }
    
    public Boolean getAvailable() {
		return available;
	}

	public void setAvailable(Boolean available) {
		this.available = available;
	}

	public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
