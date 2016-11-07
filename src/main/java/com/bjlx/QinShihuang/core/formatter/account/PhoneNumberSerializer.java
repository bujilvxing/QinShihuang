package com.bjlx.QinShihuang.core.formatter.account;

import com.bjlx.QinShihuang.model.account.PhoneNumber;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

/**
 * 手机号码
 * Created by pengyt on 2016/10/29.
 */
public class PhoneNumberSerializer extends JsonSerializer<PhoneNumber> {

    @Override
    public void serialize(PhoneNumber phoneNumber, JsonGenerator gen, SerializerProvider serializers) {
        try {
            gen.writeStartObject();
            if(phoneNumber.getDialCode() != null)
                gen.writeNumberField(PhoneNumber.fd_dialCode, phoneNumber.getDialCode());
            gen.writeStringField(PhoneNumber.fd_number, phoneNumber.getNumber());
            gen.writeEndObject();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}