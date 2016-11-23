package com.bjlx.QinShihuang.requestmodel;

/**
 * 回答参数
 * Created by xiaozhi on 2016/11/22.
 */
public class AnswerReq {
    /**
     * 标题
     */
    private String title;

    /**
     * 具体描述
     */
    private String content;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
