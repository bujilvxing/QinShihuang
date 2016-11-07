package com.bjlx.QinShihuang.core.formatter.misc;

import java.io.IOException;

import com.bjlx.QinShihuang.model.misc.Audio;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

/**
 * 语音序列化
 * Created by xiaozhi on 2016/10/31.
 */
public class AudioSerializer extends JsonSerializer<Audio> {

    @Override
    public void serialize(Audio audio, JsonGenerator gen, SerializerProvider serializers) {
	    try {
	        gen.writeStartObject();
	        
	        if(audio.getLength() != null) 
	        	gen.writeNumberField(Audio.fd_length, audio.getLength());
	        
	        if(audio.getCreateTime() != null) 
	        	gen.writeNumberField(Audio.fd_createTime, audio.getCreateTime());
	        
	        if(audio.getFileName() != null)
	        	gen.writeStringField(Audio.fd_fileName, audio.getFileName());
	    	
	        gen.writeStringField(Audio.fd_url, audio.getUrl() == null ? "" : audio.getUrl());
	    	
	        gen.writeStringField(Audio.fd_key, audio.getKey() == null ? "" : audio.getKey());
	    	
	        gen.writeEndObject();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
}