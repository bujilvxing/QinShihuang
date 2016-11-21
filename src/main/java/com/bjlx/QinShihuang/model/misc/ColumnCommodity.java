package com.bjlx.QinShihuang.model.misc;

import com.bjlx.QinShihuang.model.marketplace.Commodity;
import org.bson.types.ObjectId;
import org.hibernate.validator.constraints.NotBlank;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Transient;

import java.util.List;

/**
 * 首页商品
 * Created by pengyt on 2016/11/12.
 */
@Entity
public class ColumnCommodity {

    @Transient
    public final static String fd_id = "id";
    @Transient
    public final static String fd_category = "category";
    @Transient
    public final static String fd_commodityIds = "commodityIds";
    @Transient
    public final static String fd_commodities = "commodities";

    /**
     * 主键
     */
    @NotBlank
    @Id
    private ObjectId id;

    /**
     * 商品模块分类
     */
    private String category;

    /**
     * 商品列表的id
     */
    private List<ObjectId> commodityIds;

    /**
     * 商品列表
     */
    @Transient
    private List<Commodity> commodities;

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public List<ObjectId> getCommodityIds() {
        return commodityIds;
    }

    public void setCommodityIds(List<ObjectId> commodityIds) {
        this.commodityIds = commodityIds;
    }

    public List<Commodity> getCommodities() {
        return commodities;
    }

    public void setCommodities(List<Commodity> commodities) {
        this.commodities = commodities;
    }
}
