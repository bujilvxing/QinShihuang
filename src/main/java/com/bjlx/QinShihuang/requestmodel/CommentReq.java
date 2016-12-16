package com.bjlx.QinShihuang.requestmodel;

import com.bjlx.QinShihuang.model.misc.ImageItem;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 评论参数
 * Created by xiaozhi on 2016/12/12.
 */
public class CommentReq {

    /**
     * 评价的详情
     */
    @NotBlank
    private String contents;

    /**
     * 评论类型
     */
    @NotNull
    private Integer commentType;

    /**
     * 评论的对象id
     */
    @NotNull
    private String itemId;

    /**
     * 评论中附带的照片
     */
    private List<ImageItem> images;

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public Integer getCommentType() {
        return commentType;
    }

    public void setCommentType(Integer commentType) {
        this.commentType = commentType;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public List<ImageItem> getImages() {
        return images;
    }

    public void setImages(List<ImageItem> images) {
        this.images = images;
    }
}
