package com.bjlx.QinShihuang.requestmodel;

import com.bjlx.QinShihuang.model.misc.ImageItem;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * 聊天组参数
 * Created by xiaozhi on 2016/12/8.
 */
public class ChatgroupReq {

    /**
     * 聊天组名称
     */
    private String name = "不羁旅行群组";

    /**
     * 描述
     */
    @Size(min = 0, max = 1024)
    private String groupDesc = "畅聊旅行话题";

    /**
     * 头像
     */
    private ImageItem avatar;

    /**
     * 讨论组标签
     */
    List<String> tags;

    /**
     * 讨论组成员
     */
    private List<Long> participants;

    /**
     * 讨论组最大允许人数
     */
    @Min(value = 1)
    @Max(value = 2000)
    private Integer maxUsers = 250;

    /**
     * 是否公开
     */
    private Boolean visible = true;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGroupDesc() {
        return groupDesc;
    }

    public void setGroupDesc(String groupDesc) {
        this.groupDesc = groupDesc;
    }

    public ImageItem getAvatar() {
        return avatar;
    }

    public void setAvatar(ImageItem avatar) {
        this.avatar = avatar;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public List<Long> getParticipants() {
        return participants;
    }

    public void setParticipants(List<Long> participants) {
        this.participants = participants;
    }

    public Integer getMaxUsers() {
        return maxUsers;
    }

    public void setMaxUsers(Integer maxUsers) {
        this.maxUsers = maxUsers;
    }

    public Boolean isVisible() {
        return visible;
    }

    public void setVisible(Boolean visible) {
        this.visible = visible;
    }
}
