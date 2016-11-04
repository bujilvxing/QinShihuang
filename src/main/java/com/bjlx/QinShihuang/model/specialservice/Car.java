package com.bjlx.QinShihuang.model.specialservice;

import com.bjlx.QinShihuang.model.account.RealNameInfo;
import com.bjlx.QinShihuang.model.misc.ImageItem;

import java.util.List;

import org.bson.types.ObjectId;
import org.hibernate.validator.constraints.NotBlank;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Transient;

/**
 * Created by pengyt on 2016/7/25.
 * 汽车信息
 */
@Entity
public class Car {

	@Transient
    public final static String fd_id = "id";
	@Transient
    public final static String fd_carId = "carId";
	@Transient
    public final static String fd_transmission = "transmission";
	@Transient
    public final static String fd_vehicleType = "vehicleType";
	@Transient
    public final static String fd_brand = "brand";
	@Transient
    public final static String fd_carOwner = "carOwner";
	@Transient
    public final static String fd_displacement = "displacement";
	@Transient
    public final static String fd_seatNum = "seatNum";
	@Transient
    public final static String fd_name = "name";
	@Transient
    public final static String fd_fuelType = "fuelType";
	@Transient
    public final static String fd_gasolineType = "gasolineType";
	@Transient
    public final static String fd_actuationType = "actuationType";
	@Transient
    public final static String fd_dormer = "dormer";
	@Transient
    public final static String fd_gps = "gps";
	@Transient
    public final static String fd_seatType = "seatType";
	@Transient
    public final static String fd_airbagNum = "airbagNum";
	@Transient
    public final static String fd_gearboxType = "gearboxType";
	@Transient
    public final static String fd_airConditioner = "airConditioner";
	@Transient
    public final static String fd_cover = "cover";
	@Transient
    public final static String fd_images = "images";

    /**
     * 主键
     */
    @NotBlank
    @Id
    private ObjectId id;

    /**
     * 牌照
     */
    private String carId;

    /**
     * 档。1表示自动挡，2表示手动挡
     */
    private Integer transmission;

    /**
     * 车型
     */
    private String vehicleType;

    /**
     * 品牌
     */
    private String brand;

    /**
     * 车主信息
     */
    private RealNameInfo carOwner;

    /**
     * 排量
     */
    private Float displacement;

    /**
     * 座位数
     */
    private Integer seatNum;

    /**
     * 名称
     */
    private String name;

    /**
     * 燃料。例如：汽油、天然气、电
     */
    private String fuelType;
    
    /**
     * 93号,95号,97号等汽油
     */
    private String gasolineType;

    /**
     * 驱动方式。前驱，后驱，前后驱
     */
    private String actuationType;

    /**
     * 有无天窗
     */
    private Boolean dormer;

    /**
     * GPS导航
     */
    private Boolean gps;

    /**
     * 座椅
     */
    private String seatType;

    /**
     * 气囊数量
     */
    private Integer airbagNum;

    /**
     * 变速箱类型
     */
    private String gearboxType;

    /**
     * 是否有空调
     */
    private Boolean airConditioner;

    private ImageItem cover;
    
    /**
     * 图片列表
     */
    private List<ImageItem> images;
    
    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getCarId() {
        return carId;
    }

    public void setCarId(String carId) {
        this.carId = carId;
    }

    public Integer getTransmission() {
        return transmission;
    }

    public void setTransmission(Integer transmission) {
        this.transmission = transmission;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public RealNameInfo getCarOwner() {
        return carOwner;
    }

    public void setCarOwner(RealNameInfo carOwner) {
        this.carOwner = carOwner;
    }

    public Float getDisplacement() {
        return displacement;
    }

    public void setDisplacement(Float displacement) {
        this.displacement = displacement;
    }

    public Integer getSeatNum() {
        return seatNum;
    }

    public void setSeatNum(Integer seatNum) {
        this.seatNum = seatNum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public String getActuationType() {
        return actuationType;
    }

    public void setActuationType(String actuationType) {
        this.actuationType = actuationType;
    }

    public Boolean isDormer() {
        return dormer;
    }

    public void setDormer(Boolean dormer) {
        this.dormer = dormer;
    }

    public Boolean isGps() {
        return gps;
    }

    public void setGps(Boolean gps) {
        this.gps = gps;
    }

    public String getSeatType() {
        return seatType;
    }

    public void setSeatType(String seatType) {
        this.seatType = seatType;
    }

    public Integer getAirbagNum() {
        return airbagNum;
    }

    public void setAirbagNum(Integer airbagNum) {
        this.airbagNum = airbagNum;
    }

    public String getGearboxType() {
        return gearboxType;
    }

    public void setGearboxType(String gearboxType) {
        this.gearboxType = gearboxType;
    }

    public Boolean isAirConditioner() {
        return airConditioner;
    }

    public void setAirConditioner(Boolean airConditioner) {
        this.airConditioner = airConditioner;
    }

	public String getGasolineType() {
		return gasolineType;
	}

	public void setGasolineType(String gasolineType) {
		this.gasolineType = gasolineType;
	}

	public Boolean getDormer() {
		return dormer;
	}

	public Boolean getGps() {
		return gps;
	}

	public Boolean getAirConditioner() {
		return airConditioner;
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
    
}
