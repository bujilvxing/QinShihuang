package com.bjlx.QinShihuang.core.formatter.misc;

import java.io.IOException;

import com.bjlx.QinShihuang.model.misc.Card;
import com.bjlx.QinShihuang.model.misc.ImageItem;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class CardSerializer extends JsonSerializer<Card> {

    @Override
    public void serialize(Card card, JsonGenerator gen, SerializerProvider serializers) {
        try {
            gen.writeStartObject();
            gen.writeStringField(Card.fd_id, card.getId() == null ? "" : card.getId().toString());
            gen.writeStringField(Card.fd_title, card.getTitle() == null ? "" : card.getTitle());
            gen.writeStringField(Card.fd_summary, card.getSummary() == null ? "" : card.getSummary());
            
            gen.writeFieldName(Card.fd_cover);
            ImageItem cover = card.getCover();
            if (cover != null) {
                JsonSerializer<Object> retCover = serializers.findValueSerializer(ImageItem.class, null);
                retCover.serialize(cover, gen, serializers);
            } else {
            	gen.writeStartObject();
                gen.writeEndObject();
            }
            
            gen.writeFieldName(Card.fd_thumb);
            ImageItem thumb = card.getThumb();
            if (thumb != null) {
                JsonSerializer<Object> retThumb = serializers.findValueSerializer(ImageItem.class, null);
                retThumb.serialize(thumb, gen, serializers);
            } else {
            	gen.writeStartObject();
                gen.writeEndObject();
            }
            gen.writeStringField(Card.fd_detailUrl, card.getDetailUrl() == null ? "" : card.getDetailUrl());
            gen.writeEndObject();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

