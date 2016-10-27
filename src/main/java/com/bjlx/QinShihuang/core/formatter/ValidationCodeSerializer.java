package com.bjlx.QinShihuang.core.formatter;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.bjlx.QinShihuang.model.misc.ValidationCode;
import java.io.IOException;

/**
 * 验证码序列化
 * Created by pengyt on 2016/10/23.
 */
public class ValidationCodeSerializer extends JsonSerializer<ValidationCode> {

    @Override
    public void serialize(ValidationCode validationCode, JsonGenerator gen, SerializerProvider serializers) {
        try {
            gen.writeStartObject();
            
            gen.writeStringField("id", validationCode.getId() == null ? "" : validationCode.getId().toString());
            if(validationCode.getTel() != null) {
            	gen.writeFieldName("tel");
            	gen.writeStartObject();
            	gen.writeNumberField("dialCode", validationCode.getTel().getDialCode() == null ? 86 : validationCode.getTel().getDialCode());
            	gen.writeStringField("number", validationCode.getTel().getNumber() == null ? "" : validationCode.getTel().getNumber());
            	gen.writeEndObject();
            }
            if(validationCode.getEmail() != null) {
            	gen.writeStringField("email", validationCode.getEmail());
            }
            gen.writeStringField("code", validationCode.getCode() == null ? "" : validationCode.getCode());
            gen.writeNumberField("action", validationCode.getAction() == null ? -1 : validationCode.getAction());
            gen.writeNumberField("failCnt", validationCode.getFailCnt() == null ? 0 : validationCode.getFailCnt());
            gen.writeNumberField("createTime", validationCode.getCreateTime() == null ? 0 : validationCode.getCreateTime());
            gen.writeNumberField("expireTime", validationCode.getExpireTime() == null ? 0 : validationCode.getExpireTime());
            gen.writeNumberField("lastSendTime", validationCode.getLastSendTime() == null ? 0 : validationCode.getLastSendTime());
            gen.writeNumberField("resendTime", validationCode.getResendTime() == null ? 0 : validationCode.getResendTime());
            
            gen.writeEndObject();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
