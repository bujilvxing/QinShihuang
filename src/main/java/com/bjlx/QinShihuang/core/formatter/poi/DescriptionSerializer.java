package com.bjlx.QinShihuang.core.formatter.poi;

import java.io.IOException;

import com.bjlx.QinShihuang.model.poi.Description;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class DescriptionSerializer extends JsonSerializer<Description> {

    @Override
    public void serialize(Description description, JsonGenerator gen, SerializerProvider serializers) {
        try {
            gen.writeStartObject();
            if(description.getDesc() != null)
            	gen.writeStringField(Description.fd_desc, description.getDesc());
            if(description.getDetails() != null)
            	gen.writeStringField(Description.fd_details, description.getDetails());
            if(description.getTips() != null)
            	gen.writeStringField(Description.fd_tips, description.getTips());
            if(description.getTraffic() != null)
            	gen.writeStringField(Description.fd_traffic, description.getTraffic());
            gen.writeEndObject();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}