package com.bjlx.QinShihuang.core.formatter.activity;

import com.bjlx.QinShihuang.model.activity.Activity;
import com.bjlx.QinShihuang.model.activity.Ticket;
import com.bjlx.QinShihuang.model.misc.Address;
import com.bjlx.QinShihuang.model.misc.Contact;
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

            List<Contact> applicantInfos = activity.getApplicantInfos();
            gen.writeFieldName(Activity.fd_applicantInfos);
            gen.writeStartArray();
            if (applicantInfos != null && !applicantInfos.isEmpty()) {
                JsonSerializer<Object> ret = serializers.findValueSerializer(Contact.class, null);
                for (Contact applicantInfo : applicantInfos)
                    ret.serialize(applicantInfo, gen, serializers);
            }
            gen.writeEndArray();


            List<Ticket> tickets = activity.getTickets();
            gen.writeFieldName(Activity.fd_tickets);
            gen.writeStartArray();
            if (tickets != null && !tickets.isEmpty()) {
                JsonSerializer<Object> ret = serializers.findValueSerializer(Ticket.class, null);
                for (Ticket ticket : tickets)
                    ret.serialize(ticket, gen, serializers);
            }
            gen.writeEndArray();

            gen.writeEndObject();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}