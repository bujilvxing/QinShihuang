package com.bjlx.QinShihuang.core.formatter.account;

import com.bjlx.QinShihuang.model.account.OAuthInfo;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

/**
 * 第三方账号序列化
 * Created by xiaozhi on 2016/10/31.
 */
public class OAuthInfoSerializer extends JsonSerializer<OAuthInfo> {

    @Override
    public void serialize(OAuthInfo oAuthInfo, JsonGenerator gen, SerializerProvider serializers) {
        try {
            gen.writeStartObject();
            gen.writeStringField(OAuthInfo.fd_provider, oAuthInfo.getProvider() == null ? "" : oAuthInfo.getProvider());
            gen.writeStringField(OAuthInfo.fd_oauthId, oAuthInfo.getOauthId() == null ? "" : oAuthInfo.getOauthId());
            gen.writeStringField(OAuthInfo.fd_nickName, oAuthInfo.getNickName() == null ? "" : oAuthInfo.getNickName());
            gen.writeStringField(OAuthInfo.fd_avatar, oAuthInfo.getAvatar() == null ? "" : oAuthInfo.getAvatar());
            gen.writeStringField(OAuthInfo.fd_token, oAuthInfo.getToken() == null ? "" : oAuthInfo.getToken());
            gen.writeEndObject();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}