package com.bjlx.QinShihuang.core.formatter.geo;

import java.io.IOException;
import java.util.Set;
import com.bjlx.QinShihuang.model.geo.Locality;
import com.bjlx.QinShihuang.model.misc.ImageItem;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class LocalityBasicSerializer extends JsonSerializer<Locality> {

    @Override
    public void serialize(Locality locality, JsonGenerator gen, SerializerProvider serializers) {
        try {
            gen.writeStartObject();
            
            gen.writeStringField(Locality.fd_id, locality.getId() == null ? "" : locality.getId().toString());
            
            if(locality.getZhName() != null)
            	gen.writeStringField(Locality.fd_zhName, locality.getZhName());
        	
            if(locality.getEnName() != null)
            	gen.writeStringField(Locality.fd_enName, locality.getEnName());

            gen.writeFieldName(Locality.fd_cover);
            ImageItem cover = locality.getCover();
            if (cover != null) {
                JsonSerializer<Object> retCover = serializers.findValueSerializer(ImageItem.class, null);
                retCover.serialize(cover, gen, serializers);
            } else {
                gen.writeStartObject();
                gen.writeEndObject();
            }
           
        	if(locality.getRank() != null)
        		gen.writeNumberField(Locality.fd_rank, locality.getRank());

            Set<String> alias = locality.getAlias();
            if (alias != null && (!alias.isEmpty())) {
            	gen.writeFieldName(Locality.fd_alias);
                gen.writeStartArray();
                for (String alia : alias)
                    gen.writeString(alia == null ? "" : alia);
                gen.writeEndArray();
            }
        	
            gen.writeNumberField(Locality.fd_visitCnt, locality.getVisitCnt() == null ? 0 : locality.getVisitCnt());
            gen.writeNumberField(Locality.fd_commentCnt, locality.getCommentCnt() == null ? 0 : locality.getCommentCnt());
            gen.writeNumberField(Locality.fd_favorCnt, locality.getFavorCnt() == null ? 0 : locality.getFavorCnt());
            gen.writeNumberField(Locality.fd_hotness, locality.getHotness() == null ? 0.0 : locality.getHotness());
            gen.writeNumberField(Locality.fd_rating, locality.getRating() == null ? 0.0 : locality.getRating());
            
            Set<String> tags = locality.getTags();
            if (tags != null && (!tags.isEmpty())) {
            	gen.writeFieldName(Locality.fd_tags);
                gen.writeStartArray();
                for (String tag : tags)
                    gen.writeString(tag == null ? "" : tag);
                gen.writeEndArray();
            }

            gen.writeEndObject();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}