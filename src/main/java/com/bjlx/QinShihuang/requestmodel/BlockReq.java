package com.bjlx.QinShihuang.requestmodel;

/**
 * 黑名单
 * Created by xiaozhi on 2016/11/10.
 */
public class BlockReq {

    /**
     * 待屏蔽用户id
     */
    private Long blockId;

    public Long getBlockId() {
        return blockId;
    }

    public void setBlockId(Long blockId) {
        this.blockId = blockId;
    }
}
