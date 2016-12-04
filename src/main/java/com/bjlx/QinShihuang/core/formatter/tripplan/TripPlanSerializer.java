package com.bjlx.QinShihuang.core.formatter.tripplan;

import com.bjlx.QinShihuang.model.misc.ImageItem;
import com.bjlx.QinShihuang.model.tripplan.TripItem;
import com.bjlx.QinShihuang.model.tripplan.TripPlan;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.util.List;

/**
 * 行程规划序列化器
 * Created by xiaozhi on 2016/10/31.
 */
public class TripPlanSerializer extends JsonSerializer<TripPlan> {

    @Override
    public void serialize(TripPlan tripPlan, JsonGenerator gen, SerializerProvider serializers) {
        try {
            gen.writeStartObject();
            gen.writeStringField(TripPlan.fd_id, tripPlan.getId() == null ? "" : tripPlan.getId().toString());
            gen.writeNumberField(TripPlan.fd_userId, tripPlan.getUserId() == null ? 0 : tripPlan.getUserId());
            gen.writeStringField(TripPlan.fd_nickName, tripPlan.getNickName() == null ? "" : tripPlan.getNickName());

            gen.writeFieldName(TripPlan.fd_avatar);
            ImageItem avatar = tripPlan.getAvatar();
            if (avatar != null) {
                JsonSerializer<Object> retAvatar = serializers.findValueSerializer(ImageItem.class, null);
                retAvatar.serialize(avatar, gen, serializers);
            } else {
                gen.writeStartObject();
                gen.writeEndObject();
            }

            gen.writeNumberField(TripPlan.fd_createTime, tripPlan.getCreateTime() == null ? 0L : tripPlan.getCreateTime());
            gen.writeNumberField(TripPlan.fd_updateTime, tripPlan.getUpdateTime() == null ? 0L : tripPlan.getUpdateTime());
            gen.writeStringField(TripPlan.fd_title, tripPlan.getTitle() == null ? "" : tripPlan.getTitle());
            gen.writeStringField(TripPlan.fd_desc, tripPlan.getDesc() == null ? "" : tripPlan.getDesc());

            gen.writeFieldName(TripPlan.fd_cover);
            ImageItem cover = tripPlan.getCover();
            if (cover != null) {
                JsonSerializer<Object> retCover = serializers.findValueSerializer(ImageItem.class, null);
                retCover.serialize(cover, gen, serializers);
            } else {
                gen.writeStartObject();
                gen.writeEndObject();
            }

            List<TripItem> tripItems = tripPlan.getTripItems();
            if (tripItems != null && !tripItems.isEmpty()) {
                gen.writeFieldName(TripPlan.fd_tripItems);
                gen.writeStartArray();
                JsonSerializer<Object> ret = serializers.findValueSerializer(TripItem.class, null);
                for (TripItem tripItem : tripItems)
                    ret.serialize(tripItem, gen, serializers);
                gen.writeEndArray();
            }

            gen.writeNumberField(TripPlan.fd_favorCnt, tripPlan.getFavorCnt() == null ? 0 : tripPlan.getFavorCnt());
            gen.writeNumberField(TripPlan.fd_commentCnt, tripPlan.getCommentCnt() == null ? 0 : tripPlan.getCommentCnt());
            gen.writeNumberField(TripPlan.fd_viewCnt, tripPlan.getViewCnt() == null ? 0 : tripPlan.getViewCnt());
            gen.writeNumberField(TripPlan.fd_shareCnt, tripPlan.getShareCnt() == null ? 0 : tripPlan.getShareCnt());
            gen.writeNumberField(TripPlan.fd_voteCnt, tripPlan.getVoteCnt() == null ? 0 : tripPlan.getVoteCnt());
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

            if(tripPlan.getHotness() != null)
                gen.writeNumberField(TripPlan.fd_hotness, tripPlan.getHotness());

            gen.writeEndObject();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}