package com.bjlx.QinShihuang.core.formatter;

import com.bjlx.QinShihuang.model.account.PhoneNumber;
import com.bjlx.QinShihuang.model.account.RealNameInfo;
import com.bjlx.QinShihuang.model.account.UserInfo;
import com.bjlx.QinShihuang.model.misc.ImageItem;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.util.List;
import java.util.Map;

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
            gen.writeFieldName(UserInfo.fd_travellers);
            gen.writeStartArray();
            if (travellers != null && !travellers.isEmpty()) {
                JsonSerializer<Object> ret = serializers.findValueSerializer(RealNameInfo.class, null);
                for (RealNameInfo traveller : travellers)
                    ret.serialize(traveller, gen, serializers);
            }
            gen.writeEndArray();

            gen.writeStringField(UserInfo.fd_promotionCode, userInfo.getPromotionCode() == null ? "" : userInfo.getPromotionCode());

            gen.writeBooleanField(UserInfo.fd_loginStatus, userInfo.isLoginStatus());
            
//            /**
//             * 登录时间
//             */
//            private Long loginTime = 0L;
//
//            /**
//             * 登出时间
//             */
//            private Long logoutTime = 0L;
//
//            /**
//             * 登录设备来源
//             */
//            private List<String> loginSource = null;
//
//            /**
//             * 设备版本
//             */
//            private Long version = 0L;
//
//            /**
//             * 用户角色。普通用户，商家等等
//             */
//            private List<Integer> roles = null;
//
//            /**
//             * 用户备注。此字段为Transient，是不存入数据库的，但是取用户数据的时候，可以将给此字段赋值，为了返回用户信息的
//             */
//            @Transient
//            private String memo = null;
//
//            /**
//             * 用户的居住地
//             */
//            private String residence = null;
//
//            /**
//             * 用户的生日
//             */
//            private String birthday  = null;
//
//            /**
//             * 第三方账号的信息
//             */
//            private List<OAuthInfo> oauthInfoList = null;
//
//            /**
//             * 用户等级
//             */
//            private Integer level = 1;
//
//            /**
//             * 用户足迹
//             */
//            private List<Trace> traces;
//
//            /**
//             * 用户发布的活动
//             */
//            private List<Activity> activities;
//
//            /**
//             * 用户发表的游记
//             */
//            private List<TravelNote> travelNotes;
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
