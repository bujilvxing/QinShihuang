package com.bjlx.QinShihuang.requestmodel;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 门票参数
 * Created by xiaozhi on 2016/12/5.
 */
public class TicketReq {

    @NotBlank
    private String id;

    /**
     * 门票价格
     */
    private Double price;

    /**
     * 原价
     */
    private Double marketPrice;

    /**
     * 是否免费
     */
    private Boolean free;

    /**
     * 退款方式，1表示退款到平台公共账号，2表示原路返回，3表示不接受退款
     */
    private Integer refundWay = 2;

    /**
     * 委托平台说明
     */
    private String refundDesc;

    /**
     * 票种说明
     */
    private String desc;

    /**
     * 最大数量
     */
    private Integer maxNum;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public Boolean isFree() {
        return free;
    }

    public void setFree(Boolean free) {
        this.free = free;
    }

    public Integer getRefundWay() {
        return refundWay;
    }

    public void setRefundWay(Integer refundWay) {
        this.refundWay = refundWay;
    }

    public String getRefundDesc() {
        return refundDesc;
    }

    public void setRefundDesc(String refundDesc) {
        this.refundDesc = refundDesc;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Integer getMaxNum() {
        return maxNum;
    }

    public void setMaxNum(Integer maxNum) {
        this.maxNum = maxNum;
    }
}
