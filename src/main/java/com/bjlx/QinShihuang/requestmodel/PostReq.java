package com.bjlx.QinShihuang.requestmodel;

import com.bjlx.QinShihuang.model.misc.ImageItem;
import org.hibernate.validator.constraints.NotBlank;

import java.util.List;

/**
 * 帖子参数
 * Created by xiaozhi on 2016/12/8.
 */
public class PostReq {

    /**
     * 标题
     */
    private String title;

    /**
     * 封面图
     */
    private ImageItem cover;

    /**
     * 图集
     */
    private List<ImageItem> images;

    /**
     * 帖子摘要
     */
    @NotBlank
    private String summary;

    /**
     * 帖子详情
     */
    private String content;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ImageItem getCover() {
        return cover;
    }

    public void setCover(ImageItem cover) {
        this.cover = cover;
    }

    public List<ImageItem> getImages() {
        return images;
    }

    public void setImages(List<ImageItem> images) {
        this.images = images;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
