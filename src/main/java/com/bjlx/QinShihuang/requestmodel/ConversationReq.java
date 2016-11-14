package com.bjlx.QinShihuang.requestmodel;

import java.util.List;

/**
 * 回话参数
 * Created by pengyt on 2016/11/15.
 */
public class ConversationReq {

    private Boolean mute;

    private List<String> ids;

    public Boolean isMute() {
        return mute;
    }

    public void setMute(Boolean mute) {
        this.mute = mute;
    }

    public List<String> getIds() {
        return ids;
    }

    public void setIds(List<String> ids) {
        this.ids = ids;
    }
}
