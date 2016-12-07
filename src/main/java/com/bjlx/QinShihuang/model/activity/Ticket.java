package com.bjlx.QinShihuang.model.activity;

import org.bson.types.ObjectId;
import org.hibernate.validator.constraints.NotBlank;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Transient;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * Created by pengyt on 2016/7/22.
 * 门票
 */
@Entity
public class Ticket {

	@Transient
    public final static String fd_id = "id";
	@Transient
    public final static String fd_price = "price";
	@Transient
    public final static String fd_marketPrice = "marketPrice";
	@Transient
    public final static String fd_free = "free";
	@Transient
    public final static String fd_refundWay = "refundWay";
	@Transient
    public final static String fd_refundDesc = "refundDesc";
	@Transient
    public final static String fd_desc = "desc";
	@Transient
    public final static String fd_maxNum = "maxNum";
    @Transient
    public final static String fd_title = "title";
    @Transient
    public final static String fd_status = "status";
    @Transient
    public final static String fd_creatorId = "creatorId";
    @Transient
    public final static String fd_updateTime = "updateTime";

    /**
     * 主键
     */
    @NotBlank
    @Id
    private ObjectId id;

    /**
     * 门票标题
     */
    @NotNull
    private String title;

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
    @Min(value = 1)
    private Integer maxNum;

    /**
     * 创建时间
     */
    private Long createTime;

    /**
     * 更新时间
     */
    private Long updateTime;

    /**
     * 门票状态。1表示正常，2表示已删除
     */
    private Integer status;

    /**
     * 门票创建人
     */
    private Long creatorId;

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

    public Integer getMaxNum() {
        return maxNum;
    }

    public void setMaxNum(Integer maxNum) {
        this.maxNum = maxNum;
    }

    public Double getMarketPrice() {
        return marketPrice;
    }

    public void setMarketPrice(Double marketPrice) {
        this.marketPrice = marketPrice;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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

    public Long getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Long creatorId) {
        this.creatorId = creatorId;
    }

    public Ticket() {

    }

    public Ticket(Boolean free, Integer maxNum) {
        this.id = new ObjectId();
        this.free = free;
        this.maxNum = maxNum;
    }

    public Ticket(String id, Boolean free, Integer maxNum) {
        this.id = new ObjectId(id);
        this.free = free;
        this.maxNum = maxNum;
        this.createTime = System.currentTimeMillis();
        this.updateTime = this.createTime;
    }
}
