package com.bjlx.QinShihuang.requestmodel;

import com.bjlx.QinShihuang.model.im.Content;

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
    private Content content;

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
    private String convId;

    public Long getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(Long receiverId) {
        this.receiverId = receiverId;
    }

    public Content getContent() {
        return content;
    }

    public void setContent(Content content) {
        this.content = content;
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

    public String getConvId() {
        return convId;
    }

    public void setConvId(String convId) {
        this.convId = convId;
    }
}
