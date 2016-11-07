package com.bjlx.QinShihuang.model.specialservice;

import com.bjlx.QinShihuang.model.misc.Address;
import com.bjlx.QinShihuang.model.misc.Contact;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.bson.types.ObjectId;
import org.hibernate.validator.constraints.NotBlank;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Transient;

/**
 * 租车
 * Created by pengyt on 2016/7/26.
 */
@Entity
public class RentCar {

	@Transient
	public final static String fd_id = "id";
	@Transient
	public final static String fd_price = "price";
	@Transient
	public final static String fd_pickupAddr = "pickupAddr";
	@Transient
	public final static String fd_returnAddr = "returnAddr";
	@Transient
	public final static String fd_contact = "contact";
	@Transient
	public final static String fd_minRentDay = "minRentDay";
	@Transient
	public final static String fd_car = "car";
	@Transient
	public final static String fd_autoInsurance = "autoInsurance";
	@Transient
	public final static String fd_autoInsurancePrice = "autoInsurancePrice";
	@Transient
	public final static String fd_pickup = "pickup";

	/**
	 * 主键
	 */
	@NotBlank
	@Id
	private ObjectId id;
	
    /**
     * 价格
     */
	@Min(value = 0)
    private Integer price;

    /**
     * 取车地点
     */
    private Address pickupAddr;

    /**
     * 还车地点
     */
    private Address returnAddr;

    /**
     * 联系人
     */
    @NotNull
    private Contact contact;

    /**
     * 起租天数
     */
    private Integer minRentDay;

    /**
     * 出租的车辆信息
     */
    @NotNull
    private Car car;

    /**
     * 是否可以买车险
     */
    private Boolean autoInsurance = true;

    /**
     * 车险价格
     */
    @Min(value = 0)
    private Integer autoInsurancePrice;

    /**
     * 是否送车给租客
     */
    private Boolean pickup = false;

    
	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}

	public Boolean getAutoInsurance() {
		return autoInsurance;
	}

	public Boolean getPickup() {
		return pickup;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public Address getPickupAddr() {
		return pickupAddr;
	}

	public void setPickupAddr(Address pickupAddr) {
		this.pickupAddr = pickupAddr;
	}

	public Address getReturnAddr() {
		return returnAddr;
	}

	public void setReturnAddr(Address returnAddr) {
		this.returnAddr = returnAddr;
	}

	public Contact getContact() {
		return contact;
	}

	public void setContact(Contact contact) {
		this.contact = contact;
	}

	public Integer getMinRentDay() {
		return minRentDay;
	}

	public void setMinRentDay(Integer minRentDay) {
		this.minRentDay = minRentDay;
	}

	public Car getCar() {
		return car;
	}

	public void setCar(Car car) {
		this.car = car;
	}

	public Boolean isAutoInsurance() {
		return autoInsurance;
	}

	public void setAutoInsurance(Boolean autoInsurance) {
		this.autoInsurance = autoInsurance;
	}

	public Integer getAutoInsurancePrice() {
		return autoInsurancePrice;
	}

	public void setAutoInsurancePrice(Integer autoInsurancePrice) {
		this.autoInsurancePrice = autoInsurancePrice;
	}

	public Boolean isPickup() {
		return pickup;
	}

	public void setPickup(Boolean pickup) {
		this.pickup = pickup;
	}
}
