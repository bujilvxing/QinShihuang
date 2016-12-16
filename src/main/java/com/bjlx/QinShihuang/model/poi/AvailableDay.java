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
    private Integer price;

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

	public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public AvailableDay() {

    }

    public AvailableDay(Long bookTime, Boolean available, Integer price) {
        this.bookTime = bookTime;
        this.available = available;
        this.price = price;
    }
}
