package com.bjlx.QinShihuang.core.formatter.trace;

import com.bjlx.QinShihuang.model.misc.Audio;
import com.bjlx.QinShihuang.model.misc.ImageItem;
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

            gen.writeFieldName(Trace.fd_audio);
            Audio audio = trace.getAudio();
            if (avatar != null) {
                JsonSerializer<Object> retAudio = serializers.findValueSerializer(Audio.class, null);
                retAudio.serialize(audio, gen, serializers);
            } else {
                gen.writeStartObject();
                gen.writeEndObject();
            }

//            /**
//             * 语音描述
//             */
//            private Audio audio;
//
//            /**
//             * 标题
//             */
//            private String title;
//
//            /**
//             * 文字描述
//             */
//            private String desc;
//
//            /**
//             * 状态，1表示私密可不见，2表示好友可见，3表示所有人可见
//             */
//            private Integer status = 1;
//
//            /**
//             * 足迹所参加的活动
//             */
//            private Activity activity;
//
//            /**
//             * 足迹所在的景点
//             */
//            private Viewspot viewspot;
//
//            /**
//             * 足迹所在的餐馆
//             */
//            private Restaurant restaurant;
//
//            /**
//             * 足迹所在的购物
//             */
//            private Shopping shopping;
//
//            /**
//             * 足迹所在的酒店
//             */
//            private Hotel hotel;
//
//            /**
//             * 收藏次数
//             */
//            @Min(value = 0)
//            private Integer favorCnt = 0;
//
//            /**
//             * 评论次数
//             */
//            @Min(value = 0)
//            private Integer commentCnt = 0;
//
//            /**
//             * 浏览次数
//             */
//            @Min(value = 0)
//            private Integer viewCnt = 0;
//
//            /**
//             * 转发次数
//             */
//            @Min(value = 0)
//            private Integer shareCnt = 0;
//
//            /**
//             * 源足迹id
//             */
//            private ObjectId originId;
//
//            /**
//             * 足迹原创用户id
//             */
//            private Long originUserId;
//
//            /**
//             * 足迹原创用户昵称
//             */
//            private String originNickName;
//
//            /**
//             * 足迹原创用户头像
//             */
//            private ImageItem originAvatar;
//
//            /**
//             * 经度
//             */
//            @Min(value = -90)
//            @Max(value = 90)
//            private Double lat;
//
//            /**
//             * 纬度
//             */
//            @Min(value = -180)
//            @Max(value = 180)
//            private Double lng;
            gen.writeEndObject();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}