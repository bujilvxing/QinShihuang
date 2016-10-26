package com.bjlx.QinShihuang.model.im;

import org.bson.types.ObjectId;
import org.hibernate.validator.constraints.NotBlank;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

import javax.validation.constraints.NotNull;

/**
 * Created by pengyt on 2016/7/24.
 * 用户关系。包含关注与被关注，黑名单，备注
 */
@Entity
public class Relationship {

    /**
     * 主键
     */
    @NotBlank
    @Id
    private ObjectId id;

    /**
     * 用户A的用户id
     */
    @NotNull
    private Long userA;

    /**
     * 用户B的用户id
     */
    @NotNull
    private Long userB;

    /**
     * 用户A关注用户B
     */
    private Integer follow;

    /**
     * 用户A将用户B拉黑
     */
    private Integer block;

    /**
     * 用户A备注用户B
     */
    private String memo;

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public Long getUserA() {
        return userA;
    }

    public void setUserA(Long userA) {
        this.userA = userA;
    }

    public Long getUserB() {
        return userB;
    }

    public void setUserB(Long userB) {
        this.userB = userB;
    }

    public Integer getFollow() {
        return follow;
    }

    public void setFollow(Integer follow) {
        this.follow = follow;
    }

    public Integer getBlock() {
        return block;
    }

    public void setBlock(Integer block) {
        this.block = block;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public Relationship(Long userA, Long userB) {
        this.id = new ObjectId();
        this.userA = userA;
        this.userB = userB;
    }
}
