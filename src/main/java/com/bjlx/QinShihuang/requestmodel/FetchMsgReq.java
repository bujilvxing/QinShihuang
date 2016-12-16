package com.bjlx.QinShihuang.requestmodel;

/**
 * 拉取消息参数
 * Created by xiaozhi on 2016/11/14.
 */
public class FetchMsgReq {

    /**
     * app最后一条消息的时间
     */
    private Long purgeBefore;

    public Long getPurgeBefore() {
        return purgeBefore;
    }

    public void setPurgeBefore(Long purgeBefore) {
        this.purgeBefore = purgeBefore;
    }
}
