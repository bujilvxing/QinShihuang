package com.bjlx.QinShihuang.model.misc;

import org.bson.types.ObjectId;
import org.hibernate.validator.constraints.NotBlank;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

import javax.validation.constraints.NotNull;
import java.util.UUID;

/**
 * 令牌
 * Created by pengyt on 2016/10/28.
 */
@Entity
public class Token {

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

    public Token() {
        this.id = new ObjectId();
        this.token = String.format("token::%s", UUID.randomUUID().toString().replaceAll("-", ""));
    }
}
