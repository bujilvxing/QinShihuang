package com.bjlx.QinShihuang.requestmodel;

/**
 * 关注用户参数
 * Created by pengyt on 2016/11/8.
 */
public class FollowingReq {

    /**
     * 关注的用户id
     */
    private Long followingId;

    public Long getFollowingId() {
        return followingId;
    }

    public void setFollowingId(Long followingId) {
        this.followingId = followingId;
    }
}
