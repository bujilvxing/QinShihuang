package com.bjlx.QinShihuang.core.formatter.im;

import java.io.IOException;
import java.util.List;

import com.bjlx.QinShihuang.model.im.Chatgroup;
import com.bjlx.QinShihuang.model.misc.ImageItem;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

/**
 * 群组序列化
 * @author xiaozhi
 *
 */
public class ChatgroupSerializer extends JsonSerializer<Chatgroup> {

    @Override
    public void serialize(Chatgroup chatgroup, JsonGenerator gen, SerializerProvider serializers) {
        try {
            gen.writeStartObject();
            gen.writeStringField(Chatgroup.fd_id, chatgroup.getId() == null ? "" : chatgroup.getId().toString());
            gen.writeNumberField(Chatgroup.fd_chatGroupId, chatgroup.getChatGroupId() == null ? 0L : chatgroup.getChatGroupId());
            gen.writeStringField(Chatgroup.fd_name, chatgroup.getName() == null ? "不羁旅行群组" : chatgroup.getName());
            if(chatgroup.getGroupDesc() != null)
            	gen.writeStringField(Chatgroup.fd_groupDesc, chatgroup.getGroupDesc());
            
            gen.writeFieldName(Chatgroup.fd_avatar);
            ImageItem avatar = chatgroup.getAvatar();
            if (avatar != null) {
                JsonSerializer<Object> retAvatar = serializers.findValueSerializer(ImageItem.class, null);
                retAvatar.serialize(avatar, gen, serializers);
            } else {
                gen.writeStartObject();
                gen.writeEndObject();
            }
            List<String> tags = chatgroup.getTags();
            if (tags != null && (!tags.isEmpty())) {
            	gen.writeFieldName(Chatgroup.fd_tags);
                gen.writeStartArray();
                for (String tag : tags)
                    gen.writeString(tag == null ? "" : tag);
                gen.writeEndArray();
            }

            gen.writeNumberField(Chatgroup.fd_creator, chatgroup.getCreator() == null ? 0L : chatgroup.getCreator());

            List<Long> admins = chatgroup.getAdmins();
            if (admins != null && (!admins.isEmpty())) {
            	gen.writeFieldName(Chatgroup.fd_admins);
                gen.writeStartArray();
                for (Long admin : admins)
                    gen.writeNumber(admin == null ? 0 : admin);
                gen.writeEndArray();
            }
            
            List<Long> participants = chatgroup.getParticipants();
            if (participants != null && (!participants.isEmpty())) {
            	gen.writeFieldName(Chatgroup.fd_participants);
                gen.writeStartArray();
                for (Long participant : participants)
                    gen.writeNumber(participant == null ? 0 : participant);
                gen.writeEndArray();
            }

            gen.writeNumberField(Chatgroup.fd_maxUsers, chatgroup.getMaxUsers() == null ? 250 : chatgroup.getMaxUsers());
            gen.writeNumberField(Chatgroup.fd_createTime, chatgroup.getCreateTime() == null ? 0L : chatgroup.getCreateTime());
            gen.writeNumberField(Chatgroup.fd_updateTime, chatgroup.getUpdateTime() == null ? 0L : chatgroup.getUpdateTime());
            gen.writeBooleanField(Chatgroup.fd_visible, chatgroup.getVisible() == null ? true : chatgroup.getVisible());
            gen.writeNumberField(Chatgroup.fd_level, chatgroup.getLevel() == null ? 1 : chatgroup.getLevel());

            gen.writeEndObject();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
