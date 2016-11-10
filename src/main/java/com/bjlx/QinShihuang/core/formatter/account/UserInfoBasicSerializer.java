package com.bjlx.QinShihuang.core.formatter.account;

import com.bjlx.QinShihuang.model.account.UserInfo;
import com.bjlx.QinShihuang.model.misc.ImageItem;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

public class UserInfoBasicSerializer extends JsonSerializer<UserInfo> {

    @Override
    public void serialize(UserInfo userInfo, JsonGenerator gen, SerializerProvider serializers) {
        try {
            gen.writeStartObject();
            gen.writeStringField(UserInfo.fd_id, userInfo.getId() == null ? "" : userInfo.getId().toString());

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
            if(userInfo.getMemo() != null)
                gen.writeStringField(UserInfo.fd_memo, userInfo.getMemo());
            gen.writeEndObject();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
