package com.bjlx.QinShihuang.core.formatter.poi;

import java.io.IOException;
import com.bjlx.QinShihuang.model.misc.Contact;
import com.bjlx.QinShihuang.model.misc.ImageItem;
import com.bjlx.QinShihuang.model.poi.Shopping;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class ShoppingBasicSerializer extends JsonSerializer<Shopping> {

    @Override
    public void serialize(Shopping shopping, JsonGenerator gen, SerializerProvider serializers) {
	    try {
	        gen.writeStartObject();
	        gen.writeStringField(Shopping.fd_id, shopping.getId() == null ? "" : shopping.getId().toString());
	        if(shopping.getLat() != null)
	        	gen.writeNumberField(Shopping.fd_lat, shopping.getLat());
	        if(shopping.getLng() != null)
	        	gen.writeNumberField(Shopping.fd_lng, shopping.getLng());

	        gen.writeFieldName(Shopping.fd_cover);
            ImageItem cover = shopping.getCover();
            if (cover != null) {
                JsonSerializer<Object> retCover = serializers.findValueSerializer(ImageItem.class, null);
                retCover.serialize(cover, gen, serializers);
            } else {
                gen.writeStartObject();
                gen.writeEndObject();
            }

            Contact contact = shopping.getContact();
            if (contact != null) {
            	gen.writeFieldName(Shopping.fd_contact);
                JsonSerializer<Object> retContact = serializers.findValueSerializer(Contact.class, null);
                retContact.serialize(contact, gen, serializers);
            }
            
            gen.writeStringField(Shopping.fd_zhName, shopping.getZhName() == null ? "" : shopping.getZhName());
            gen.writeStringField(Shopping.fd_enName, shopping.getEnName() == null ? "" : shopping.getEnName());
            gen.writeStringField(Shopping.fd_url, shopping.getUrl() == null ? "" : shopping.getUrl());
            gen.writeNumberField(Shopping.fd_marketPrice, shopping.getMarketPrice() == null ? 0.0 : shopping.getMarketPrice());
            gen.writeNumberField(Shopping.fd_price, shopping.getPrice() == null ? 0.0 : shopping.getPrice());
            
            gen.writeNumberField(Shopping.fd_saleVolume, shopping.getSaleVolume() == null ? 0 : shopping.getSaleVolume());
            if(shopping.getDiscount() != null)
            	gen.writeNumberField(Shopping.fd_discount, shopping.getDiscount());
            
	        gen.writeEndObject();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
}
