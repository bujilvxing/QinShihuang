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
}
