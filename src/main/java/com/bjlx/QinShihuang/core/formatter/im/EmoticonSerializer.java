package com.bjlx.QinShihuang.core.formatter.im;

import java.io.IOException;

import com.bjlx.QinShihuang.model.im.Emoticon;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class EmoticonSerializer extends JsonSerializer<Emoticon> {

    @Override
    public void serialize(Emoticon emoticon, JsonGenerator gen, SerializerProvider serializers) {
        try {
            gen.writeStartObject();
            gen.writeStringField(Emoticon.fd_group, emoticon.getGroup() == null ? "" : emoticon.getGroup());
            gen.writeStringField(Emoticon.fd_url, emoticon.getUrl() == null ? "" : emoticon.getUrl());
            gen.writeEndObject();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}