package com.bjlx.QinShihuang.model.misc;

import org.bson.types.ObjectId;
import org.hibernate.validator.constraints.NotBlank;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Transient;

/**
 * Created by pengyt on 2016/8/9.
 * HTML卡片
 */
@Entity
public class Card {

	@Transient
	public final static String fd_id = "id";
	@Transient
	public final static String fd_title = "title";
	@Transient
	public final static String fd_summary = "summary";
	@Transient
	public final static String fd_cover = "cover";
	@Transient
	public final static String fd_thumb = "thumb";
	@Transient
	public final static String fd_detailUrl = "detailUrl";
	
    /**
     * 主键
     */
    @NotBlank
    @Id
    private ObjectId id;

    /**
     * 标题
     */
    private String title;

    /**
     * 摘要
     */
    private String summary;

    /**
     * 封面图
     */
    private ImageItem cover;

    /**
     * 缩略图
     */
    private ImageItem thumb;

    /**
     * 详情链接
     */
    private String detailUrl;

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public ImageItem getCover() {
        return cover;
    }

    public void setCover(ImageItem cover) {
        this.cover = cover;
    }

    public String getDetailUrl() {
        return detailUrl;
    }

    public void setDetailUrl(String detailUrl) {
        this.detailUrl = detailUrl;
    }

    public ImageItem getThumb() {
        return thumb;
    }

    public void setThumb(ImageItem thumb) {
        this.thumb = thumb;
    }
}
