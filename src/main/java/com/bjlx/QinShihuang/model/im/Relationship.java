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
    public final static String fd_userA = "userA";

    @Transient
    public final static String fd_userB = "userB";

    @Transient
    public final static String fd_followingA = "followingA";

    @Transient
    public final static String fd_followingB = "followingB";

    @Transient
    public final static String fd_blockA = "blockA";

    @Transient
    public final static String fd_blockB = "blockB";

    @Transient
    public final static String fd_memoA = "memoA";

    @Transient
    public final static String fd_memoB = "memoB";

    /**
     * 主键
     */
    @NotBlank
    @Id
    private ObjectId id;

    /**
     * 用户A
     */
    @NotNull
    private Long userA;

    /**
     * 用户B
     */
    @NotNull
    private Long userB;

    /**
     * B关注A
     */
    private Boolean followingA;

    /**
     * A关注B
     */
    private Boolean followingB;

    /**
     * B拉黑A
     */
    private Boolean blockA;

    /**
     * A拉黑B
     */
    private Boolean blockB;

    /**
     * B备注A
     */
    private String memoA;

    /**
     * A备注B
     */
    private String memoB;

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

    public Boolean isFollowingA() {
        return followingA;
    }

    public void setFollowingA(Boolean followingA) {
        this.followingA = followingA;
    }

    public Boolean isFollowingB() {
        return followingB;
    }

    public void setFollowingB(Boolean followingB) {
        this.followingB = followingB;
    }

    public Boolean isBlockA() {
        return blockA;
    }

    public void setBlockA(Boolean blockA) {
        this.blockA = blockA;
    }

    public Boolean isBlockB() {
        return blockB;
    }

    public void setBlockB(Boolean blockB) {
        this.blockB = blockB;
    }

    public String getMemoA() {
        return memoA;
    }

    public void setMemoA(String memoA) {
        this.memoA = memoA;
    }

    public String getMemoB() {
        return memoB;
    }

    public void setMemoB(String memoB) {
        this.memoB = memoB;
    }

    public Relationship() {

    }

    public Relationship(Long userA, Long userB) {
        this.id = new ObjectId();
        this.userA = userA;
        this.userB = userB;
    }
}
