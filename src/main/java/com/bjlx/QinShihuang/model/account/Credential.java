package com.bjlx.QinShihuang.model.account;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * Created by xiaozhi on 2016/7/24.
 * 盐，密码的密文
 */
@Entity
public class Credential {

    /**
     * 域名称，便于查询，以免输入有误
     */
    @Transient
    public final static String fd_userId = "userId";

    @Transient
    public final static String fd_salt = "salt";

    @Transient
    public final static String fd_passwdHash = "passwdHash";

    /**
     * 主键
     */
    @Id
    private ObjectId id;

    /**
     * 用户id
     */
    @NotNull
    @Min(value = 1)
    @Indexed(options = @IndexOptions(unique = true))
    private Long userId;

    /**
     * 盐，用于生成密码的密文
     */
    private String salt = "";

    /**
     * 用户密码的密文
     */
    @Property("pwdHash")
    private String passwdHash = "";

    /**
     * 加密算法，默认为1
     */
    @Min(1)
    @Max(1)
    private Integer method = 1;

    /**
     * 用户的授权码
     */
    @Embedded
    @NotNull
    private SecretKey secretKey;

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getPasswdHash() {
        return passwdHash;
    }

    public void setPasswdHash(String passwdHash) {
        this.passwdHash = passwdHash;
    }

    public Integer getMethod() {
        return method;
    }

    public void setMethod(Integer method) {
        this.method = method;
    }

    public SecretKey getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(SecretKey secretKey) {
        this.secretKey = secretKey;
    }

    public Credential(Long userId, String salt, String passwdHash, SecretKey secretKey) {
        this.id = new ObjectId();
        this.userId = userId;
        this.salt = salt;
        this.passwdHash = passwdHash;
        this.secretKey = secretKey;
    }
}
