package com.bjlx.QinShihuang.model.misc;

import org.bson.types.ObjectId;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Transient;

import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;

/**
 * 专栏运营位
 * @author xiaozhi
 *
 */
@Entity
public class Column {

	@Transient
	public final static String fd_id = "id";
	@Transient
	public final static String fd_itemId = "itemId";
	@Transient
	public final static String fd_rank = "rank";
	@Transient
	public final static String fd_columnType = "columnType";
	@Transient
	public final static String fd_itemType = "itemType";
	@Transient
	public final static String fd_title = "title";
	@Transient
	public final static String fd_link = "link";
	@Transient
	public final static String fd_linkType = "linkType";
	@Transient
	public final static String fd_cover = "cover";
	@Transient
	public final static String fd_status = "status";
	@Transient
	public final static String fd_desc = "desc";

	/**
	 * 主键
	 */
	@NotBlank
	@Id
	private ObjectId id = new ObjectId();
	
	/**
	 * 排名
	 */
	@Min(value = 1)
	private Integer rank;
	
	/**
	 * 专栏分类。攻略、酒店、购物等
	 */
	private String itemType;
	
	/**
	 * TODO
	 * 专栏类型，"slide"表示幻灯片专栏，"special"表示特色专栏，这个值还需要敲定
	 */
	private String columnType;
	
	/**
	 * 标题
	 */
	@NotBlank
	@Length(min = 1, max = 256)
	private String title;

	/**
	 * 链接
	 */
	@NotBlank
	@URL
	@Length(min = 1, max = 1024)
	private String link;

	/**
	 * 链接类型。app或者web
	 */
	private String linkType = "app";
	
	/**
	 * 图像
	 */
	private ImageItem cover;

	/**
	 * 专栏状态: 审核中, 已发布
	 */
	@Pattern(regexp = "(review|pub|disabled)")
	private String status;

	/**
	 * 描述
	 */
	private String desc;

	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}

	public Integer getRank() {
		return rank;
	}

	public void setRank(Integer rank) {
		this.rank = rank;
	}

	public String getColumnType() {
		return columnType;
	}

	public void setColumnType(String columnType) {
		this.columnType = columnType;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public ImageItem getCover() {
		return cover;
	}

	public void setCover(ImageItem cover) {
		this.cover = cover;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getLinkType() {
		return linkType;
	}

	public void setLinkType(String linkType) {
		this.linkType = linkType;
	}

	public String getItemType() {
		return itemType;
	}

	public void setItemType(String itemType) {
		this.itemType = itemType;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public Column() {

	}

	public Column(Integer rank, String title, String link, ImageItem cover, String desc) {
		this.id = new ObjectId();
		this.rank = rank;
		this.itemType = "viewspot";
		this.columnType = "special";
		this.title = title;
		this.link = link;
		this.linkType = "app";
		this.cover = cover;
		this.status = "pub";
		this.desc = desc;
	}
}
