package com.bjlx.QinShihuang.core.formatter;

import com.bjlx.QinShihuang.model.Sms;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.JsonSerializer;

import java.io.IOException;

/**
 * 验证码序列化
 * Created by pengyt on 2016/10/23.
 */
public class SmsSerializer extends JsonSerializer<Sms> {

    @Override
    public void serialize(Sms sms, JsonGenerator gen, SerializerProvider serializers) {
        try {
            gen.writeStartObject();
            gen.writeStringField("id", sms.getId().toString());
            gen.writeStringField("account", sms.getAccount());
            gen.writeStringField("validationCode", sms.getValidationCode());
            gen.writeEndObject();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
