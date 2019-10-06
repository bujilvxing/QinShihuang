package com.bjlx.QinShihuang.core.formatter.activity;

import com.bjlx.QinShihuang.model.activity.Joiner;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.util.List;

/**
 * 参与者序列化
 * Created by xiaozhi on 2016/12/6.
 */
public class JoinerSerializer extends JsonSerializer<Joiner> {

    @Override
    public void serialize(Joiner joiner, JsonGenerator gen, SerializerProvider serializers) {
        try {
            gen.writeStartObject();
            gen.writeNumberField(Joiner.fd_userId, joiner.getUserId() == null ? 0 : joiner.getUserId());
            if(joiner.getName() != null) {
                gen.writeStringField(Joiner.fd_name, joiner.getName());
            }

            List<String> phoneList = joiner.getPhoneList();
            if (phoneList != null && (!phoneList.isEmpty())) {
                gen.writeFieldName(Joiner.fd_phoneList);
                gen.writeStartArray();
                for (String phone : phoneList)
                    gen.writeString(phone == null ? "" : phone);
                gen.writeEndArray();
            }

            List<String> cellphoneList = joiner.getCellphoneList();
            if (cellphoneList != null && (!cellphoneList.isEmpty())) {
                gen.writeFieldName(Joiner.fd_cellphoneList);
                gen.writeStartArray();
                for (String cellphone : cellphoneList)
                    gen.writeString(cellphone == null ? "" : cellphone);
                gen.writeEndArray();
            }

            if(joiner.getQq() != null)
                gen.writeStringField(Joiner.fd_qq, joiner.getQq());

            if(joiner.getWeixin() != null)
                gen.writeStringField(Joiner.fd_weixin, joiner.getWeixin());

            if(joiner.getSina() != null)
                gen.writeStringField(Joiner.fd_sina, joiner.getSina());

            if(joiner.getFax() != null)
                gen.writeStringField(Joiner.fd_fax, joiner.getFax());

            if(joiner.getEmail() != null)
                gen.writeStringField(Joiner.fd_email, joiner.getEmail());

            if(joiner.getWebsite() != null)
                gen.writeStringField(Joiner.fd_website, joiner.getWebsite());

            gen.writeEndObject();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}