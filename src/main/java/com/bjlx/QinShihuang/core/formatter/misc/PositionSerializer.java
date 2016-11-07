package com.bjlx.QinShihuang.core.formatter.misc;

import java.io.IOException;

import com.bjlx.QinShihuang.model.misc.Position;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class PositionSerializer extends JsonSerializer<Position> {

    @Override
    public void serialize(Position position, JsonGenerator gen, SerializerProvider serializers) {
        try {
            gen.writeStartObject();
            if(position.getName() != null)
            	gen.writeStringField(Position.fd_name, position.getName());
            gen.writeNumberField(Position.fd_lat, position.getLat() == null ? 39.15 : position.getLat());
            gen.writeNumberField(Position.fd_lng, position.getLng() == null ? 116.07: position.getLng());
            if(position.getDesc() != null)
            	gen.writeStringField(Position.fd_desc, position.getDesc());
        	
            gen.writeEndObject();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
