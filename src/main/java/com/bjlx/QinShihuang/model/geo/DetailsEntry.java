package com.bjlx.QinShihuang.model.geo;

import com.bjlx.QinShihuang.model.misc.ImageItem;
import org.hibernate.validator.constraints.NotBlank;
import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Transient;

import java.util.List;

/**
 * 详情介绍，包含标题、描述和图集
 *
 * Created by pengyt on 2015/10/19.
 */
@Embedded
public class DetailsEntry {

	@Transient
	public final static String fd_cover = "cover";
	@Transient
	public final static String fd_images = "images";
	@Transient
	public final static String fd_title = "title";
	@Transient
	public final static String fd_desc = "desc";
	/**
	 * 封面图
	 */
	private ImageItem cover;

	/**
	 * 图集
	 */
	private List<ImageItem> images;
	
	/**
	 * 标题
	 */
	@NotBlank
	private String title;

	/**
	 * 描述
	 */
	@NotBlank
	private String desc;

	public ImageItem getCover() {
		return cover;
	}

	public void setCover(ImageItem cover) {
		this.cover = cover;
	}

	public List<ImageItem> getImages() {
		return images;
	}

	public void setImages(List<ImageItem> images) {
		this.images = images;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public DetailsEntry() {

	}

	public DetailsEntry(ImageItem cover, List<ImageItem> images, String title, String desc) {
		this.cover = cover;
		this.images = images;
		this.title = title;
		this.desc = desc;
	}
}
