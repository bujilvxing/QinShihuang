package com.bjlx.QinShihuang.core.formatter;

import com.bjlx.QinShihuang.model.trace.Trace;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

/**
 * 足迹序列化
 * Created by xiaozhi on 2016/10/31.
 */
public class TraceSerializer extends JsonSerializer<Trace> {

    @Override
    public void serialize(Trace trace, JsonGenerator gen, SerializerProvider serializers) {
        try {
            gen.writeStartObject();

            gen.writeEndObject();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}