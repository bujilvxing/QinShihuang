package com.bjlx.QinShihuang.requestmodel;

import java.util.List;

/**
 * 问题参数
 * Created by xiaozhi on 2016/11/22.
 */
public class QuestionReq {

    /**
     * 问题来源
     */
    private String source;

    /**
     * 问题的主题
     */
    private List<String> topics;

    /**
     * 问题的标签
     */
    private List<String> tags;

    /**
     * 标题
     */
    String title;

    /**
     * 具体描述
     */
    String content;

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public List<String> getTopics() {
        return topics;
    }

    public void setTopics(List<String> topics) {
        this.topics = topics;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

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
