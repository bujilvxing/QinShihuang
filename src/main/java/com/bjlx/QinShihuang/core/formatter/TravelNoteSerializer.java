package com.bjlx.QinShihuang.core.formatter;

import com.bjlx.QinShihuang.model.misc.ImageItem;
import com.bjlx.QinShihuang.model.misc.TravelNote;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * 游记序列化
 * Created by xiaozhi on 2016/10/31.
 */
public class TravelNoteSerializer extends JsonSerializer<TravelNote> {

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

            List<ImageItem> images = travelNote.getImages();
            gen.writeFieldName(TravelNote.fd_images);
            gen.writeStartArray();
            if (images != null && !images.isEmpty()) {
                JsonSerializer<Object> ret = serializers.findValueSerializer(ImageItem.class, null);
                for (ImageItem image : images)
                    ret.serialize(image, gen, serializers);
            }
            gen.writeEndArray();

            if(travelNote.getRating() != null)
                gen.writeNumberField(TravelNote.fd_rating, travelNote.getRating());

            if(travelNote.getHotness() != null)
                gen.writeNumberField(TravelNote.fd_hotness, travelNote.getHotness());

            gen.writeStringField(TravelNote.fd_title, travelNote.getTitle() == null ? "" : travelNote.getTitle());

            if(travelNote.getPublishTime() != null)
                gen.writeNumberField(TravelNote.fd_publishTime, travelNote.getPublishTime());

            if(travelNote.getFavorCnt() != null)
                gen.writeNumberField(TravelNote.fd_favorCnt, travelNote.getFavorCnt());

            if(travelNote.getCommentCnt() != null)
                gen.writeNumberField(TravelNote.fd_commentCnt, travelNote.getCommentCnt());

            if(travelNote.getViewCnt() != null)
                gen.writeNumberField(TravelNote.fd_viewCnt, travelNote.getViewCnt());

            if(travelNote.getShareCnt() != null)
                gen.writeNumberField(TravelNote.fd_shareCnt, travelNote.getShareCnt());

            if(travelNote.getTravelTime() != null)
                gen.writeNumberField(TravelNote.fd_travelTime, travelNote.getTravelTime());

            gen.writeStringField(TravelNote.fd_summary, travelNote.getSummary() == null ? "" : travelNote.getSummary());

            gen.writeFieldName(TravelNote.fd_contents);
            List<Map<String, String>> contents = travelNote.getContents();
            gen.writeStartArray();
            if (contents != null && !contents.isEmpty()) {
                for (Map<String, String> cn : contents) {
                    gen.writeStartObject();
                    for (Map.Entry<String, String> entry : cn.entrySet()) {
                        gen.writeStringField(entry.getKey(), entry.getValue() == null ? "" : entry.getValue());
                    }
                    gen.writeEndObject();
                }
            }
            gen.writeEndArray();

            gen.writeStringField(TravelNote.fd_source, travelNote.getSource() == null ? "" : travelNote.getSource());
            gen.writeBooleanField(TravelNote.fd_essence, travelNote.isEssence() == null ? false : travelNote.isEssence());
            gen.writeEndObject();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}