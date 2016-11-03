package com.bjlx.QinShihuang.model.timeline;

import com.bjlx.QinShihuang.model.misc.Card;
import com.bjlx.QinShihuang.model.misc.ImageItem;
import org.bson.types.ObjectId;
import org.hibernate.validator.constraints.NotBlank;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by pengyt on 2016/8/9.
 * 朋友圈
 */
@Entity
public class Moment {

    public final static String fd_id = "id";
    public final static String fd_publishTime = "publishTime";
    public final static String fd_userId = "userId";
    public final static String fd_nickName = "nickName";
    public final static String fd_avatar = "avatar";
    public final static String fd_originId = "originId";
    public final static String fd_originUserId = "originUserId";
    public final static String fd_originNickName = "originNickName";
    public final static String fd_originAvatar = "originAvatar";
    public final static String fd_text = "text";
    public final static String fd_images = "images";
    public final static String fd_comment = "comment";
    public final static String fd_card = "card";

    /**
     * 主键
     */
    @NotBlank
    @Id
    private ObjectId id;

    /**
     * 发布时间
     */
    @NotNull
    private Long publishTime;

    /**
     * 发布朋友圈的用户id
     */
    @NotNull
    private Long userId;

    /**
     * 发布朋友圈的用户昵称
     */
    private String nickName;

    /**
     * 发布朋友圈的头像
     */
    private ImageItem avatar;

    /**
     * 源朋友圈消息的id
     */
    private ObjectId originId;

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
    private Card card;

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public Long getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Long publishTime) {
        this.publishTime = publishTime;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public ImageItem getAvatar() {
        return avatar;
    }

    public void setAvatar(ImageItem avatar) {
        this.avatar = avatar;
    }

    public ObjectId getOriginId() {
        return originId;
    }

    public void setOriginId(ObjectId originId) {
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

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }
}
