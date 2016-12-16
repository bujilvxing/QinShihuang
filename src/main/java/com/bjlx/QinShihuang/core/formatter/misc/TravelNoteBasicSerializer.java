package com.bjlx.QinShihuang.core.formatter.misc;

import com.bjlx.QinShihuang.model.account.UserInfo;
import com.bjlx.QinShihuang.model.misc.ImageItem;
import com.bjlx.QinShihuang.model.misc.TravelNote;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

public class TravelNoteBasicSerializer extends JsonSerializer<TravelNote> {

    @Override
    public void serialize(TravelNote travelNote, JsonGenerator gen, SerializerProvider serializers) {
        try {
            gen.writeStartObject();

            gen.writeStringField(TravelNote.fd_id, travelNote.getId() == null ? "" : travelNote.getId().toString());
            gen.writeFieldName(TravelNote.fd_cover);
            ImageItem cover = travelNote.getCover();
            if (cover != null) {
                JsonSerializer<Object> retCover = serializers.findValueSerializer(ImageItem.class, null);
                retCover.serialize(cover, gen, serializers);
            } else {
                gen.writeStartObject();
                gen.writeEndObject();
            }

            gen.writeFieldName(TravelNote.fd_author);
            UserInfo author = travelNote.getAuthor();
            if (author != null) {
                JsonSerializer<Object> retUserInfo = serializers.findValueSerializer(UserInfo.class, null);
                retUserInfo.serialize(author, gen, serializers);
            } else {
                gen.writeStartObject();
                gen.writeEndObject();
            }
            
            gen.writeStringField(TravelNote.fd_title, travelNote.getTitle() == null ? "" : travelNote.getTitle());

            gen.writeStringField(TravelNote.fd_summary, travelNote.getSummary() == null ? "" : travelNote.getSummary());
            gen.writeBooleanField(TravelNote.fd_essence, travelNote.isEssence() == null ? false : travelNote.isEssence());
            gen.writeEndObject();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}