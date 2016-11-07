package com.bjlx.QinShihuang.core.formatter.im;

import java.io.IOException;

import com.bjlx.QinShihuang.model.im.Chatgroup;
import com.bjlx.QinShihuang.model.misc.ImageItem;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class ChatgroupBasicSerializer extends JsonSerializer<Chatgroup> {

    @Override
    public void serialize(Chatgroup chatgroup, JsonGenerator gen, SerializerProvider serializers) {
        try {
            gen.writeStartObject();
            gen.writeStringField(Chatgroup.fd_id, chatgroup.getId() == null ? "" : chatgroup.getId().toString());
            gen.writeNumberField(Chatgroup.fd_chatGroupId, chatgroup.getChatGroupId() == null ? 0L : chatgroup.getChatGroupId());
            gen.writeStringField(Chatgroup.fd_name, chatgroup.getName() == null ? "不羁旅行群组" : chatgroup.getName());
            gen.writeFieldName(Chatgroup.fd_avatar);
            ImageItem avatar = chatgroup.getAvatar();
            if (avatar != null) {
                JsonSerializer<Object> retAvatar = serializers.findValueSerializer(ImageItem.class, null);
                retAvatar.serialize(avatar, gen, serializers);
            } else {
                gen.writeStartObject();
                gen.writeEndObject();
            }
            gen.writeEndObject();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
