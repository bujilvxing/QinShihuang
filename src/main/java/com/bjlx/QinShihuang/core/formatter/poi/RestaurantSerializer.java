package com.bjlx.QinShihuang.core.formatter.poi;

import com.bjlx.QinShihuang.model.geo.Locality;
import com.bjlx.QinShihuang.model.misc.Address;
import com.bjlx.QinShihuang.model.misc.Contact;
import com.bjlx.QinShihuang.model.misc.ImageItem;
import com.bjlx.QinShihuang.model.poi.Description;
import com.bjlx.QinShihuang.model.poi.Restaurant;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.util.List;

/**
 * 餐馆序列化
 * Created by xiaozhi on 2016/10/31.
 */
public class RestaurantSerializer extends JsonSerializer<Restaurant> {

    @Override
    public void serialize(Restaurant restaurant, JsonGenerator gen, SerializerProvider serializers) {
	    try {
	        gen.writeStartObject();
	        gen.writeStringField(Restaurant.fd_id, restaurant.getId() == null ? "" : restaurant.getId().toString());
	        if(restaurant.getLat() != null)
	        	gen.writeNumberField(Restaurant.fd_lat, restaurant.getLat());
	        if(restaurant.getLng() != null)
	        	gen.writeNumberField(Restaurant.fd_lng, restaurant.getLng());

	        gen.writeFieldName(Restaurant.fd_cover);
            ImageItem cover = restaurant.getCover();
            if (cover != null) {
                JsonSerializer<Object> retCover = serializers.findValueSerializer(ImageItem.class, null);
                retCover.serialize(cover, gen, serializers);
            } else {
                gen.writeStartObject();
                gen.writeEndObject();
            }

            List<ImageItem> images = restaurant.getImages();
            gen.writeFieldName(Restaurant.fd_images);
            gen.writeStartArray();
            if (images != null && !images.isEmpty()) {
                JsonSerializer<Object> ret = serializers.findValueSerializer(ImageItem.class, null);
                for (ImageItem image : images)
                    ret.serialize(image, gen, serializers);
            }
            gen.writeEndArray();
            
            if(restaurant.getRank() != null)
                gen.writeNumberField(Restaurant.fd_rank, restaurant.getRank());
            
            if(restaurant.getRating() != null)
                gen.writeNumberField(Restaurant.fd_rating, restaurant.getRating());

            if(restaurant.getHotness() != null)
                gen.writeNumberField(Restaurant.fd_hotness, restaurant.getHotness());

            
            Contact contact = restaurant.getContact();
            if (contact != null) {
            	gen.writeFieldName(Restaurant.fd_contact);
                JsonSerializer<Object> retContact = serializers.findValueSerializer(Contact.class, null);
                retContact.serialize(contact, gen, serializers);
            }
            
            gen.writeStringField(Restaurant.fd_zhName, restaurant.getZhName() == null ? "" : restaurant.getZhName());
            gen.writeStringField(Restaurant.fd_enName, restaurant.getEnName() == null ? "" : restaurant.getEnName());
            gen.writeStringField(Restaurant.fd_url, restaurant.getUrl() == null ? "" : restaurant.getUrl());
            gen.writeNumberField(Restaurant.fd_marketPrice, restaurant.getMarketPrice() == null ? 0 : restaurant.getMarketPrice());
            gen.writeNumberField(Restaurant.fd_price, restaurant.getPrice() == null ? 0 : restaurant.getPrice());
            if(restaurant.getPriceDesc() != null)
            	gen.writeStringField(Restaurant.fd_priceDesc, restaurant.getPriceDesc());
            
            if(restaurant.getOpenTime() != null)
            	gen.writeStringField(Restaurant.fd_openTime, restaurant.getOpenTime());

            Description description = restaurant.getDescription();
            if (description != null) {
            	gen.writeFieldName(Restaurant.fd_description);
                JsonSerializer<Object> retDescription = serializers.findValueSerializer(Description.class, null);
                retDescription.serialize(description, gen, serializers);
            }
            List<String> tags = restaurant.getTags();
            if (tags != null && (!tags.isEmpty())) {
            	gen.writeFieldName(Restaurant.fd_tags);
                gen.writeStartArray();
                for (String tag : tags)
                    gen.writeString(tag == null ? "" : tag);
                gen.writeEndArray();
            }

            List<String> alias = restaurant.getAlias();
            if (alias != null && (!alias.isEmpty())) {
            	gen.writeFieldName(Restaurant.fd_alias);
                gen.writeStartArray();
                for (String alia : alias)
                    gen.writeString(alia == null ? "" : alia);
                gen.writeEndArray();
            }
            
            List<String> targets = restaurant.getTargets();
            if (targets != null && (!targets.isEmpty())) {
            	gen.writeFieldName(Restaurant.fd_targets);
                gen.writeStartArray();
                for (String target : targets)
                    gen.writeString(target == null ? "" : target);
                gen.writeEndArray();
            }

            if(restaurant.getSource() != null)
            	gen.writeStringField(Restaurant.fd_source, restaurant.getSource());

            if(restaurant.getGuideUrl() != null)
            	gen.writeStringField(Restaurant.fd_guideUrl, restaurant.getGuideUrl());

            Address address = restaurant.getAddress();
            if (address != null) {
            	gen.writeFieldName(Restaurant.fd_address);
                JsonSerializer<Object> retAddress = serializers.findValueSerializer(Address.class, null);
                retAddress.serialize(address, gen, serializers);
            }

            List<Locality> locList = restaurant.getLocList();
            if (locList != null && !locList.isEmpty()) {
            	gen.writeFieldName(Restaurant.fd_locList);
                gen.writeStartArray();
                JsonSerializer<Object> ret = serializers.findValueSerializer(Locality.class, null);
                for (Locality loc : locList)
                    ret.serialize(loc, gen, serializers);
                gen.writeEndArray();
            }
            gen.writeNumberField(Restaurant.fd_saleVolume, restaurant.getSaleVolume() == null ? 0 : restaurant.getSaleVolume());
            
            
            Locality locality = restaurant.getLocality();
            if (locality != null) {
            	gen.writeFieldName(Restaurant.fd_locality);
                JsonSerializer<Object> retLocality = serializers.findValueSerializer(Locality.class, null);
                retLocality.serialize(locality, gen, serializers);
            }
	        gen.writeEndObject();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
}