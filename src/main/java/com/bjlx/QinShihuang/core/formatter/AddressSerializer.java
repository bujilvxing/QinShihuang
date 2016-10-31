package com.bjlx.QinShihuang.core.formatter;

/**
 * 地址序列化
 * Created by xiaozhi on 2016/10/31.
 */

import com.bjlx.QinShihuang.model.misc.Address;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

public class AddressSerializer extends JsonSerializer<Address> {

    @Override
    public void serialize(Address address, JsonGenerator gen, SerializerProvider serializers) {
        try {
            gen.writeStartObject();
            if(address.getProvince() != null)
                gen.writeStringField(Address.fd_province, address.getProvince());
            if(address.getCity() != null)
                gen.writeStringField(Address.fd_city, address.getCity());
            if(address.getDistrict() != null)
                gen.writeStringField(Address.fd_district, address.getDistrict());
            if(address.getDetail() != null)
                gen.writeStringField(Address.fd_detail, address.getDetail());
            if(address.getZipCode() != null)
                gen.writeStringField(Address.fd_zipCode, address.getZipCode());
            gen.writeEndObject();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}