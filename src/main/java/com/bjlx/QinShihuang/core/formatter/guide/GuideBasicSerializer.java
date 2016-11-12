package com.bjlx.QinShihuang.core.formatter.guide;

import com.bjlx.QinShihuang.model.guide.Guide;
import com.bjlx.QinShihuang.model.misc.ImageItem;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

/**
 * 攻略基本信息序列化
 * Created by pengyt on 2016/11/12.
 */
public class GuideBasicSerializer extends JsonSerializer<Guide> {

    @Override
    public void serialize(Guide guide, JsonGenerator gen, SerializerProvider serializers) {
        try {
            gen.writeStartObject();
            gen.writeStringField(Guide.fd_id, guide.getId() == null ? "" : guide.getId().toString());

            gen.writeFieldName(Guide.fd_cover);
            ImageItem cover = guide.getCover();
            if (cover != null) {
                JsonSerializer<Object> retCover = serializers.findValueSerializer(ImageItem.class, null);
                retCover.serialize(cover, gen, serializers);
            } else {
                gen.writeStartObject();
                gen.writeEndObject();
            }

            gen.writeNumberField(Guide.fd_updateTime, guide.getUpdateTime() == null ? 0 : guide.getUpdateTime());
            gen.writeStringField(Guide.fd_title, guide.getTitle() == null ? "" : guide.getTitle());

            if(guide.getDesc() != null)
                gen.writeStringField(Guide.fd_desc, guide.getDesc());

            if(guide.getSummary() != null)
                gen.writeStringField(Guide.fd_summary, guide.getSummary());

            if(guide.getDetailUrl() != null)
                gen.writeStringField(Guide.fd_detailUrl, guide.getDetailUrl());

            gen.writeNumberField(Guide.fd_viewCnt, guide.getViewCnt() == null ? 0 : guide.getViewCnt());
            gen.writeNumberField(Guide.fd_shareCnt, guide.getShareCnt() == null ? 0 : guide.getShareCnt());

            gen.writeEndObject();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}