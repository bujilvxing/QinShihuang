package com.bjlx.QinShihuang.core.formatter.misc;

import com.bjlx.QinShihuang.model.misc.Contact;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.util.List;

/**
 * 联系人序列化
 * Created by xiaozhi on 2016/10/31.
 */
public class ContactSerializer extends JsonSerializer<Contact> {

    @Override
    public void serialize(Contact contact, JsonGenerator gen, SerializerProvider serializers) {
        try {
            gen.writeStartObject();
            List<String> phoneList = contact.getPhoneList();
            if (phoneList != null && (!phoneList.isEmpty())) {
            	gen.writeFieldName(Contact.fd_phoneList);
                gen.writeStartArray();
                for (String phone : phoneList)
                    gen.writeString(phone == null ? "" : phone);
                gen.writeEndArray();
            }
            

            List<String> cellphoneList = contact.getCellphoneList();
            if (cellphoneList != null && (!cellphoneList.isEmpty())) {
            	gen.writeFieldName(Contact.fd_cellphoneList);
                gen.writeStartArray();
                for (String cellphone : cellphoneList)
                    gen.writeString(cellphone == null ? "" : cellphone);
                gen.writeEndArray();
            }
            
            if(contact.getQq() != null)
                gen.writeStringField(Contact.fd_qq, contact.getQq());

            if(contact.getWeixin() != null)
                gen.writeStringField(Contact.fd_weixin, contact.getWeixin());

            if(contact.getSina() != null)
                gen.writeStringField(Contact.fd_sina, contact.getSina());

            if(contact.getFax() != null)
                gen.writeStringField(Contact.fd_fax, contact.getFax());

            if(contact.getEmail() != null)
                gen.writeStringField(Contact.fd_email, contact.getEmail());

            if(contact.getWebsite() != null)
                gen.writeStringField(Contact.fd_website, contact.getWebsite());

            gen.writeEndObject();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}