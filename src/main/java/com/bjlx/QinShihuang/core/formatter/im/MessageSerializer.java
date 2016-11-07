package com.bjlx.QinShihuang.core.formatter.im;

import java.io.IOException;
import java.util.List;
import com.bjlx.QinShihuang.model.im.Content;
import com.bjlx.QinShihuang.model.im.Message;
import com.bjlx.QinShihuang.model.misc.ImageItem;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class MessageSerializer extends JsonSerializer<Message> {

    @Override
    public void serialize(Message message, JsonGenerator gen, SerializerProvider serializers) {
        try {
            gen.writeStartObject();
            gen.writeStringField(Message.fd_id, message.getId() == null ? "" : message.getId().toString());
            gen.writeStringField(Message.fd_convId, message.getConvId() == null ? "" : message.getConvId().toString());
            gen.writeNumberField(Message.fd_msgId, message.getMsgId() == null ? 0 : message.getMsgId());
            
            Content content = message.getContent();
            if (content != null) {
            	gen.writeFieldName(Message.fd_content);
                JsonSerializer<Object> retContent = serializers.findValueSerializer(Content.class, null);
                retContent.serialize(content, gen, serializers);
            } else {
                gen.writeStartObject();
                gen.writeEndObject();
            }
            
            gen.writeNumberField(Message.fd_senderId, message.getSenderId() == null ? 0 : message.getSenderId());
            gen.writeNumberField(Message.fd_receiverId, message.getReceiverId() == null ? 0 : message.getReceiverId());
            gen.writeStringField(Message.fd_senderNickName, message.getSenderNickName() == null ? "" : message.getSenderNickName());

            gen.writeFieldName(Message.fd_senderAvatar);
            ImageItem senderAvatar = message.getSenderAvatar();
            if (senderAvatar != null) {
                JsonSerializer<Object> retSenderAvatar = serializers.findValueSerializer(ImageItem.class, null);
                retSenderAvatar.serialize(senderAvatar, gen, serializers);
            } else {
                gen.writeStartObject();
                gen.writeEndObject();
            }
            
            gen.writeNumberField(Message.fd_msgType, message.getMsgType() == null ? 0 : message.getMsgType());
            gen.writeStringField(Message.fd_abbrev, message.getAbbrev() == null ? "" : message.getAbbrev());
            gen.writeNumberField(Message.fd_timestamp, message.getTimestamp() == null ? 0L : message.getTimestamp());
            
            gen.writeNumberField(Message.fd_chatType, message.getChatType() == null ? 0 : message.getChatType());

            List<Long> receiverIdList = message.getReceiverIdList();
            if (receiverIdList != null && (!receiverIdList.isEmpty())) {
            	gen.writeFieldName(Message.fd_receiverIdList);
                gen.writeStartArray();
                for (Long receiverId : receiverIdList)
                    gen.writeNumber(receiverId == null ? 0 : receiverId);
                gen.writeEndArray();
            }
            
            gen.writeEndObject();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}