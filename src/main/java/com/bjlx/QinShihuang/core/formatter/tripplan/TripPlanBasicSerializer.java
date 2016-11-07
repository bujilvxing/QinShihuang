package com.bjlx.QinShihuang.core.formatter.tripplan;

import com.bjlx.QinShihuang.model.misc.ImageItem;
import com.bjlx.QinShihuang.model.tripplan.TripPlan;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

public class TripPlanBasicSerializer extends JsonSerializer<TripPlan> {

    @Override
    public void serialize(TripPlan tripPlan, JsonGenerator gen, SerializerProvider serializers) {
        try {
            gen.writeStartObject();
            gen.writeStringField(TripPlan.fd_id, tripPlan.getId() == null ? "" : tripPlan.getId().toString());
            gen.writeNumberField(TripPlan.fd_userId, tripPlan.getUserId() == null ? 0 : tripPlan.getUserId());
            gen.writeStringField(TripPlan.fd_nickName, tripPlan.getNickName() == null ? "" : tripPlan.getNickName());

            ImageItem avatar = tripPlan.getAvatar();
            if (avatar != null) {
                JsonSerializer<Object> retAvatar = serializers.findValueSerializer(ImageItem.class, null);
                retAvatar.serialize(avatar, gen, serializers);
            } else {
                gen.writeStartObject();
                gen.writeEndObject();
            }

            gen.writeStringField(TripPlan.fd_title, tripPlan.getTitle() == null ? "" : tripPlan.getTitle());
            gen.writeFieldName(TripPlan.fd_cover);
            ImageItem cover = tripPlan.getCover();
            if (cover != null) {
                JsonSerializer<Object> retCover = serializers.findValueSerializer(ImageItem.class, null);
                retCover.serialize(cover, gen, serializers);
            } else {
                gen.writeStartObject();
                gen.writeEndObject();
            }

            if(tripPlan.getOriginId() != null)
                gen.writeStringField(TripPlan.fd_originId, tripPlan.getOriginId().toString());

            if(tripPlan.getOriginUserId() != null)
                gen.writeNumberField(TripPlan.fd_originUserId, tripPlan.getOriginUserId());

            if(tripPlan.getOriginNickName() != null)
                gen.writeStringField(TripPlan.fd_originNickName, tripPlan.getOriginNickName());

            ImageItem originAvatar = tripPlan.getOriginAvatar();
            if (originAvatar != null) {
                gen.writeFieldName(TripPlan.fd_originAvatar);
                JsonSerializer<Object> retOriginAvatar = serializers.findValueSerializer(ImageItem.class, null);
                retOriginAvatar.serialize(originAvatar, gen, serializers);
            }

            gen.writeEndObject();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
