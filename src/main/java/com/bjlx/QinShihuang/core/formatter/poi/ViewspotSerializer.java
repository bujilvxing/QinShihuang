package com.bjlx.QinShihuang.core.formatter.poi;

import com.bjlx.QinShihuang.model.geo.Locality;
import com.bjlx.QinShihuang.model.misc.Address;
import com.bjlx.QinShihuang.model.misc.Contact;
import com.bjlx.QinShihuang.model.misc.ImageItem;
import com.bjlx.QinShihuang.model.poi.Description;
import com.bjlx.QinShihuang.model.poi.Viewspot;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.util.List;

/**
 * 景点序列化
 * Created by xiaozhi on 2016/10/31.
 */
public class ViewspotSerializer extends JsonSerializer<Viewspot> {

    @Override
    public void serialize(Viewspot viewspot, JsonGenerator gen, SerializerProvider serializers) {
	    try {
	        gen.writeStartObject();
	        gen.writeStringField(Viewspot.fd_id, viewspot.getId() == null ? "" : viewspot.getId().toString());
	        if(viewspot.getLat() != null)
	        	gen.writeNumberField(Viewspot.fd_lat, viewspot.getLat());
	        if(viewspot.getLng() != null)
	        	gen.writeNumberField(Viewspot.fd_lng, viewspot.getLng());

	        gen.writeFieldName(Viewspot.fd_cover);
            ImageItem cover = viewspot.getCover();
            if (cover != null) {
                JsonSerializer<Object> retCover = serializers.findValueSerializer(ImageItem.class, null);
                retCover.serialize(cover, gen, serializers);
            } else {
                gen.writeStartObject();
                gen.writeEndObject();
            }

            List<ImageItem> images = viewspot.getImages();
            gen.writeFieldName(Viewspot.fd_images);
            gen.writeStartArray();
            if (images != null && !images.isEmpty()) {
                JsonSerializer<Object> ret = serializers.findValueSerializer(ImageItem.class, null);
                for (ImageItem image : images)
                    ret.serialize(image, gen, serializers);
            }
            gen.writeEndArray();
            
            if(viewspot.getRank() != null)
                gen.writeNumberField(Viewspot.fd_rank, viewspot.getRank());
            
            if(viewspot.getRating() != null)
                gen.writeNumberField(Viewspot.fd_rating, viewspot.getRating());

            if(viewspot.getHotness() != null)
                gen.writeNumberField(Viewspot.fd_hotness, viewspot.getHotness());

            if(viewspot.getFavorCnt() != null)
                gen.writeNumberField(Viewspot.fd_favorCnt, viewspot.getFavorCnt());

            Contact contact = viewspot.getContact();
            if (contact != null) {
            	gen.writeFieldName(Viewspot.fd_contact);
                JsonSerializer<Object> retContact = serializers.findValueSerializer(Contact.class, null);
                retContact.serialize(contact, gen, serializers);
            }
            
            gen.writeStringField(Viewspot.fd_zhName, viewspot.getZhName() == null ? "" : viewspot.getZhName());
            gen.writeStringField(Viewspot.fd_enName, viewspot.getEnName() == null ? "" : viewspot.getEnName());
            gen.writeStringField(Viewspot.fd_url, viewspot.getUrl() == null ? "" : viewspot.getUrl());
            gen.writeNumberField(Viewspot.fd_marketPrice, viewspot.getMarketPrice() == null ? 0 : viewspot.getMarketPrice());
            gen.writeNumberField(Viewspot.fd_price, viewspot.getPrice() == null ? 0 : viewspot.getPrice());
            if(viewspot.getPriceDesc() != null)
            	gen.writeStringField(Viewspot.fd_priceDesc, viewspot.getPriceDesc());
            
            if(viewspot.getOpenTime() != null)
            	gen.writeStringField(Viewspot.fd_openTime, viewspot.getOpenTime());

            Description description = viewspot.getDescription();
            if (description != null) {
            	gen.writeFieldName(Viewspot.fd_description);
                JsonSerializer<Object> retDescription = serializers.findValueSerializer(Description.class, null);
                retDescription.serialize(description, gen, serializers);
            }
            List<String> tags = viewspot.getTags();
            if (tags != null && (!tags.isEmpty())) {
            	gen.writeFieldName(Viewspot.fd_tags);
                gen.writeStartArray();
                for (String tag : tags)
                    gen.writeString(tag == null ? "" : tag);
                gen.writeEndArray();
            }

            List<String> alias = viewspot.getAlias();
            if (alias != null && (!alias.isEmpty())) {
            	gen.writeFieldName(Viewspot.fd_alias);
                gen.writeStartArray();
                for (String alia : alias)
                    gen.writeString(alia == null ? "" : alia);
                gen.writeEndArray();
            }
            
            List<String> targets = viewspot.getTargets();
            if (targets != null && (!targets.isEmpty())) {
            	gen.writeFieldName(Viewspot.fd_targets);
                gen.writeStartArray();
                for (String target : targets)
                    gen.writeString(target == null ? "" : target);
                gen.writeEndArray();
            }

            if(viewspot.getSource() != null)
            	gen.writeStringField(Viewspot.fd_source, viewspot.getSource());

            if(viewspot.getGuideUrl() != null)
            	gen.writeStringField(Viewspot.fd_guideUrl, viewspot.getGuideUrl());

            Address address = viewspot.getAddress();
            if (address != null) {
            	gen.writeFieldName(Viewspot.fd_address);
                JsonSerializer<Object> retAddress = serializers.findValueSerializer(Address.class, null);
                retAddress.serialize(address, gen, serializers);
            }

            List<Locality> locList = viewspot.getLocList();
            if (locList != null && !locList.isEmpty()) {
            	gen.writeFieldName(Viewspot.fd_locList);
                gen.writeStartArray();
                JsonSerializer<Object> ret = serializers.findValueSerializer(Locality.class, null);
                for (Locality loc : locList)
                    ret.serialize(loc, gen, serializers);
                gen.writeEndArray();
            }
            gen.writeNumberField(Viewspot.fd_saleVolume, viewspot.getSaleVolume() == null ? 0 : viewspot.getSaleVolume());
            if(viewspot.getDiscount() != null)
            	gen.writeNumberField(Viewspot.fd_discount, viewspot.getDiscount());
            Locality locality = viewspot.getLocality();
            if (locality != null) {
            	gen.writeFieldName(Viewspot.fd_locality);
                JsonSerializer<Object> retLocality = serializers.findValueSerializer(Locality.class, null);
                retLocality.serialize(locality, gen, serializers);
            }

	        gen.writeEndObject();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
}