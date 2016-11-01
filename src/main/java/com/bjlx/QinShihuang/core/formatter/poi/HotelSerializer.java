package com.bjlx.QinShihuang.core.formatter.poi;

import java.io.IOException;

import com.bjlx.QinShihuang.model.poi.Hotel;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

/**
 * 旅馆序列化
 * Created by xiaozhi on 2016/10/31.
 */
public class HotelSerializer  extends JsonSerializer<Hotel> {

    @Override
    public void serialize(Hotel hotel, JsonGenerator gen, SerializerProvider serializers) {
	    try {
	        gen.writeStartObject();
	        gen.writeStringField(Hotel.fd_id, hotel.getId() == null ? "" : hotel.getId().toString());
	        
//	        /**
//	    	 * 经度
//	    	 */
//	    	@Min(value = -90)
//	    	@Max(value = 90)
//	    	private Double lat;
//	    	
//	    	/**
//	    	 * 纬度
//	    	 */
//	    	@Min(value = -180)
//	    	@Max(value = 180)
//	    	private Double lng;
//	    	
//	    	/**
//	    	 * 封面图
//	    	 */
//	    	private ImageItem cover;
//	    	
//	    	/**
//	    	 * 图集
//	    	 */
//	    	private List<ImageItem> images;
//	    	
//	    	/**
//	    	 * 排名
//	    	 */
//	    	@Min(value = 1)
//	    	private Integer rank;
//	    	
//	    	/**
//	    	 * 热门程度
//	    	 */
//	    	@Min(value = 0)
//	    	private Double hotness = 0.0;
//	    	
//	    	/**
//	    	 * 评分
//	    	 */
//	    	@Min(value = 0)
//	    	private Double rating = 0.0;
//	    	
//	    	/**
//	    	 * POI联系信息
//	    	 */
//	    	private Contact contact;
//
//	    	/**
//	    	 * POI中文名
//	    	 */
//	    	@NotBlank
//	    	private String zhName;
//	    	
//	    	/**
//	    	 * POI英文名
//	    	 */
//	    	@NotBlank
//	    	private String enName;
//	    	
//	    	/**
//	    	 * POI链接
//	    	 */
//	    	@NotBlank
//	    	private String url;
//
//	    	/**
//	    	 * 市场价
//	    	 */
//	    	private Double marketPrice = 0.0;
//
//	    	/**
//	    	 * POI价格
//	    	 */
//	    	@NotBlank
//	    	private Double price = 0.0;
//	    	
//	    	/**
//	    	 * POI价格描述
//	    	 */
//	    	private String priceDesc;
//	    	
//	    	/**
//	    	 * 开放时间描述
//	    	 */
//	    	private String openTime;
//	    	
//	    	/**
//	    	 * POI描述
//	    	 */
//	    	private Description description;
//	    	
//	    	/**
//	    	 * POI标签
//	    	 */
//	    	private List<String> tags;
//	    	
//	    	/**
//	    	 * POI的别名
//	    	 */
//	    	private List<String> alias;
//	    	
//	    	/**
//	    	 * POI所在的行政区划。
//	    	 */
//	    	private List<String> targets;
//	    	
//	    	/**
//	    	 * 表示该POI的来源。注意：一个POI可以有多个来源。
//	    	 * 示例：
//	    	 * <p>
//	    	 * source: { "baidu": {"url": "foobar", "id": 27384}}
//	    	 */
//	    	private String source;
//	    	
//	    	/**
//	    	 * 旅行指南URL
//	    	 */
//	    	private String guideUrl;
//	    	
//	    	/**
//	    	 * POI地址
//	    	 */
//	    	@NotBlank
//	    	private String address;
//	    	
//	    	/**
//	    	 * 从属行政关系
//	    	 */
//	    	private List<Locality> locList;
//	    	
//	    	/**
//	    	 * 销售量
//	    	 */
//	    	private Integer saleVolume;
//
//	    	/**
//	    	 * 折扣
//	    	 */
//	    	private Float discount;
//	    	
//	    	/**
//	    	 * 租车
//	    	 */
//	    	private RentCar rentCar;
//	    	
//	    	/**
//	    	 * 所在目的地
//	    	 */
//	    	private Locality locality;
//
//	    	/**
//	    	 * 一段时间内可用的房间
//	    	 */
//	    	private List<AvailableDay> availableDays;
	        gen.writeEndObject();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
}