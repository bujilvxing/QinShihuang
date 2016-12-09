package com.bjlx.QinShihuang.model.im;

/**
 * Created by xiaozhi on 2016/7/21.
 * 讨论组
 */

import org.bson.types.ObjectId;
import org.hibernate.validator.constraints.NotBlank;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.IndexOptions;
import org.mongodb.morphia.annotations.Indexed;
import org.mongodb.morphia.annotations.Transient;

import com.bjlx.QinShihuang.model.misc.ImageItem;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

/**
 * 群组
 * @author xiaozhi
 *
 */
@Entity
public class Chatgroup {

	/**
     * 域名称，便于查询，以免输入有误
     */
	@Transient
    public final static String fd_id = "id";
	@Transient
    public final static String fd_chatGroupId = "chatGroupId";
	@Transient
    public final static String fd_name = "name";
	@Transient
    public final static String fd_groupDesc = "groupDesc";
	@Transient
    public final static String fd_avatar = "avatar";
	@Transient
    public final static String fd_tags = "tags";
	@Transient
    public final static String fd_creatorId = "creatorId";
	@Transient
    public final static String fd_admins = "admins";
	@Transient
    public final static String fd_maxUsers = "maxUsers";
	@Transient
    public final static String fd_visible = "visible";
	@Transient
    public final static String fd_participants = "participants";
	@Transient
    public final static String fd_level = "level";
	@Transient
    public final static String fd_createTime = "createTime";
	@Transient
    public final static String fd_updateTime = "updateTime";
    @Transient
    public final static String fd_status = "status";

    /**
     * 主键
     */
    @Id
    private ObjectId id = new ObjectId();

    /**
     * 讨论组id
     */
    @NotNull
    @Indexed(options = @IndexOptions(unique = true))
    private Long chatGroupId = 0L;
    
    /**
     * 名称
     */
    @NotBlank
    @Size(min = 2, max = 32)
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
     * 创建者用户id
     */
    @NotNull
    private Long creatorId;
    
    /**
     * 管理员用户id
     */
    private List<Long> admins;

    /**
     * 讨论组成员
     */
    private List<Long> participants;

    /**
     * 讨论组最大允许人数
     */
    @NotNull
    @Min(value = 1)
    @Max(value = 2000)
    private Integer maxUsers = 250;
    
    /**
     * 创建时间
     */
    @NotNull
    private Long createTime = 0L;

    /**
     * 更新时间
     */
    @NotNull
    private Long updateTime = 0L;
    
    /**
     * 是否公开
     */
    @NotNull
    private Boolean visible = true;

    /**
     * 群等级
     */
    private Integer level = 1;

    /**
     * 群组状态
     */
    private Integer status = 1;

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public Long getChatGroupId() {
        return chatGroupId;
    }

    public void setChatGroupId(Long chatGroupId) {
        this.chatGroupId = chatGroupId;
    }

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

    public Long getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Long creatorId) {
        this.creatorId = creatorId;
    }

    public List<Long> getAdmins() {
        return admins;
    }

    public void setAdmins(List<Long> admins) {
        this.admins = admins;
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

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public Boolean getVisible() {
		return visible;
	}

	public void setVisible(Boolean visible) {
		this.visible = visible;
	}

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Chatgroup() {

    }

    public Chatgroup(Long chatGroupId, String name, ImageItem avatar, Long creatorId, Integer maxUsers, Boolean visible) {
        this.id = new ObjectId();
        this.chatGroupId = chatGroupId;
        this.name = name;
        this.avatar = avatar;
        this.creatorId = creatorId;
        this.maxUsers = maxUsers;
        this.createTime = System.currentTimeMillis();
        this.updateTime = System.currentTimeMillis();
        this.visible = visible;
        this.status = 1;
        this.admins = new ArrayList<Long>();
        this.admins.add(creatorId);
    }
}
