package com.bjlx.QinShihuang.requestmodel;

/**
 * 消息参数
 * Created by pengyt on 2016/11/10.
 */
public class MsgReq {

    /**
     * 接受者id
     */
    private Long receiverId;

    /**
     * 消息内容
     */
    private String contents;

    /**
     * 消息类型
     */
    private Integer msgType;

    /**
     * 聊天类型
     */
    private Integer chatType;

    /**
     * 回话id
     */
    private String conversationId;

    public Long getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(Long receiverId) {
        this.receiverId = receiverId;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public Integer getMsgType() {
        return msgType;
    }

    public void setMsgType(Integer msgType) {
        this.msgType = msgType;
    }

    public Integer getChatType() {
        return chatType;
    }

    public void setChatType(Integer chatType) {
        this.chatType = chatType;
    }

    public String getConversationId() {
        return conversationId;
    }

    public void setConversationId(String conversationId) {
        this.conversationId = conversationId;
    }
}
