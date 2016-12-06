package com.bjlx.QinShihuang.model.activity;

import com.bjlx.QinShihuang.model.misc.Contact;
import org.mongodb.morphia.annotations.Transient;

/**
 * 参与人
 * Created by xiaozhi on 2016/12/6.
 */
public class Joiner extends Contact {

    @Transient
    public final static String fd_userId = "userId";

    /**
     * 用户id
     */
    private Long userId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
