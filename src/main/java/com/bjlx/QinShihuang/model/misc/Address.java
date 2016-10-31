package com.bjlx.QinShihuang.model.misc;

import org.mongodb.morphia.annotations.Embedded;

/**
 * 联系地址序列化
 * Created by pengyt on 2016/7/22.
 */
@Embedded
public class Address {

    public final static String fd_province = "province";
    public final static String fd_city = "city";
    public final static String fd_district = "district";
    public final static String fd_detail = "detail";
    public final static String fd_zipCode = "zipCode";

    /**
     * 省份
     */
    private String province;

    /**
     * 城市
     */
    private String city;

    /**
     * 区，县
     */
    private String district;

    /**
     * 详细地址
     */
    private String detail;

    /**
     * 邮编
     */
    private String zipCode;

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }
}
