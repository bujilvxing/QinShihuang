package com.bjlx.QinShihuang.requestmodel;

import com.bjlx.QinShihuang.model.misc.ImageItem;

/**
 * 允许更新用户信息
 * Created by xiaozhi on 2016/11/8.
 */
public class UpdateUserInfoReq {

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 头像
     */
    private ImageItem avatar;

    /**
     * 性别
     * 1表示未选择，2表示男，3表示女
     */
    private Integer gender;

    /**
     * 用户签名
     */
    private String signature;

    /**
     * 用户的居住地
     */
    private String residence = null;

    /**
     * 用户的生日
     */
    private Long birthday  = null;

    /**
     * 用户等级
     */
    private Integer level;

    /**
     * 星座
     */
    private Integer zodiac;

    /**
     * 设置声音提醒
     */
    private Boolean soundNotify = true;

    /**
     * 设置振动提醒
     */
    private Boolean vibrateNotify = true;

    /**
     * 背景图片
     */
    private ImageItem backGround;

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public ImageItem getAvatar() {
        return avatar;
    }

    public void setAvatar(ImageItem avatar) {
        this.avatar = avatar;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getResidence() {
        return residence;
    }

    public void setResidence(String residence) {
        this.residence = residence;
    }

    public Long getBirthday() {
        return birthday;
    }

    public void setBirthday(Long birthday) {
        this.birthday = birthday;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getZodiac() {
        return zodiac;
    }

    public void setZodiac(Integer zodiac) {
        this.zodiac = zodiac;
    }

    public Boolean getSoundNotify() {
        return soundNotify;
    }

    public void setSoundNotify(Boolean soundNotify) {
        this.soundNotify = soundNotify;
    }

    public Boolean getVibrateNotify() {
        return vibrateNotify;
    }

    public void setVibrateNotify(Boolean vibrateNotify) {
        this.vibrateNotify = vibrateNotify;
    }

    public ImageItem getBackGround() {
        return backGround;
    }

    public void setBackGround(ImageItem backGround) {
        this.backGround = backGround;
    }
}
