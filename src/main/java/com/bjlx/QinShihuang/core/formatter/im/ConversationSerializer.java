package com.bjlx.QinShihuang.core.formatter.im;

import java.io.IOException;
import java.util.List;
import com.bjlx.QinShihuang.model.im.Conversation;
import com.bjlx.QinShihuang.utils.Constant;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class ConversationSerializer extends JsonSerializer<Conversation> {

    @Override
    public void serialize(Conversation conversation, JsonGenerator gen, SerializerProvider serializers) {
        try {
            gen.writeStartObject();
            gen.writeStringField(Conversation.fd_id, conversation.getId() == null ? "" : conversation.getId().toString());
            gen.writeNumberField(Conversation.fd_chatType, conversation.getChatType() == null ? Constant.SINGLE_CHAT : conversation.getChatType());
            gen.writeNumberField(Conversation.fd_msgCounter, conversation.getMsgCounter() == null ? 0L : conversation.getMsgCounter());
            gen.writeStringField(Conversation.fd_conversationId, conversation.getConversationId() == null ? "" : conversation.getConversationId());
            gen.writeNumberField(Conversation.fd_createTime, conversation.getCreateTime() == null ? 0L : conversation.getCreateTime());
            gen.writeNumberField(Conversation.fd_updateTime, conversation.getUpdateTime() == null ? 0L : conversation.getUpdateTime());
            gen.writeNumberField(Conversation.fd_unreadCnt, conversation.getUnreadCnt() == null ? 0 : conversation.getUnreadCnt());
            gen.writeStringField(Conversation.fd_lastMsgContent, conversation.getLastMsgContent() == null ? "" : conversation.getLastMsgContent());
            List<Long> muteNotif = conversation.getMuteNotif();
            if (muteNotif != null && (!muteNotif.isEmpty())) {
            	gen.writeFieldName(Conversation.fd_muteNotif);
                gen.writeStartArray();
                for (Long userId : muteNotif)
                    gen.writeNumber(userId == null ? 0L : userId);
                gen.writeEndArray();
            }

            gen.writeBooleanField(Conversation.fd_muted, conversation.getMuted() == null ? false : conversation.getMuted());
            
            List<Long> pinList = conversation.getPinList();
            if (pinList != null && (!pinList.isEmpty())) {
            	gen.writeFieldName(Conversation.fd_pinList);
                gen.writeStartArray();
                for (Long userId : pinList)
                    gen.writeNumber(userId == null ? 0L : userId);
                gen.writeEndArray();
            }
            gen.writeBooleanField(Conversation.fd_pinned, conversation.getPinned() == null ? false : conversation.getPinned());
            gen.writeNumberField(Conversation.fd_targetId, conversation.getTargetId() == null ? 0L : conversation.getTargetId());
            
            gen.writeEndObject();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
