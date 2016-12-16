package com.bjlx.QinShihuang.core.formatter.activity;

import java.io.IOException;

import com.bjlx.QinShihuang.model.activity.Activity;
import com.bjlx.QinShihuang.model.misc.ImageItem;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class ActivityBasicSerializer extends JsonSerializer<Activity> {

    @Override
    public void serialize(Activity activity, JsonGenerator gen, SerializerProvider serializers) {
        try {
            gen.writeStartObject();
            gen.writeStringField(Activity.fd_id, activity.getId() == null ? "" : activity.getId().toString());
            gen.writeStringField(Activity.fd_title, activity.getTitle() == null ? "" : activity.getTitle());
            if(activity.getMaxNum() != null)
                gen.writeNumberField(Activity.fd_maxNum, activity.getMaxNum());

            gen.writeNumberField(Activity.fd_joinNum, activity.getJoinNum() == null ? 0 : activity.getJoinNum());

            gen.writeNumberField(Activity.fd_favorCnt, activity.getFavorCnt() == null ? 0 : activity.getFavorCnt());

            gen.writeNumberField(Activity.fd_commentCnt, activity.getCommentCnt() == null ? 0 : activity.getCommentCnt());

            gen.writeNumberField(Activity.fd_viewCnt, activity.getViewCnt() == null ? 0 : activity.getViewCnt());

            gen.writeNumberField(Activity.fd_shareCnt, activity.getShareCnt() == null ? 0 : activity.getShareCnt());

            gen.writeNumberField(Activity.fd_voteCnt, activity.getVoteCnt() == null ? 0 : activity.getVoteCnt());

            gen.writeFieldName(Activity.fd_cover);
            ImageItem cover = activity.getCover();
            if (cover != null) {
                JsonSerializer<Object> retCover = serializers.findValueSerializer(ImageItem.class, null);
                retCover.serialize(cover, gen, serializers);
            } else {
                gen.writeStartObject();
                gen.writeEndObject();
            }
            gen.writeBooleanField(Activity.fd_isFree, activity.getIsFree() == null ? true : activity.getIsFree());
            gen.writeEndObject();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}