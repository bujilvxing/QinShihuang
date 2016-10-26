package com.bjlx.QinShihuang.model.poi;

import com.bjlx.QinShihuang.model.geo.Locality;
import com.bjlx.QinShihuang.model.misc.Address;
import com.bjlx.QinShihuang.model.misc.Contact;
import com.bjlx.QinShihuang.model.misc.ImageItem;
import org.bson.types.ObjectId;
import org.hibernate.validator.constraints.NotBlank;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.List;

@Entity
public class Restaurant {

	/**
	 * 主键
	 */
	@NotBlank
	@Id
	private ObjectId id = null;
	
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
	 * 封面图
	 */
	private ImageItem cover;
	
	/**
	 * 图集
	 */
	private List<ImageItem> images;
	
	/**
	 * 排名
	 */
	@Min(value = 1)
	private Integer rank;
	
	/**
	 * 热门程度
	 */
	@Min(value = 0)
	private Double hotness = 0.0;
	
	/**
	 * 评分
	 */
	@Min(value = 0)
	private Double rating = 0.0;
	
	/**
	 * POI联系信息
	 */
	private Contact contact;

	/**
	 * POI中文名
	 */
	@NotBlank
	private String zhName;
	
	/**
	 * POI英文名
	 */
	@NotBlank
	private String enName;
	
	/**
	 * POI链接
	 */
	@NotBlank
	private String url;
	
	/**
	 * POI价格
	 */
	@NotBlank
	private Double price = 0.0;
	
	/**
	 * POI价格描述
	 */
	private String priceDesc;

	/**
	 * 开放时间描述
	 */
	private String openTime;
	
	/**
	 * POI描述
	 */
	private Description description;
	
	/**
	 * POI标签
	 */
	private List<String> tags;
	
	/**
	 * POI的别名
	 */
	private List<String> alias;
	
	/**
	 * POI所在的行政区划。
	 */
	private List<String> targets;
	
	/**
	 * 表示该POI的来源。注意：一个POI可以有多个来源。
	 * 示例：
	 * <p>
	 * source: { "baidu": {"url": "foobar", "id": 27384}}
	 */
	private String source;
	
	/**
	 * 旅行指南URL
	 */
	private String guideUrl;
	
	/**
	 * POI地址
	 */
	@NotBlank
	private Address address;
	
	/**
	 * 从属行政关系
	 */
	private List<Locality> locList;
	
	/**
	 * 所在目的地
	 */
	private Locality locality;

	/**
	 * 销量
	 */
	private Integer saleVolume;

	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
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

	public Integer getRank() {
		return rank;
	}

	public void setRank(Integer rank) {
		this.rank = rank;
	}

	public Double getHotness() {
		return hotness;
	}

	public void setHotness(Double hotness) {
		this.hotness = hotness;
	}

	public Double getRating() {
		return rating;
	}

	public void setRating(Double rating) {
		this.rating = rating;
	}

	public Contact getContact() {
		return contact;
	}

	public void setContact(Contact contact) {
		this.contact = contact;
	}

	public String getZhName() {
		return zhName;
	}

	public void setZhName(String zhName) {
		this.zhName = zhName;
	}

	public String getEnName() {
		return enName;
	}

	public void setEnName(String enName) {
		this.enName = enName;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getPriceDesc() {
		return priceDesc;
	}

	public void setPriceDesc(String priceDesc) {
		this.priceDesc = priceDesc;
	}

	public String getOpenTime() {
		return openTime;
	}

	public void setOpenTime(String openTime) {
		this.openTime = openTime;
	}

	public Description getDescription() {
		return description;
	}

	public void setDescription(Description description) {
		this.description = description;
	}

	public List<String> getTags() {
		return tags;
	}

	public void setTags(List<String> tags) {
		this.tags = tags;
	}

	public List<String> getAlias() {
		return alias;
	}

	public void setAlias(List<String> alias) {
		this.alias = alias;
	}

	public List<String> getTargets() {
		return targets;
	}

	public void setTargets(List<String> targets) {
		this.targets = targets;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getGuideUrl() {
		return guideUrl;
	}

	public void setGuideUrl(String guideUrl) {
		this.guideUrl = guideUrl;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public List<Locality> getLocList() {
		return locList;
	}

	public void setLocList(List<Locality> locList) {
		this.locList = locList;
	}

	public Locality getLocality() {
		return locality;
	}

	public void setLocality(Locality locality) {
		this.locality = locality;
	}

	public Integer getSaleVolume() {
		return saleVolume;
	}

	public void setSaleVolume(Integer saleVolume) {
		this.saleVolume = saleVolume;
	}
}
