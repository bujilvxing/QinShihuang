package com.bjlx.QinShihuang.model.im;

import org.bson.types.ObjectId;
import org.hibernate.validator.constraints.NotBlank;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Transient;

import javax.validation.constraints.NotNull;

/**
 * Created by pengyt on 2016/7/24.
 * 用户关系。包含关注与被关注，黑名单，备注
 */
@Entity
public class Relationship {

    @Transient
    public final static String fd_userId = "userId";

    @Transient
    public final static String fd_followingId = "followingId";

    @Transient
    public final static String fd_follow = "follow";

    @Transient
    public final static String fd_block = "block";

    @Transient
    public final static String fd_memo = "memo";

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
     * 被关注的用户id
     */
    @NotNull
    private Long followingId;

    /**
     * 是否关注
     */
    private Boolean follow;

    /**
     * 是否拉黑
     */
    private Boolean block;

    /**
     * 备注
     */
    private String memo;

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

    public Long getFollowingId() {
        return followingId;
    }

    public void setFollowingId(Long followingId) {
        this.followingId = followingId;
    }

    public Boolean isFollow() {
        return follow;
    }

    public void setFollow(Boolean follow) {
        this.follow = follow;
    }

    public Boolean getBlock() {
        return block;
    }

    public void setBlock(Boolean block) {
        this.block = block;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public Relationship() {

    }

    public Relationship(Long userId, Long followingId) {
        this.id = new ObjectId();
        this.userId = userId;
        this.followingId = followingId;
    }
}
