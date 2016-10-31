package com.bjlx.QinShihuang.core.formatter;

import com.bjlx.QinShihuang.model.activity.Activity;
import com.bjlx.QinShihuang.model.misc.Address;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

/**
 * 活动序列化
 * Created by xiaozhi on 2016/10/31.
 */
public class ActivitySerializer extends JsonSerializer<Activity> {

    @Override
    public void serialize(Activity activity, JsonGenerator gen, SerializerProvider serializers) {
        try {
            gen.writeStartObject();
            gen.writeStringField(Activity.fd_id, activity.getId() == null ? "" : activity.getId().toString());
            gen.writeStringField(Activity.fd_title, activity.getTitle() == null ? "" : activity.getTitle());
            if(activity.getMaxNum() != null)
                gen.writeNumberField(Activity.fd_maxNum, activity.getMaxNum());

            if(activity.getJoinNum() != null)
                gen.writeNumberField(Activity.fd_joinNum, activity.getJoinNum());

            if(activity.getStartTime() != null)
                gen.writeNumberField(Activity.fd_startTime, activity.getStartTime());

            if(activity.getEndTime() != null)
                gen.writeNumberField(Activity.fd_endTime, activity.getEndTime());

            Address address = activity.getAddress();
            JsonSerializer<Object> retAddress;
            if (address != null) {
                retAddress = serializers.findValueSerializer(Address.class, null);
                retAddress.serialize(address, gen, serializers);
            }


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
//             * 分享次数
//             */
//            @Min(value = 0)
//            private Integer shareCnt = 0;
//
//            /**
//             * 海报
//             */
//            private List<ImageItem> posters;
//
//            /**
//             * 活动主题
//             */
//            private String theme;
//
//            /**
//             * 活动分类
//             */
//            private String category;
//
//            /**
//             * 活动标签
//             */
//            private List<String> tags;
//
//            /**
//             * 活动是否为隐私活动，1表示不可见，2表示可见
//             */
//            private Integer visiable = 1;
//
//            /**
//             * 活动详情
//             */
//            private String desc;
//
//            /**
//             * 报名人信息
//             */
//            private List<Contact> applicantInfos;
//
//            /**
//             * 门票
//             */
//            private List<Ticket> tickets;
            gen.writeEndObject();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}