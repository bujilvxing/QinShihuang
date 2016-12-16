package com.bjlx.QinShihuang.core.formatter.trace;

import com.bjlx.QinShihuang.model.activity.Activity;
import com.bjlx.QinShihuang.model.misc.Audio;
import com.bjlx.QinShihuang.model.misc.ImageItem;
import com.bjlx.QinShihuang.model.poi.Hotel;
import com.bjlx.QinShihuang.model.poi.Restaurant;
import com.bjlx.QinShihuang.model.poi.Shopping;
import com.bjlx.QinShihuang.model.poi.Viewspot;
import com.bjlx.QinShihuang.model.trace.Trace;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.util.List;

/**
 * 足迹序列化
 * Created by xiaozhi on 2016/10/31.
 */
public class TraceSerializer extends JsonSerializer<Trace> {

    @Override
    public void serialize(Trace trace, JsonGenerator gen, SerializerProvider serializers) {
        try {
            gen.writeStartObject();
            gen.writeStringField(Trace.fd_id, trace.getId() == null ? "" : trace.getId().toString());
            gen.writeNumberField(Trace.fd_userId, trace.getUserId() == null ? 0 : trace.getUserId());
            gen.writeStringField(Trace.fd_nickName, trace.getNickName() == null ? "" : trace.getNickName());
            gen.writeFieldName(Trace.fd_avatar);
            ImageItem avatar = trace.getAvatar();
            if (avatar != null) {
                JsonSerializer<Object> retAvatar = serializers.findValueSerializer(ImageItem.class, null);
                retAvatar.serialize(avatar, gen, serializers);
            } else {
                gen.writeStartObject();
                gen.writeEndObject();
            }

            gen.writeNumberField(Trace.fd_createTime, trace.getCreateTime() == null ? 0 : trace.getCreateTime());
            gen.writeNumberField(Trace.fd_traceTime, trace.getTraceTime() == null ? 0 : trace.getTraceTime());
            gen.writeNumberField(Trace.fd_updateTime, trace.getUpdateTime() == null ? 0 : trace.getUpdateTime());


            gen.writeFieldName(Trace.fd_cover);
            ImageItem cover = trace.getCover();
            if (cover != null) {
                JsonSerializer<Object> retCover = serializers.findValueSerializer(ImageItem.class, null);
                retCover.serialize(cover, gen, serializers);
            } else {
                gen.writeStartObject();
                gen.writeEndObject();
            }

            List<ImageItem> images = trace.getImages();
            gen.writeFieldName(Trace.fd_images);
            gen.writeStartArray();
            if (images != null && !images.isEmpty()) {
                JsonSerializer<Object> ret = serializers.findValueSerializer(ImageItem.class, null);
                for (ImageItem image : images)
                    ret.serialize(image, gen, serializers);
            }
            gen.writeEndArray();

            Audio audio = trace.getAudio();
            if (avatar != null) {
            	gen.writeFieldName(Trace.fd_audio);
                JsonSerializer<Object> retAudio = serializers.findValueSerializer(Audio.class, null);
                retAudio.serialize(audio, gen, serializers);
            }
            
            gen.writeStringField(Trace.fd_title, trace.getTitle() == null ? "" : trace.getTitle());
            if(trace.getDesc() != null)
            	gen.writeStringField(Trace.fd_desc, trace.getDesc());
            gen.writeNumberField(Trace.fd_status, trace.getStatus() == null ? 1 : trace.getStatus());
            
            Activity activity = trace.getActivity();
            if (activity != null) {
            	gen.writeFieldName(Trace.fd_activity);
                JsonSerializer<Object> retActivity = serializers.findValueSerializer(Activity.class, null);
                retActivity.serialize(activity, gen, serializers);
            }
            
            Viewspot viewspot = trace.getViewspot();
            if (viewspot != null) {
            	gen.writeFieldName(Trace.fd_viewspot);
                JsonSerializer<Object> retViewspot = serializers.findValueSerializer(Viewspot.class, null);
                retViewspot.serialize(viewspot, gen, serializers);
            }
            
            Restaurant restaurant = trace.getRestaurant();
            if (restaurant != null) {
            	gen.writeFieldName(Trace.fd_restaurant);
                JsonSerializer<Object> retRestaurant = serializers.findValueSerializer(Restaurant.class, null);
                retRestaurant.serialize(restaurant, gen, serializers);
            }
            
            Shopping shopping = trace.getShopping();
            if (shopping != null) {
            	gen.writeFieldName(Trace.fd_shopping);
                JsonSerializer<Object> retShopping = serializers.findValueSerializer(Shopping.class, null);
                retShopping.serialize(shopping, gen, serializers);
            }
            
            Hotel hotel = trace.getHotel();
            if (hotel != null) {
            	gen.writeFieldName(Trace.fd_hotel);
                JsonSerializer<Object> retHotel = serializers.findValueSerializer(Hotel.class, null);
                retHotel.serialize(hotel, gen, serializers);
            }

            gen.writeNumberField(Trace.fd_favorCnt, trace.getFavorCnt() == null ? 0 : trace.getFavorCnt());
            gen.writeNumberField(Trace.fd_commentCnt, trace.getCommentCnt() == null ? 0 : trace.getCommentCnt());
            gen.writeNumberField(Trace.fd_viewCnt, trace.getViewCnt() == null ? 0 : trace.getViewCnt());
            gen.writeNumberField(Trace.fd_shareCnt, trace.getShareCnt() == null ? 0 : trace.getShareCnt());

            if(trace.getOriginId() != null)
            	gen.writeStringField(Trace.fd_originId, trace.getOriginId().toString());
            
            if(trace.getOriginUserId() != null)
            	gen.writeNumberField(Trace.fd_originUserId, trace.getOriginUserId());

            if(trace.getOriginNickName() != null)
            	gen.writeStringField(Trace.fd_originNickName, trace.getOriginNickName());
            
            
            ImageItem originAvatar = trace.getOriginAvatar();
            if (originAvatar != null) {
            	gen.writeFieldName(Trace.fd_originAvatar);
                JsonSerializer<Object> retOriginAvatar = serializers.findValueSerializer(ImageItem.class, null);
                retOriginAvatar.serialize(originAvatar, gen, serializers);
            }
            
            if(trace.getLat() != null)
            	gen.writeNumberField(Trace.fd_lat, trace.getLat());
            if(trace.getLng() != null)
            	gen.writeNumberField(Trace.fd_lng, trace.getLng());
            gen.writeNumberField(Trace.fd_voteCnt, trace.getVoteCnt() == null ? 0 : trace.getVoteCnt());
            gen.writeEndObject();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}