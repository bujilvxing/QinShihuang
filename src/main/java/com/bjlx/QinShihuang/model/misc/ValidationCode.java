package com.bjlx.QinShihuang.model.misc;

import com.bjlx.QinShihuang.model.account.PhoneNumber;
import org.bson.types.ObjectId;
import org.hibernate.validator.constraints.NotBlank;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

import javax.validation.constraints.NotNull;

/**
 * Created by pengyt on 2016/7/24.
 * 验证码
 */
@Entity
public class ValidationCode {

    public final static String fd_number = "tel.number";
    public final static String fd_dialCode = "tel.dialCode";
    public final static String fd_tel = "tel";
    public final static String fd_email = "email";
    public final static String fd_id = "id";
    public final static String fd_createTime = "createTime";
    public final static String fd_expireTime = "expireTime";
    public final static String fd_action = "action";
    public final static String fd_code = "code";
    public final static String fd_lastSendTime = "lastSendTime";
    public final static String fd_used = "used";
    public final static String fd_resendTime = "resendTime";
    public final static String fd_failCnt = "failCnt";
    
    /**
     * 主键
     */
    @NotBlank
    @Id
    private ObjectId id;

    /**
     * 验证码创建时间
     */
    @NotNull
    private Long createTime;

    /**
     * 验证码过期时间
     */
    @NotNull
    private Long expireTime;

    /**
     * 用于何处。1表示新用户注册，2表示重置密码，3表示绑定手机号，4表示绑定邮箱
     */
    private Integer action;

    /**
     * 验证码内容
     */
    @NotNull
    private String code;

    /**
     * 邮箱接收验证码时的邮箱号
     */
    private String email;

    /**
     * 手机接收验证码时的手机号
     */
    private PhoneNumber tel;

    /**
     * 是否已经验证过
     */
    private boolean used = false;

    /**
     * 上一次发送验证码的时间
     */
    public Long lastSendTime;

    /**
     * 下一次允许发送验证码的时间
     */
    public Long resendTime;

    /**
     * 验证错误次数
     */
    public Integer failCnt = 0;

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Long getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Long expireTime) {
        this.expireTime = expireTime;
    }

    public Integer getAction() {
        return action;
    }

    public void setAction(Integer action) {
        this.action = action;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public PhoneNumber getTel() {
        return tel;
    }

    public void setTel(PhoneNumber tel) {
        this.tel = tel;
    }

    public boolean isUsed() {
        return used;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }

    public Long getLastSendTime() {
        return lastSendTime;
    }

    public void setLastSendTime(Long lastSendTime) {
        this.lastSendTime = lastSendTime;
    }

    public Long getResendTime() {
        return resendTime;
    }

    public void setResendTime(Long resendTime) {
        this.resendTime = resendTime;
    }

    public Integer getFailCnt() {
        return failCnt;
    }

    public void setFailCnt(Integer failCnt) {
        this.failCnt = failCnt;
    }
    
    public ValidationCode() {
    	
    }
    
    public ValidationCode(Long createTime, Long expireTime, String code, String email, Integer action) {
        this.id = new ObjectId();
        this.createTime = createTime;
        this.expireTime = expireTime;
        this.code = code;
        this.email = email;
        this.lastSendTime = createTime;
        this.resendTime = createTime + 60 * 1000L;
        this.action = action;
    }
    
    public ValidationCode(Long createTime, Long expireTime, String code, PhoneNumber tel, Integer action) {
        this.id = new ObjectId();
        this.createTime = createTime;
        this.expireTime = expireTime;
        this.code = code;
        this.tel = tel;
        this.lastSendTime = createTime;
        this.resendTime = createTime + 60 * 1000L;
        this.action = action;
    }
}
