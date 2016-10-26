package com.bjlx.QinShihuang.model.poi;

import org.mongodb.morphia.annotations.Embedded;

import java.util.Date;

/**
 * Created by pengyt on 2016/7/26.
 * 宾馆房间可利用的时间以及价格
 */
@Embedded
public class AvailableDay {

    private Date bookTime;

    private Boolean available;

    private Double price;

    public Date getBookTime() {
        return bookTime;
    }

    public void setBookTime(Date bookTime) {
        this.bookTime = bookTime;
    }

    public Boolean isAvailable() {
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
