package com.bjlx.QinShihuang.model.activity;

import org.bson.types.ObjectId;
import org.hibernate.validator.constraints.NotBlank;
import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

/**
 * Created by pengyt on 2016/7/22.
 * 门票
 */
@Entity
@Embedded
public class Ticket {

    /**
     * 主键
     */
    @NotBlank
    @Id
    private ObjectId id;

    /**
     * 门票价格
     */
    private Double price;

    /**
     * 是否免费
     */
    private Boolean free;

    /**
     * 退款方式，1表示退款到平台公共账号，2表示原路返回，3表示不接受退款
     */
    private Integer refundWay;

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
    private String maxNum;

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
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

    public String getMaxNum() {
        return maxNum;
    }

    public void setMaxNum(String maxNum) {
        this.maxNum = maxNum;
    }

    public Ticket(Boolean free, String maxNum) {
        this.id = new ObjectId();
        this.free = free;
        this.maxNum = maxNum;
    }
}
