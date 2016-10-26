package com.bjlx.QinShihuang.model.specialservice;

import com.bjlx.QinShihuang.model.misc.Address;
import com.bjlx.QinShihuang.model.misc.Contact;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;

/**
 * Created by pengyt on 2016/7/26.
 */
@Entity
public class RentCar {

	/**
	 * 主键
	 */
	private ObjectId id;
	
    /**
     * 价格
     */
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
    private Contact contact;

    /**
     * 起租天数
     */
    private Integer minRentDay;

    /**
     * 出租的车辆信息
     */
    private Car car;

    /**
     * 是否可以买车险
     */
    private Boolean autoInsurance;

    /**
     * 车险价格
     */
    private Integer autoInsurancePrice;

    /**
     * 是否送车给租客
     */
    private Boolean pickup;

    
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
