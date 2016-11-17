package com.bjlx.QinShihuang.requestmodel;

/**
 * Created by xiaozhi on 2016/11/17.
 */
public class VoteReq {

    /**
     * 点赞类型
     */
    private Integer voteType;

    /**
     * 点赞对象的id
     */
    private String itemId;


    public Integer getVoteType() {
        return voteType;
    }

    public void setVoteType(Integer voteType) {
        this.voteType = voteType;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

}
