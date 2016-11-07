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

            List<IdProof> identities = realNameInfo.getIdentities();
            
            if (identities != null && !identities.isEmpty()) {
            	gen.writeFieldName(RealNameInfo.fd_identities);
            	gen.writeStartArray();
                JsonSerializer<Object> ret = serializers.findValueSerializer(IdProof.class, null);
                for (IdProof idProof : identities)
                    ret.serialize(idProof, gen, serializers);
                gen.writeEndArray();
            }

            PhoneNumber tel = realNameInfo.getTel();
            if (tel != null) {
            	gen.writeFieldName(RealNameInfo.fd_tel);
                JsonSerializer<Object> retTel = serializers.findValueSerializer(PhoneNumber.class, null);
                retTel.serialize(tel, gen, serializers);
            }

            if (realNameInfo.getEmail() == null)
                gen.writeStringField(RealNameInfo.fd_email, realNameInfo.getEmail());
            
            gen.writeEndObject();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
