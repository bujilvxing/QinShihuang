package com.bjlx.QinShihuang.model.misc;

import com.bjlx.QinShihuang.model.guide.Guide;
import org.bson.types.ObjectId;
import org.hibernate.validator.constraints.NotBlank;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Transient;

import java.util.List;

/**
 * 首页指南
 * Created by pengyt on 2016/11/12.
 */
@Entity
public class ColumnGuide {

    @Transient
    public final static String fd_id = "id";
    @Transient
    public final static String fd_guideIds = "guideIds";

    /**
     * 主键
     */
    @NotBlank
    @Id
    private ObjectId id;

    /**
     * 攻略列表
     */
    private List<ObjectId> guideIds;

    /**
     * 攻略列表
     */
    @Transient
    private List<Guide> guides;

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public List<ObjectId> getGuideIds() {
        return guideIds;
    }

    public void setGuideIds(List<ObjectId> guideIds) {
        this.guideIds = guideIds;
    }

    public List<Guide> getGuides() {
        return guides;
    }

    public void setGuides(List<Guide> guides) {
        this.guides = guides;
    }
}
