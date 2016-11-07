package com.bjlx.QinShihuang.core.formatter.poi;

import java.io.IOException;
import java.util.List;

import com.bjlx.QinShihuang.model.misc.Address;
import com.bjlx.QinShihuang.model.misc.Contact;
import com.bjlx.QinShihuang.model.misc.ImageItem;
import com.bjlx.QinShihuang.model.poi.Hotel;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class HotelBasicSerializer extends JsonSerializer<Hotel> {

    @Override
    public void serialize(Hotel hotel, JsonGenerator gen, SerializerProvider serializers) {
	    try {
	        gen.writeStartObject();
	        gen.writeStringField(Hotel.fd_id, hotel.getId() == null ? "" : hotel.getId().toString());
	        
	        gen.writeFieldName(Hotel.fd_cover);
            ImageItem cover = hotel.getCover();
            if (cover != null) {
                JsonSerializer<Object> retCover = serializers.findValueSerializer(ImageItem.class, null);
                retCover.serialize(cover, gen, serializers);
            } else {
                gen.writeStartObject();
                gen.writeEndObject();
            }

            Contact contact = hotel.getContact();
            if (contact != null) {
            	gen.writeFieldName(Hotel.fd_contact);
                JsonSerializer<Object> retContact = serializers.findValueSerializer(Contact.class, null);
                retContact.serialize(contact, gen, serializers);
            }
            
            gen.writeStringField(Hotel.fd_zhName, hotel.getZhName() == null ? "" : hotel.getZhName());
            gen.writeStringField(Hotel.fd_enName, hotel.getEnName() == null ? "" : hotel.getEnName());
            gen.writeStringField(Hotel.fd_url, hotel.getUrl() == null ? "" : hotel.getUrl());
            gen.writeNumberField(Hotel.fd_marketPrice, hotel.getMarketPrice() == null ? 0.0 : hotel.getMarketPrice());
            gen.writeNumberField(Hotel.fd_price, hotel.getPrice() == null ? 0.0 : hotel.getPrice());
            
            List<String> tags = hotel.getTags();
            if (tags != null && (!tags.isEmpty())) {
            	gen.writeFieldName(Hotel.fd_tags);
                gen.writeStartArray();
                for (String tag : tags)
                    gen.writeString(tag == null ? "" : tag);
                gen.writeEndArray();
            }

            Address address = hotel.getAddress();
            if (address != null) {
            	gen.writeFieldName(Hotel.fd_address);
                JsonSerializer<Object> retAddress = serializers.findValueSerializer(Address.class, null);
                retAddress.serialize(address, gen, serializers);
            }

            gen.writeNumberField(Hotel.fd_saleVolume, hotel.getSaleVolume() == null ? 0 : hotel.getSaleVolume());
            if(hotel.getDiscount() != null)
            	gen.writeNumberField(Hotel.fd_discount, hotel.getDiscount());
            
	        gen.writeEndObject();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
}