package com.bjlx.QinShihuang.requestmodel;

import com.bjlx.QinShihuang.model.misc.ImageItem;

import java.util.List;

/**
 * 时间线参数
 * Created by xiaozhi on 2016/11/21.
 */
public class MomentReq {

    /**
     * 源朋友圈消息的id
     */
    private String originId;

    /**
     * 源朋友圈消息的发布者用户id
     */
    private Long originUserId;

    /**
     * 源朋友圈消息的发布者用户昵称
     */
    private String originNickName;

    /**
     * 源朋友圈消息的发布者用户头像
     */
    private ImageItem originAvatar;

    /**
     * 文字消息
     */
    private String text;

    /**
     * 图片
     */
    private List<ImageItem> images;

    /**
     * 自己评论
     */
    private String comment;

    /**
     * HTML卡片信息
     */
    private CardReq card;

    public String getOriginId() {
        return originId;
    }

    public void setOriginId(String originId) {
        this.originId = originId;
    }

    public Long getOriginUserId() {
        return originUserId;
    }

    public void setOriginUserId(Long originUserId) {
        this.originUserId = originUserId;
    }

    public String getOriginNickName() {
        return originNickName;
    }

    public void setOriginNickName(String originNickName) {
        this.originNickName = originNickName;
    }

    public ImageItem getOriginAvatar() {
        return originAvatar;
    }

    public void setOriginAvatar(ImageItem originAvatar) {
        this.originAvatar = originAvatar;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<ImageItem> getImages() {
        return images;
    }

    public void setImages(List<ImageItem> images) {
        this.images = images;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public CardReq getCard() {
        return card;
    }

    public void setCard(CardReq card) {
        this.card = card;
    }
}
