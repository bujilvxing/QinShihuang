package com.bjlx.QinShihuang.requestmodel;

import com.bjlx.QinShihuang.model.misc.ImageItem;

/**
 * 收藏参数
 * Created by xiaozhi on 2016/11/16.
 */
public class FavoriteReq {

    /**
     * 收藏的类型。1表示帖子，2表示足迹，3表示形成规划，4表示活动，5表示问答，6表示美食，7表示客栈，8表示游记，9表示景点，10表示购物，11表示商品
     */
    private Integer favoriteType;

    /**
     * 收藏的对象id
     */
    private String itemId;

    /**
     * 作者id
     */
    private Long authorId;

    /**
     * 作者昵称
     */
    private String authorNickName;

    /**
     * 作者头像
     */
    private ImageItem authorAvatar;

    /**
     * 封面
     */
    private ImageItem cover;

    /**
     * 标题
     */
    private String title;

    public Integer getFavoriteType() {
        return favoriteType;
    }

    public void setFavoriteType(Integer favoriteType) {
        this.favoriteType = favoriteType;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public String getAuthorNickName() {
        return authorNickName;
    }

    public void setAuthorNickName(String authorNickName) {
        this.authorNickName = authorNickName;
    }

    public ImageItem getAuthorAvatar() {
        return authorAvatar;
    }

    public void setAuthorAvatar(ImageItem authorAvatar) {
        this.authorAvatar = authorAvatar;
    }

    public ImageItem getCover() {
        return cover;
    }

    public void setCover(ImageItem cover) {
        this.cover = cover;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
