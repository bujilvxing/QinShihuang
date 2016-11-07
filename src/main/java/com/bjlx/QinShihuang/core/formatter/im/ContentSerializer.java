package com.bjlx.QinShihuang.core.formatter.im;

import java.io.IOException;

import com.bjlx.QinShihuang.model.im.Content;
import com.bjlx.QinShihuang.model.im.Emoticon;
import com.bjlx.QinShihuang.model.im.Video;
import com.bjlx.QinShihuang.model.misc.Audio;
import com.bjlx.QinShihuang.model.misc.ImageItem;
import com.bjlx.QinShihuang.model.misc.Position;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class ContentSerializer extends JsonSerializer<Content> {

    @Override
    public void serialize(Content content, JsonGenerator gen, SerializerProvider serializers) {
        try {
            gen.writeStartObject();
            if(content.getText() != null)
            	gen.writeStringField(Content.fd_text, content.getText());
            
            
            ImageItem thumb = content.getThumb();
            if (thumb != null) {
            	gen.writeFieldName(Content.fd_thumb);
                JsonSerializer<Object> retThumb = serializers.findValueSerializer(ImageItem.class, null);
                retThumb.serialize(thumb, gen, serializers);
            } 
            
            ImageItem full = content.getFull();
            if (full != null) {
            	gen.writeFieldName(Content.fd_full);
                JsonSerializer<Object> retFull = serializers.findValueSerializer(ImageItem.class, null);
                retFull.serialize(full, gen, serializers);
            } 
        	
            ImageItem origin = content.getOrigin();
            if (origin != null) {
            	gen.writeFieldName(Content.fd_origin);
                JsonSerializer<Object> retOrigin = serializers.findValueSerializer(ImageItem.class, null);
                retOrigin.serialize(origin, gen, serializers);
            } 
        	
            Audio audio = content.getAudio();
            if (audio != null) {
            	gen.writeFieldName(Content.fd_audio);
                JsonSerializer<Object> retAudio = serializers.findValueSerializer(Audio.class, null);
                retAudio.serialize(audio, gen, serializers);
            } 
        	
            Position position = content.getPosition();
            if (position != null) {
            	gen.writeFieldName(Content.fd_position);
                JsonSerializer<Object> retPosition = serializers.findValueSerializer(Position.class, null);
                retPosition.serialize(position, gen, serializers);
            } 
        	
            Emoticon emoticon = content.getEmoticon();
            if (emoticon != null) {
            	gen.writeFieldName(Content.fd_emoticon);
                JsonSerializer<Object> retEmoticon = serializers.findValueSerializer(Emoticon.class, null);
                retEmoticon.serialize(emoticon, gen, serializers);
            }
        	
            Video video = content.getVideo();
            if (video != null) {
            	gen.writeFieldName(Content.fd_video);
                JsonSerializer<Object> retVideo = serializers.findValueSerializer(Video.class, null);
                retVideo.serialize(video, gen, serializers);
            }
        	
            gen.writeEndObject();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
