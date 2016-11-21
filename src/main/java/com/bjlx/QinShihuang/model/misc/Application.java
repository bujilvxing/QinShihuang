package com.bjlx.QinShihuang.model.misc;

import com.bjlx.QinShihuang.model.account.PhoneNumber;
import org.bson.types.ObjectId;
import org.hibernate.validator.constraints.NotBlank;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Transient;

import javax.validation.constraints.NotNull;

/**
 * 商家申请
 * Created by xiaozhi on 2016/11/8.
 */
@Entity
public class Application {

    @Transient
    public final static String fd_id = "id";
    @Transient
    public final static String fd_userId = "userId";
    @Transient
    public final static String fd_number = "tel.number";
    @Transient
    public final static String fd_tel = "tel";
    @Transient
    public final static String fd_createTime = "createTime";
    /**
     * 主键
     */
    @NotBlank
    @Id
    private ObjectId id;

    /**
     * 用户id
     */
    @NotNull
    private Long userId;

    /**
     * 手机号
     */
    private PhoneNumber tel;

    /**
     * 创建时间
     */
    private Long createTime;

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

    public PhoneNumber getTel() {
        return tel;
    }

    public void setTel(PhoneNumber tel) {
        this.tel = tel;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Application() {

    }

    public Application(Long userId, PhoneNumber tel) {
        this.id = new ObjectId();
        this.tel = tel;
        this.createTime = System.currentTimeMillis();
        this.userId = userId;
    }
}
