package com.bjlx.QinShihuang.core.formatter.poi;

import java.io.IOException;
import java.util.List;

import com.bjlx.QinShihuang.model.geo.Locality;
import com.bjlx.QinShihuang.model.misc.Address;
import com.bjlx.QinShihuang.model.misc.Contact;
import com.bjlx.QinShihuang.model.misc.ImageItem;
import com.bjlx.QinShihuang.model.poi.AvailableDay;
import com.bjlx.QinShihuang.model.poi.Description;
import com.bjlx.QinShihuang.model.poi.Hotel;
import com.bjlx.QinShihuang.model.specialservice.RentCar;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

/**
 * 旅馆序列化
 * Created by xiaozhi on 2016/10/31.
 */
public class HotelSerializer extends JsonSerializer<Hotel> {

    @Override
    public void serialize(Hotel hotel, JsonGenerator gen, SerializerProvider serializers) {
	    try {
	        gen.writeStartObject();
	        gen.writeStringField(Hotel.fd_id, hotel.getId() == null ? "" : hotel.getId().toString());
	        if(hotel.getLat() != null)
	        	gen.writeNumberField(Hotel.fd_lat, hotel.getLat());
	        if(hotel.getLng() != null)
	        	gen.writeNumberField(Hotel.fd_lng, hotel.getLng());

	        gen.writeFieldName(Hotel.fd_cover);
            ImageItem cover = hotel.getCover();
            if (cover != null) {
                JsonSerializer<Object> retCover = serializers.findValueSerializer(ImageItem.class, null);
                retCover.serialize(cover, gen, serializers);
            } else {
                gen.writeStartObject();
                gen.writeEndObject();
            }

            List<ImageItem> images = hotel.getImages();
            gen.writeFieldName(Hotel.fd_images);
            gen.writeStartArray();
            if (images != null && !images.isEmpty()) {
                JsonSerializer<Object> ret = serializers.findValueSerializer(ImageItem.class, null);
                for (ImageItem image : images)
                    ret.serialize(image, gen, serializers);
            }
            gen.writeEndArray();
            
            if(hotel.getRank() != null)
                gen.writeNumberField(Hotel.fd_rank, hotel.getRank());
            
            if(hotel.getRating() != null)
                gen.writeNumberField(Hotel.fd_rating, hotel.getRating());

            if(hotel.getHotness() != null)
                gen.writeNumberField(Hotel.fd_hotness, hotel.getHotness());

            
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
            if(hotel.getPriceDesc() != null)
            	gen.writeStringField(Hotel.fd_priceDesc, hotel.getPriceDesc());
            
            if(hotel.getOpenTime() != null)
            	gen.writeStringField(Hotel.fd_openTime, hotel.getOpenTime());

            Description description = hotel.getDescription();
            if (description != null) {
            	gen.writeFieldName(Hotel.fd_description);
                JsonSerializer<Object> retDescription = serializers.findValueSerializer(Description.class, null);
                retDescription.serialize(description, gen, serializers);
            }
            List<String> tags = hotel.getTags();
            if (tags != null && (!tags.isEmpty())) {
            	gen.writeFieldName(Hotel.fd_tags);
                gen.writeStartArray();
                for (String tag : tags)
                    gen.writeString(tag == null ? "" : tag);
                gen.writeEndArray();
            }

            List<String> alias = hotel.getAlias();
            if (alias != null && (!alias.isEmpty())) {
            	gen.writeFieldName(Hotel.fd_alias);
                gen.writeStartArray();
                for (String alia : alias)
                    gen.writeString(alia == null ? "" : alia);
                gen.writeEndArray();
            }
            
            List<String> targets = hotel.getTargets();
            if (targets != null && (!targets.isEmpty())) {
            	gen.writeFieldName(Hotel.fd_targets);
                gen.writeStartArray();
                for (String target : targets)
                    gen.writeString(target == null ? "" : target);
                gen.writeEndArray();
            }

            if(hotel.getSource() != null)
            	gen.writeStringField(Hotel.fd_source, hotel.getSource());

            if(hotel.getGuideUrl() != null)
            	gen.writeStringField(Hotel.fd_guideUrl, hotel.getGuideUrl());

            Address address = hotel.getAddress();
            if (address != null) {
            	gen.writeFieldName(Hotel.fd_address);
                JsonSerializer<Object> retAddress = serializers.findValueSerializer(Address.class, null);
                retAddress.serialize(address, gen, serializers);
            }

            List<Locality> locList = hotel.getLocList();
            if (locList != null && !locList.isEmpty()) {
            	gen.writeFieldName(Hotel.fd_locList);
                gen.writeStartArray();
                JsonSerializer<Object> ret = serializers.findValueSerializer(Locality.class, null);
                for (Locality loc : locList)
                    ret.serialize(loc, gen, serializers);
                gen.writeEndArray();
            }
            gen.writeNumberField(Hotel.fd_saleVolume, hotel.getSaleVolume() == null ? 0 : hotel.getSaleVolume());
            if(hotel.getDiscount() != null)
            	gen.writeNumberField(Hotel.fd_discount, hotel.getDiscount());
            
            RentCar rentCar = hotel.getRentCar();
            if (rentCar != null) {
            	gen.writeFieldName(Hotel.fd_rentCar);
                JsonSerializer<Object> retRentCar = serializers.findValueSerializer(RentCar.class, null);
                retRentCar.serialize(rentCar, gen, serializers);
            }
            
            Locality locality = hotel.getLocality();
            if (locality != null) {
            	gen.writeFieldName(Hotel.fd_locality);
                JsonSerializer<Object> retLocality = serializers.findValueSerializer(Locality.class, null);
                retLocality.serialize(locality, gen, serializers);
            }

            List<AvailableDay> availableDays = hotel.getAvailableDays();
            if (availableDays != null && !availableDays.isEmpty()) {
            	gen.writeFieldName(Hotel.fd_availableDays);
                gen.writeStartArray();
                JsonSerializer<Object> ret = serializers.findValueSerializer(AvailableDay.class, null);
                for (AvailableDay availableDay : availableDays)
                    ret.serialize(availableDay, gen, serializers);
                gen.writeEndArray();
            }

	        gen.writeEndObject();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
}