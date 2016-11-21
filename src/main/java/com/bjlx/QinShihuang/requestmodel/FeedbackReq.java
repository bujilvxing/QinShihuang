package com.bjlx.QinShihuang.requestmodel;

/**
 * 反馈参数
 * Created by xiaozhi on 2016/11/8.
 */
public class FeedbackReq {

    /**
     * 反馈内容
     */
    private String content;

    /**
     * 从哪个App反馈过来的, 例如：不羁旅行
     */
    private String origin;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }
}
