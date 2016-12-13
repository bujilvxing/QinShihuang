package com.bjlx.QinShihuang.core.formatter.poi;

import com.bjlx.QinShihuang.model.misc.Contact;
import com.bjlx.QinShihuang.model.misc.ImageItem;
import com.bjlx.QinShihuang.model.poi.Restaurant;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

public class RestaurantBasicSerializer extends JsonSerializer<Restaurant> {

    @Override
    public void serialize(Restaurant restaurant, JsonGenerator gen, SerializerProvider serializers) {
	    try {
	        gen.writeStartObject();
	        gen.writeStringField(Restaurant.fd_id, restaurant.getId() == null ? "" : restaurant.getId().toString());
	        gen.writeFieldName(Restaurant.fd_cover);
            ImageItem cover = restaurant.getCover();
            if (cover != null) {
                JsonSerializer<Object> retCover = serializers.findValueSerializer(ImageItem.class, null);
                retCover.serialize(cover, gen, serializers);
            } else {
                gen.writeStartObject();
                gen.writeEndObject();
            }

            Contact contact = restaurant.getContact();
            if (contact != null) {
            	gen.writeFieldName(Restaurant.fd_contact);
                JsonSerializer<Object> retContact = serializers.findValueSerializer(Contact.class, null);
                retContact.serialize(contact, gen, serializers);
            }
            if(restaurant.getFavorCnt() != null)
                gen.writeNumberField(Restaurant.fd_favorCnt, restaurant.getFavorCnt());

            gen.writeStringField(Restaurant.fd_zhName, restaurant.getZhName() == null ? "" : restaurant.getZhName());
            gen.writeStringField(Restaurant.fd_enName, restaurant.getEnName() == null ? "" : restaurant.getEnName());
            gen.writeStringField(Restaurant.fd_url, restaurant.getUrl() == null ? "" : restaurant.getUrl());
            gen.writeNumberField(Restaurant.fd_marketPrice, restaurant.getMarketPrice() == null ? 0 : restaurant.getMarketPrice());
            gen.writeNumberField(Restaurant.fd_price, restaurant.getPrice() == null ? 0 : restaurant.getPrice());
            

            gen.writeNumberField(Restaurant.fd_saleVolume, restaurant.getSaleVolume() == null ? 0 : restaurant.getSaleVolume());
            
            
	        gen.writeEndObject();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
}