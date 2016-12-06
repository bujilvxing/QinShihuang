package com.bjlx.QinShihuang.core.formatter.activity;

import com.bjlx.QinShihuang.model.account.UserInfo;
import com.bjlx.QinShihuang.model.activity.Activity;
import com.bjlx.QinShihuang.model.activity.Joiner;
import com.bjlx.QinShihuang.model.activity.Ticket;
import com.bjlx.QinShihuang.model.misc.Address;
import com.bjlx.QinShihuang.model.misc.ImageItem;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.util.List;

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

            gen.writeNumberField(Activity.fd_joinNum, activity.getJoinNum() == null ? 0 : activity.getJoinNum());

            if(activity.getStartTime() != null)
                gen.writeNumberField(Activity.fd_startTime, activity.getStartTime());

            if(activity.getEndTime() != null)
                gen.writeNumberField(Activity.fd_endTime, activity.getEndTime());

            Address address = activity.getAddress();
            JsonSerializer<Object> retAddress;
            if (address != null) {
                gen.writeFieldName(Activity.fd_address);
                retAddress = serializers.findValueSerializer(Address.class, null);
                retAddress.serialize(address, gen, serializers);
            }

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
            
            List<ImageItem> posters = activity.getPosters();
            gen.writeFieldName(Activity.fd_posters);
            gen.writeStartArray();
            if (posters != null && !posters.isEmpty()) {
                JsonSerializer<Object> ret = serializers.findValueSerializer(ImageItem.class, null);
                for (ImageItem poster : posters)
                    ret.serialize(poster, gen, serializers);
            }
            gen.writeEndArray();

            gen.writeStringField(Activity.fd_theme, activity.getTheme() == null ? "" : activity.getTheme());
            gen.writeStringField(Activity.fd_category, activity.getCategory() == null ? "" : activity.getCategory());

            List<String> tags = activity.getTags();
            if (tags != null && (!tags.isEmpty())) {
            	gen.writeFieldName(Activity.fd_tags);
                gen.writeStartArray();
                for (String tag : tags)
                    gen.writeString(tag == null ? "" : tag);
                gen.writeEndArray();
            }

            if(activity.getVisiable() != null)
                gen.writeNumberField(Activity.fd_visiable, activity.getVisiable());

            if(activity.getDesc() != null)
                gen.writeStringField(Activity.fd_desc, activity.getDesc());

            List<Joiner> applicantInfos = activity.getApplicantInfos();
            if (applicantInfos != null && !applicantInfos.isEmpty()) {
            	gen.writeFieldName(Activity.fd_applicantInfos);
                gen.writeStartArray();
                JsonSerializer<Object> ret = serializers.findValueSerializer(Joiner.class, null);
                for (Joiner applicantInfo : applicantInfos)
                    ret.serialize(applicantInfo, gen, serializers);
                gen.writeEndArray();
            }

            UserInfo userInfo = activity.getCreator();
            gen.writeFieldName(Activity.fd_creator);
            if (userInfo != null) {
                JsonSerializer<Object> ret = serializers.findValueSerializer(UserInfo.class, null);
                ret.serialize(userInfo, gen, serializers);
            } else {
                gen.writeStartObject();
                gen.writeEndObject();
            }
            
            List<Ticket> tickets = activity.getTickets();
            if (tickets != null && !tickets.isEmpty()) {
            	gen.writeFieldName(Activity.fd_tickets);
                gen.writeStartArray();
                JsonSerializer<Object> ret = serializers.findValueSerializer(Ticket.class, null);
                for (Ticket ticket : tickets)
                    ret.serialize(ticket, gen, serializers);
                gen.writeEndArray();
            }
            gen.writeBooleanField(Activity.fd_isFree, activity.getIsFree() == null ? true : activity.getIsFree());
            gen.writeNumberField(Activity.fd_publishTime, activity.getPublishTime() == null ? 0L : activity.getPublishTime());
            gen.writeEndObject();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}