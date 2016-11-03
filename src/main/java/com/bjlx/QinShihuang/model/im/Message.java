package com.bjlx.QinShihuang.model.im;

import org.bson.types.ObjectId;
import org.hibernate.validator.constraints.NotBlank;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Indexed;
import org.mongodb.morphia.annotations.Transient;

import com.bjlx.QinShihuang.model.misc.ImageItem;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by pengyt on 2016/7/24.
 * 消息
 */
@Entity
public class Message {

	public final static String fd_id = "id";
	public final static String fd_convId = "convId";
	public final static String fd_msgId = "msgId";
	public final static String fd_content = "content";
	public final static String fd_senderId = "senderId";
	public final static String fd_senderNickName = "senderNickName";
	public final static String fd_senderAvatar = "senderAvatar";
	public final static String fd_receiverId = "receiverId";
	public final static String fd_msgType = "msgType";
	public final static String fd_abbrev = "abbrev";
	public final static String fd_timestamp = "timestamp";
	public final static String fd_receiverIdList = "receiverIdList";
	public final static String fd_chatType = "chatType";
	
    /**
     * 主键
     */
    @NotBlank
    @Id
    private ObjectId id;

    /**
     * 所属会话的id
     */
    @Indexed
    private ObjectId convId = new ObjectId();

    /**
     * 消息id
     */
    @NotNull
    @Indexed
    private Long msgId;

    /**
     * 消息内容
     */
    private Content content;

    /**
     * 发送者id
     */
    private Long senderId;

    /**
     * 单聊时，表示接收者用户id。群聊时，表示群组的id
     */
    private Long receiverId;

    /**
     * 发送者昵称
     */
    private String senderNickName;

    /**
     * 发送者头像
     */
    private ImageItem senderAvatar;

    /**
     * 消息类型
     */
    private Integer msgType;

    /**
     * 消息摘要
     */
    @Transient
    private String abbrev;
    
    /**
     * 消息创建时间
     */
    private Long timestamp;

    /**
     * 群聊时，所有接收消息的人的id
     */
    private List<Long> receiverIdList;

    /**
     * 聊天类型
     */
    private Integer chatType;

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public ObjectId getConvId() {
        return convId;
    }

    public void setConvId(ObjectId convId) {
        this.convId = convId;
    }

    public Long getMsgId() {
        return msgId;
    }

    public void setMsgId(Long msgId) {
        this.msgId = msgId;
    }

    public Content getContent() {
        return content;
    }

    public void setContent(Content content) {
        this.content = content;
    }

    public Long getSenderId() {
        return senderId;
    }

    public void setSenderId(Long senderId) {
        this.senderId = senderId;
    }

    public Long getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(Long receiverId) {
        this.receiverId = receiverId;
    }

    public String getSenderNickName() {
        return senderNickName;
    }

    public void setSenderNickName(String senderNickName) {
        this.senderNickName = senderNickName;
    }

    public ImageItem getSenderAvatar() {
        return senderAvatar;
    }

    public void setSenderAvatar(ImageItem senderAvatar) {
        this.senderAvatar = senderAvatar;
    }

    public Integer getMsgType() {
        return msgType;
    }

    public void setMsgType(Integer msgType) {
        this.msgType = msgType;
    }

    public String getAbbrev() {
        return abbrev;
    }

    public void setAbbrev(String abbrev) {
        this.abbrev = abbrev;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public List<Long> getReceiverIdList() {
        return receiverIdList;
    }

    public void setReceiverIdList(List<Long> receiverIdList) {
        this.receiverIdList = receiverIdList;
    }

    public Integer getChatType() {
        return chatType;
    }

    public void setChatType(Integer chatType) {
        this.chatType = chatType;
    }
}
