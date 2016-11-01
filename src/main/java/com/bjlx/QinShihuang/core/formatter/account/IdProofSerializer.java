package com.bjlx.QinShihuang.core.formatter.account;

import com.bjlx.QinShihuang.model.account.ChineseID;
import com.bjlx.QinShihuang.model.account.IdProof;
import com.bjlx.QinShihuang.model.account.Passport;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

/**
 * 身份信息序列化
 * Created by pengyt on 2016/10/30.
 */
public class IdProofSerializer extends JsonSerializer<IdProof> {

    @Override
    public void serialize(IdProof idProof, JsonGenerator gen, SerializerProvider serializers) {
        try {
            gen.writeStartObject();
            if(idProof instanceof ChineseID) {
                ChineseID chineseID = (ChineseID)idProof;
                gen.writeStringField(ChineseID.fd_idType, chineseID.getIdType());
                gen.writeStringField(ChineseID.fd_number, chineseID.getNumber());
            } else if(idProof instanceof Passport) {
                Passport passport = (Passport)idProof;
                gen.writeStringField(Passport.fd_idType, passport.getIdType());
                gen.writeStringField(Passport.fd_nation, passport.getNation());
                gen.writeStringField(Passport.fd_number, passport.getNumber());
            }
            gen.writeEndObject();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}