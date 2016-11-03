package com.bjlx.QinShihuang.core.formatter.trace;

import java.io.IOException;
import com.bjlx.QinShihuang.model.misc.Audio;
import com.bjlx.QinShihuang.model.misc.ImageItem;
import com.bjlx.QinShihuang.model.trace.Trace;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class TraceBasicSerializer extends JsonSerializer<Trace> {

    @Override
    public void serialize(Trace trace, JsonGenerator gen, SerializerProvider serializers) {
        try {
            gen.writeStartObject();
            gen.writeStringField(Trace.fd_id, trace.getId() == null ? "" : trace.getId().toString());
            gen.writeNumberField(Trace.fd_userId, trace.getUserId() == null ? 0 : trace.getUserId());
            gen.writeStringField(Trace.fd_nickName, trace.getNickName() == null ? "" : trace.getNickName());
            gen.writeFieldName(Trace.fd_avatar);
            ImageItem avatar = trace.getAvatar();
            if (avatar != null) {
                JsonSerializer<Object> retAvatar = serializers.findValueSerializer(ImageItem.class, null);
                retAvatar.serialize(avatar, gen, serializers);
            } else {
                gen.writeStartObject();
                gen.writeEndObject();
            }

            gen.writeFieldName(Trace.fd_cover);
            ImageItem cover = trace.getCover();
            if (cover != null) {
                JsonSerializer<Object> retCover = serializers.findValueSerializer(ImageItem.class, null);
                retCover.serialize(cover, gen, serializers);
            } else {
                gen.writeStartObject();
                gen.writeEndObject();
            }

            Audio audio = trace.getAudio();
            if (avatar != null) {
            	gen.writeFieldName(Trace.fd_audio);
                JsonSerializer<Object> retAudio = serializers.findValueSerializer(Audio.class, null);
                retAudio.serialize(audio, gen, serializers);
            }
            gen.writeStringField(Trace.fd_title, trace.getTitle() == null ? "" : trace.getTitle());
            
            if(trace.getOriginId() != null)
            	gen.writeStringField(Trace.fd_originId, trace.getOriginId().toString());
            
            if(trace.getOriginUserId() != null)
            	gen.writeNumberField(Trace.fd_originUserId, trace.getOriginUserId());

            if(trace.getOriginNickName() != null)
            	gen.writeStringField(Trace.fd_originNickName, trace.getOriginNickName());
           
            ImageItem originAvatar = trace.getOriginAvatar();
            if (originAvatar != null) {
            	gen.writeFieldName(Trace.fd_originAvatar);
                JsonSerializer<Object> retOriginAvatar = serializers.findValueSerializer(ImageItem.class, null);
                retOriginAvatar.serialize(originAvatar, gen, serializers);
            }
            
            gen.writeEndObject();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}