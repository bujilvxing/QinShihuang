package com.bjlx.QinShihuang.core.formatter.poi;

import java.io.IOException;
import java.util.List;

import com.bjlx.QinShihuang.model.geo.Locality;
import com.bjlx.QinShihuang.model.misc.Address;
import com.bjlx.QinShihuang.model.misc.Contact;
import com.bjlx.QinShihuang.model.misc.ImageItem;
import com.bjlx.QinShihuang.model.poi.Description;
import com.bjlx.QinShihuang.model.poi.Shopping;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

/**
 * 购物序列化
 * Created by xiaozhi on 2016/10/31.
 */
public class ShoppingSerializer extends JsonSerializer<Shopping> {

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

            List<ImageItem> images = shopping.getImages();
            gen.writeFieldName(Shopping.fd_images);
            gen.writeStartArray();
            if (images != null && !images.isEmpty()) {
                JsonSerializer<Object> ret = serializers.findValueSerializer(ImageItem.class, null);
                for (ImageItem image : images)
                    ret.serialize(image, gen, serializers);
            }
            gen.writeEndArray();
            
            if(shopping.getRank() != null)
                gen.writeNumberField(Shopping.fd_rank, shopping.getRank());
            
            if(shopping.getRating() != null)
                gen.writeNumberField(Shopping.fd_rating, shopping.getRating());

            if(shopping.getHotness() != null)
                gen.writeNumberField(Shopping.fd_hotness, shopping.getHotness());
            if(shopping.getFavorCnt() != null)
                gen.writeNumberField(Shopping.fd_favorCnt, shopping.getFavorCnt());
            
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
            if(shopping.getPriceDesc() != null)
            	gen.writeStringField(Shopping.fd_priceDesc, shopping.getPriceDesc());
            
            if(shopping.getOpenTime() != null)
            	gen.writeStringField(Shopping.fd_openTime, shopping.getOpenTime());

            Description description = shopping.getDescription();
            if (description != null) {
            	gen.writeFieldName(Shopping.fd_description);
                JsonSerializer<Object> retDescription = serializers.findValueSerializer(Description.class, null);
                retDescription.serialize(description, gen, serializers);
            }
            List<String> tags = shopping.getTags();
            if (tags != null && (!tags.isEmpty())) {
            	gen.writeFieldName(Shopping.fd_tags);
                gen.writeStartArray();
                for (String tag : tags)
                    gen.writeString(tag == null ? "" : tag);
                gen.writeEndArray();
            }

            List<String> alias = shopping.getAlias();
            if (alias != null && (!alias.isEmpty())) {
            	gen.writeFieldName(Shopping.fd_alias);
                gen.writeStartArray();
                for (String alia : alias)
                    gen.writeString(alia == null ? "" : alia);
                gen.writeEndArray();
            }
            
            List<String> targets = shopping.getTargets();
            if (targets != null && (!targets.isEmpty())) {
            	gen.writeFieldName(Shopping.fd_targets);
                gen.writeStartArray();
                for (String target : targets)
                    gen.writeString(target == null ? "" : target);
                gen.writeEndArray();
            }

            if(shopping.getSource() != null)
            	gen.writeStringField(Shopping.fd_source, shopping.getSource());

            if(shopping.getGuideUrl() != null)
            	gen.writeStringField(Shopping.fd_guideUrl, shopping.getGuideUrl());

            Address address = shopping.getAddress();
            if (address != null) {
            	gen.writeFieldName(Shopping.fd_address);
                JsonSerializer<Object> retAddress = serializers.findValueSerializer(Address.class, null);
                retAddress.serialize(address, gen, serializers);
            }

            List<Locality> locList = shopping.getLocList();
            if (locList != null && !locList.isEmpty()) {
            	gen.writeFieldName(Shopping.fd_locList);
                gen.writeStartArray();
                JsonSerializer<Object> ret = serializers.findValueSerializer(Locality.class, null);
                for (Locality loc : locList)
                    ret.serialize(loc, gen, serializers);
                gen.writeEndArray();
            }
            gen.writeNumberField(Shopping.fd_saleVolume, shopping.getSaleVolume() == null ? 0 : shopping.getSaleVolume());
            if(shopping.getDiscount() != null)
            	gen.writeNumberField(Shopping.fd_discount, shopping.getDiscount());
            
            Locality locality = shopping.getLocality();
            if (locality != null) {
            	gen.writeFieldName(Shopping.fd_locality);
                JsonSerializer<Object> retLocality = serializers.findValueSerializer(Locality.class, null);
                retLocality.serialize(locality, gen, serializers);
            }

	        gen.writeEndObject();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
}