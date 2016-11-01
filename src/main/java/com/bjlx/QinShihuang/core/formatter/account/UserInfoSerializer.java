package com.bjlx.QinShihuang.core.formatter.account;

import com.bjlx.QinShihuang.model.account.OAuthInfo;
import com.bjlx.QinShihuang.model.account.PhoneNumber;
import com.bjlx.QinShihuang.model.account.RealNameInfo;
import com.bjlx.QinShihuang.model.account.UserInfo;
import com.bjlx.QinShihuang.model.activity.Activity;
import com.bjlx.QinShihuang.model.misc.ImageItem;
import com.bjlx.QinShihuang.model.misc.TravelNote;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.util.List;

/**
 * 用户信息
 * Created by pengyt on 2016/10/28.
 */
public class UserInfoSerializer extends JsonSerializer<UserInfo> {

    @Override
    public void serialize(UserInfo userInfo, JsonGenerator gen, SerializerProvider serializers) {
        try {
            gen.writeStartObject();

            gen.writeStringField(UserInfo.fd_id, userInfo.getId() == null ? "" : userInfo.getId().toString());

            PhoneNumber tel = userInfo.getTel();
            JsonSerializer<Object> retTel;
            if (tel != null) {
            	gen.writeFieldName(UserInfo.fd_tel);
                retTel = serializers.findValueSerializer(PhoneNumber.class, null);
                retTel.serialize(tel, gen, serializers);
            }

            if(userInfo.getEmail() != null) {
                gen.writeStringField(UserInfo.fd_email, userInfo.getEmail());
            }
            gen.writeNumberField(UserInfo.fd_userId, userInfo.getUserId() == null ? 0L : userInfo.getUserId());
            gen.writeStringField(UserInfo.fd_nickName, userInfo.getNickName() == null ? "" : userInfo.getNickName());

            gen.writeFieldName(UserInfo.fd_avatar);
            ImageItem avatar = userInfo.getAvatar();
            if (avatar != null) {
                JsonSerializer<Object> retAvatar = serializers.findValueSerializer(ImageItem.class, null);
                retAvatar.serialize(avatar, gen, serializers);
            } else {
                gen.writeStartObject();
                gen.writeEndObject();
            }

            if(userInfo.getGender() != null) {
                gen.writeNumberField(UserInfo.fd_gender, userInfo.getGender());
            }

            if(userInfo.getSignature() == null) {
                gen.writeStringField(UserInfo.fd_signature, userInfo.getSignature());
            }

            List<RealNameInfo> travellers = userInfo.getTravellers();
            
            if (travellers != null && !travellers.isEmpty()) {
            	gen.writeFieldName(UserInfo.fd_travellers);
                gen.writeStartArray();
                JsonSerializer<Object> ret = serializers.findValueSerializer(RealNameInfo.class, null);
                for (RealNameInfo traveller : travellers)
                    ret.serialize(traveller, gen, serializers);
                gen.writeEndArray();
            }
            

            gen.writeStringField(UserInfo.fd_promotionCode, userInfo.getPromotionCode() == null ? "" : userInfo.getPromotionCode());

            gen.writeBooleanField(UserInfo.fd_loginStatus, userInfo.isLoginStatus());
            if(userInfo.getLoginTime() != null)
                gen.writeNumberField(UserInfo.fd_loginTime, userInfo.getLoginTime());
            if(userInfo.getLogoutTime() != null)
                gen.writeNumberField(UserInfo.fd_logoutTime, userInfo.getLogoutTime());

            List<String> loginSource = userInfo.getLoginSource();
            
            if (loginSource != null && (!loginSource.isEmpty())) {
            	gen.writeFieldName(UserInfo.fd_loginSource);
                gen.writeStartArray();
                for (String e : loginSource)
                    gen.writeString(e == null ? "" : e);
                gen.writeEndArray();
            }
            
            if(userInfo.getVersion() != null)
                gen.writeNumberField(UserInfo.fd_version, userInfo.getVersion());

            List<Integer> roles = userInfo.getRoles();
            gen.writeFieldName(UserInfo.fd_roles);
            gen.writeStartArray();
            if (roles != null && (!roles.isEmpty())) {
                for (Integer role : roles)
                    gen.writeNumber(role);
            }
            gen.writeEndArray();

            if(userInfo.getMemo() != null)
                gen.writeStringField(UserInfo.fd_memo, userInfo.getMemo());

            if(userInfo.getResidence() != null)
                gen.writeStringField(UserInfo.fd_residence, userInfo.getResidence());

            if(userInfo.getResidence() != null)
                gen.writeStringField(UserInfo.fd_residence, userInfo.getResidence());

            if(userInfo.getBirthday() != null)
                gen.writeStringField(UserInfo.fd_birthday, userInfo.getBirthday());

            List<OAuthInfo> oauthInfoList = userInfo.getOauthInfoList();
            
            if (oauthInfoList != null && !oauthInfoList.isEmpty()) {
            	gen.writeFieldName(UserInfo.fd_oauthInfoList);
                gen.writeStartArray();
                JsonSerializer<Object> ret = serializers.findValueSerializer(OAuthInfo.class, null);
                for (OAuthInfo oauthInfo : oauthInfoList)
                    ret.serialize(oauthInfo, gen, serializers);
                gen.writeEndArray();
            }
            
            if(userInfo.getLevel() != null)
                gen.writeNumberField(UserInfo.fd_level, userInfo.getLevel());

//
//            /**
//             * 用户足迹
//             */
//            private List<Trace> traces;


            List<Activity> activities = userInfo.getActivities();
            
            if (activities != null && !activities.isEmpty()) {
            	gen.writeFieldName(UserInfo.fd_activities);
                gen.writeStartArray();
                JsonSerializer<Object> ret = serializers.findValueSerializer(Activity.class, null);
                for (Activity activitie : activities)
                    ret.serialize(activitie, gen, serializers);
                gen.writeEndArray();
            }
            

            List<TravelNote> travelNotes = userInfo.getTravelNotes();
            
            if (travelNotes != null && !travelNotes.isEmpty()) {
            	gen.writeFieldName(UserInfo.fd_travelNotes);
                gen.writeStartArray();
                JsonSerializer<Object> ret = serializers.findValueSerializer(TravelNote.class, null);
                for (TravelNote travelNote : travelNotes)
                    ret.serialize(travelNote, gen, serializers);
                gen.writeEndArray();
            }
            

//
//            /**
//             * 用户发布的行程规划
//             */
//            private List<TripPlan> tripPlans;

            if(userInfo.getZodiac() != null) {
                gen.writeNumberField(UserInfo.fd_zodiac, userInfo.getZodiac());
            }

            gen.writeBooleanField(UserInfo.fd_soundNotify, userInfo.isSoundNotify());
            gen.writeBooleanField(UserInfo.fd_vibrateNotify, userInfo.isVibrateNotify());

            gen.writeFieldName(UserInfo.fd_backGround);
            ImageItem backGround = userInfo.getBackGround();
            if (backGround != null) {
                JsonSerializer<Object> retBackGround = serializers.findValueSerializer(ImageItem.class, null);
                retBackGround.serialize(backGround, gen, serializers);
            } else {
                gen.writeStartObject();
                gen.writeEndObject();
            }

            gen.writeNumberField(UserInfo.fd_createTime, userInfo.getCreateTime() == null ? 0 : userInfo.getCreateTime());
            gen.writeNumberField(UserInfo.fd_updateTime, userInfo.getUpdateTime() == null ? 0 : userInfo.getUpdateTime());

            gen.writeEndObject();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
