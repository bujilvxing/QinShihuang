package com.bjlx.QinShihuang.core.formatter.geo;

import java.io.IOException;
import java.util.List;

import com.bjlx.QinShihuang.model.geo.DetailsEntry;
import com.bjlx.QinShihuang.model.misc.ImageItem;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class DetailsEntrySerializer extends JsonSerializer<DetailsEntry> {

    @Override
    public void serialize(DetailsEntry detailsEntry, JsonGenerator gen, SerializerProvider serializers) {
        try {
            gen.writeStartObject();
            gen.writeFieldName(DetailsEntry.fd_cover);
            ImageItem cover = detailsEntry.getCover();
            if (cover != null) {
                JsonSerializer<Object> retCover = serializers.findValueSerializer(ImageItem.class, null);
                retCover.serialize(cover, gen, serializers);
            } else {
                gen.writeStartObject();
                gen.writeEndObject();
            }
            
            List<ImageItem> images = detailsEntry.getImages();
            gen.writeFieldName(DetailsEntry.fd_images);
            gen.writeStartArray();
            if (images != null && !images.isEmpty()) {
                JsonSerializer<Object> ret = serializers.findValueSerializer(ImageItem.class, null);
                for (ImageItem image : images)
                    ret.serialize(image, gen, serializers);
            }
            gen.writeEndArray();
            
            gen.writeStringField(DetailsEntry.fd_title, detailsEntry.getTitle() == null ? "" : detailsEntry.getTitle());
            gen.writeStringField(DetailsEntry.fd_desc, detailsEntry.getDesc() == null ? "" : detailsEntry.getDesc());
        	
            gen.writeEndObject();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}