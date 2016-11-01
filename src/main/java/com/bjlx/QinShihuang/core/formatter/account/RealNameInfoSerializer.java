package com.bjlx.QinShihuang.core.formatter.account;

import com.bjlx.QinShihuang.model.account.IdProof;
import com.bjlx.QinShihuang.model.account.PhoneNumber;
import com.bjlx.QinShihuang.model.account.RealNameInfo;
import com.bjlx.QinShihuang.utils.CommonUtil;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.util.List;

/**
 * 实名信息的序列化
 * Created by pengyt on 2016/10/29.
 */
public class RealNameInfoSerializer extends JsonSerializer<RealNameInfo> {

    @Override
    public void serialize(RealNameInfo realNameInfo, JsonGenerator gen, SerializerProvider serializers) {
        try {
            gen.writeStartObject();
            gen.writeStringField(RealNameInfo.fd_surname, realNameInfo.getSurname());
            gen.writeStringField(RealNameInfo.fd_givenName, realNameInfo.getGivenName());
            if(realNameInfo.getGender() != null)
                gen.writeNumberField(RealNameInfo.fd_gender, realNameInfo.getGender());
            if (realNameInfo.getBirthday() == null)
                gen.writeStringField(RealNameInfo.fd_birthday, CommonUtil.getDate(realNameInfo.getBirthday()));

            gen.writeFieldName(RealNameInfo.fd_identities);
            List<IdProof> identities = realNameInfo.getIdentities();
            gen.writeStartArray();
            if (identities != null && !identities.isEmpty()) {
                JsonSerializer<Object> ret = serializers.findValueSerializer(IdProof.class, null);
                for (IdProof idProof : identities)
                    ret.serialize(idProof, gen, serializers);
            }

            PhoneNumber tel = realNameInfo.getTel();
            if (tel != null) {
                JsonSerializer<Object> retTel = serializers.findValueSerializer(PhoneNumber.class, null);
                retTel.serialize(tel, gen, serializers);
            }

            if (realNameInfo.getEmail() == null)
                gen.writeStringField(RealNameInfo.fd_email, realNameInfo.getEmail());
            gen.writeEndArray();

            gen.writeEndObject();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
