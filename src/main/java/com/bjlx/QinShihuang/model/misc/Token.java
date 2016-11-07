package com.bjlx.QinShihuang.model.misc;

import org.bson.types.ObjectId;
import org.hibernate.validator.constraints.NotBlank;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Transient;

import javax.validation.constraints.NotNull;
import java.util.UUID;

/**
 * 令牌
 * Created by pengyt on 2016/10/28.
 */
@Entity
public class Token {

	@Transient
	public final static String fd_token = "token";
	@Transient
	public final static String fd_expireTime = "expireTime";
    /**
     * 主键
     */
    @NotBlank
    @Id
    private ObjectId id;

    /**
     * 令牌
     */
    @NotNull
    private String token;

    /**
     * 是否使用过
     */
    private Boolean used = false;

    /**
     * 过期时间
     */
    private Long expireTime;

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Boolean isUsed() {
        return used;
    }

    public void setUsed(Boolean used) {
        this.used = used;
    }

    public Long getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Long expireTime) {
        this.expireTime = expireTime;
    }

    public Token() {
        this.id = new ObjectId();
        this.token = String.format("token::%s", UUID.randomUUID().toString().replaceAll("-", ""));
        this.expireTime = System.currentTimeMillis() + 6 * 60 * 1000L;
    }
}
