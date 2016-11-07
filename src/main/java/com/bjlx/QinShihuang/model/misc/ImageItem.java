package com.bjlx.QinShihuang.model.misc;

import org.bson.types.ObjectId;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Transient;

import java.util.Map;

import javax.validation.constraints.Min;

@Embedded
public class ImageItem {

	@Transient
    public static final String fd_url = "url";

    @Transient
    public static final String fd_cropHint = "cropHint";

    @Transient
    public static final String fd_width = "width";

    @Transient
    public static final String fd_height = "height";
    @Transient
    public static final String fd_caption = "caption";

    /**
     * 图像的说明
     */
    private String caption;

    /**
     * 
     */
    private Map<String, Integer> cropHint;

    /**
     * 图像的七牛主键值
     */
    @NotBlank
	@Length(max = 1024)
    private String key;

    /**
     * 图像的七牛空间名称
     */
    @Length(max = 1024)
    private String bucket;

    /**
     * 图像的url
     */
    private String url;

    /**
     * 主键
     */
    private ObjectId id;
    
    /**
	 * 图像宽度
	 */
	@Min(value = 0)
	Integer width;

	/**
	 * 图像高度
	 */
	@Min(value = 0)
	Integer height;

    /**
     * 图像格式
     */
    private String fmt;

    /**
     * 图像色彩模型。
     */
    private String cm;

    /**
     * 图像MD5哈希校验
     */
    private String hash;

    /**
     * 图像文件的大小
     */
    private Integer size;
    

	public String getCaption() {
		return caption;
	}

	public void setCaption(String caption) {
		this.caption = caption;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getBucket() {
		return bucket;
	}

	public void setBucket(String bucket) {
		this.bucket = bucket;
	}

	public Integer getWidth() {
		return width;
	}

	public void setWidth(Integer width) {
		this.width = width;
	}

	public Integer getHeight() {
		return height;
	}

	public void setHeight(Integer height) {
		this.height = height;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	
	public Map<String, Integer> getCropHint() {
		return cropHint;
	}

	public void setCropHint(Map<String, Integer> cropHint) {
		this.cropHint = cropHint;
	}

	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}

	public String getFmt() {
		return fmt;
	}

	public void setFmt(String fmt) {
		this.fmt = fmt;
	}

	public String getCm() {
		return cm;
	}

	public void setCm(String cm) {
		this.cm = cm;
	}

	public String getHash() {
		return hash;
	}

	public void setHash(String hash) {
		this.hash = hash;
	}

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

	public ImageItem() {
		
	}
	
	public ImageItem(String key, String bucket, String url, Integer width, Integer height, String fmt) {
		super();
		this.key = key;
		this.bucket = bucket;
		this.url = url;
		this.id = new ObjectId();
		this.width = width;
		this.height = height;
		this.fmt = fmt;
	}
}
