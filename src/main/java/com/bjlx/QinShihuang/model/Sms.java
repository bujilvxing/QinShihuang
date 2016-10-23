package com.bjlx.QinShihuang.model;

import org.bson.types.ObjectId;
import org.hibernate.validator.constraints.NotBlank;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

/**
 * 短消息
 * Created by pengyt on 2016/10/23.
 */
@Entity
public class Sms {

    /**
     * 主键
     */
    @Id
    @NotBlank
    private ObjectId id;

    /**
     * 验证码
     */
    private String validationCode;

    /**
     * 账户
     */
    private String account;

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getValidationCode() {
        return validationCode;
    }

    public void setValidationCode(String validationCode) {
        this.validationCode = validationCode;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    /**
     * 构造方法
     * @param validationCode 验证码
     * @param account 账号
     */
    public Sms(String validationCode, String account) {
        this.id = new ObjectId();
        this.validationCode = validationCode;
        this.account = account;
    }
}
