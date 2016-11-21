package com.bjlx.QinShihuang.core.formatter.im;

import com.bjlx.QinShihuang.model.im.Conversation;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

public class ConversationSerializer extends JsonSerializer<Conversation> {

    @Override
    public void serialize(Conversation conversation, JsonGenerator gen, SerializerProvider serializers) {
        try {
            gen.writeStartObject();
            gen.writeStringField(Conversation.fd_id, conversation.getId() == null ? "" : conversation.getId().toString());
            gen.writeNumberField(Conversation.fd_createTime, conversation.getCreateTime() == null ? 0L : conversation.getCreateTime());
            gen.writeNumberField(Conversation.fd_updateTime, conversation.getUpdateTime() == null ? 0L : conversation.getUpdateTime());
            gen.writeStringField(Conversation.fd_lastMsgContent, conversation.getLastMsgContent() == null ? "" : conversation.getLastMsgContent());
            gen.writeBooleanField(Conversation.fd_muted, conversation.getMuted() == null ? false : conversation.getMuted());
            gen.writeBooleanField(Conversation.fd_pinned, conversation.getPinned() == null ? false : conversation.getPinned());
            gen.writeEndObject();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
