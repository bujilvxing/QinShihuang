package com.bjlx.QinShihuang.model.misc;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.mongodb.morphia.annotations.Embedded;

/**
 * 位置信息
 * @author xiaozhi
 *
 */
@Embedded
public class Position {

	public final static String fd_name = "name";
	public final static String fd_lat = "lat";
	public final static String fd_lng = "lng";
	public final static String fd_desc = "desc";
	
	/**
	 * 名称
	 */
	private String name;
	
	/**
	 * 经度
	 */
	@Min(value = -90)
	@Max(value = 90)
	private Double lat;
	
	/**
	 * 纬度
	 */
	@Min(value = -180)
	@Max(value = 180)
	private Double lng;
	
	/**
	 * 描述
	 */
	private String desc;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getLat() {
		return lat;
	}

	public void setLat(Double lat) {
		this.lat = lat;
	}

	public Double getLng() {
		return lng;
	}

	public void setLng(Double lng) {
		this.lng = lng;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	
}
